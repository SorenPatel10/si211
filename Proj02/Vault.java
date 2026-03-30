/*
MIDN Soren Patel
Proj02
Vault.java
*/

import java.util.*;
import java.io.*;

/**
 * Vault class
 */
public class Vault {

    /**
     * private inner class to store user information
     */
    private static class UserInfo {
        String username;
        String alg;
        String hash;

        /**
         * constructor
         */
        public UserInfo(String username, String alg, String hash) {
            this.username = username;
            this.alg = alg;
            this.hash = hash;
        }
    }

    /**
     * private inner class to store other data entries
     */
    private static class DataEntry{
        String username;
        String encalg;
        String ciphertext;

        /**
         * constructor
         */
        public DataEntry(String username, String encalg, String ciphertext){
            this.username = username;
            this.encalg = encalg;
            this.ciphertext = ciphertext;
        }
    }

    /**
     * method to read users and data from a vault file
     */
    private static boolean readFile(String fname, ArrayList<UserInfo> users, ArrayList<DataEntry> dataList) {

        //open file
        try (Scanner sc = new Scanner(new File(fname))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty())
                    continue;

                //split line by whitespace
                String[] tokens = line.split("\\s");
                //check file formatting
                if (tokens[0].equals("user")) {
                    if(tokens.length != 4){
                        System.out.println("Error! File '" + fname + "' improperly formatted.");
                        return false;
                    }
                    //add user to userlist
                    users.add(new UserInfo(tokens[1], tokens[2], tokens[3]));
                }
                //data line
                else if(tokens[0].equals("data")){
                    if(tokens.length != 4){
                        System.out.println("Error! File '" + fname + "' improperly formatted.");
                        return false;
                    }
                    //add data to datalist
                    dataList.add(new DataEntry(tokens[1], tokens[2], tokens[3]));
                }
                else{
                    System.out.println("Error! File '" + fname + "' improperly formatted.");
                    return false;
                }
            }
        }
        //file not found
        catch (FileNotFoundException f) {
            System.out.println("Error! File '" + fname + "' could not be opened.");
            return false;
        }
        return true;
    }

    /**
     * method to write all users back to vault file
     */
    private static boolean writeUsers(String fname, ArrayList<UserInfo> users) {
        //open printwrite to write to file
        try (PrintWriter pw = new PrintWriter(new File(fname))) {
            for (UserInfo temp: users){
                pw.println("user " + temp.username + " " + temp.alg + " " + temp.hash);
            }
            //successful write
            return true;
        }
        //write failure due to FNFexception
        catch (FileNotFoundException e) {
            System.out.println("Error! Could not write to file '" + fname + "'.");
            return false;
        }
    }

    /**
     * method to return a hasher based on algorithm name
     * uses switch case logic
     */
    private static Hasher getHasher(String alg) {
        switch (alg) {
            case "padcut":
                return new PadCutHash();
            case "shift+caesar":
                return new ShiftCaesarHash();
            case "shift+vigenere":
                return new ShiftVigenereHash();
            default:
                return null;
        }
    }

    /**
     * method to return encryptor based on the encalg name
     */
    private static Encryptor getEncryptor(String alg){
        switch(alg){
            case "clear":
                return new Clear();
            case "caesar":
                return new Caesar();
            case "vigenere":
                return new Vigenere();
            default:
                return null;
        }
    }

    /**
     * method to authenticate a user
     */
    private static void authenticate(Console con, ArrayList<UserInfo> users, ArrayList<DataEntry> dataList) {
        //user input handling for username and password
        String username = con.readLine("username: ");
        System.out.print("password: ");
        char[] pswdChars = con.readPassword();
        String pswd = new String(pswdChars);

        //search for username
        UserInfo match = null;
        for (UserInfo temp: users) {
            if (temp.username.equals(username)) {
                match = temp;
                break;
            }
        }

        //no match
        if (match == null) {
            System.out.println("Access denied!");
            return;
        }

        //get hasher for user's algorithm
        Hasher algChoice = getHasher(match.alg);
        if (algChoice == null) {
            System.out.println("Error! Hash algorithm '" + match.alg + "' not supported.");
            return;
        }

        try {
            //compute hash
            String computed = algChoice.hash(pswd.toCharArray());

            //grant access if hashes match and enter command loop
            if(computed.equals(match.hash)) {
                System.out.println("Access granted!");
                commandLoop(con, username, pswd, dataList);
            }
            else
                System.out.println("Access denied!");

        }
        catch (Exception e) {
            System.out.println("Access denied!");
        }   
    }

    /**
     * method to add a new user
     */
    private static void addUser(Console con, ArrayList<UserInfo> users, String fname) {
        //user input for new user information
        String username = con.readLine("username: ");
        System.out.print("password: ");
        char[] pswdChars = con.readPassword();
        String password = new String(pswdChars);
        String alg = con.readLine("Hash algorithm: ");

        //error check password for valid ASCII range
        for(char c: password.toCharArray()){
            if (c < 42 || c > 122){
                System.out.println("Error! Invalid symbol '" + c + "' in password.");
                return;
            }
        }

        //error check algorithm input
        Hasher hasher = getHasher(alg);
        if(hasher == null){
            System.out.println("Error! Hash algorithm '" + alg + "' not supported.");
            return;
        }

        //error check username uniqueness
        for(UserInfo u: users) {
            if (u.username.equals(username)) {
                System.out.println("Error! Username '" + username + "' already in use.");
                return;
            }
        }

        //compute hash
        String hash;
        try {
            hash = hasher.hash(password.toCharArray());
        }
        catch(Exception e){
            System.out.println("Error! Could not hash password.");
            return;
        }

        //add user to arraylist and write to file
        users.add(new UserInfo(username, alg, hash));
        writeUsers(fname, users);
    }

    /**
     * function handling command loop and user input for commands
     */
    private static void commandLoop(Console con, String username, String password, ArrayList<DataEntry> dataList){
        while(true){
            //get user input
            String cmd = con.readLine("> ");

            //quit
            if(cmd.equals("quit")){
                break;
            }
            //labels
            else if(cmd.equals("labels")){
                handleLabels(username, password, dataList);
            }
            //get x
            //uses startsWith method to determine if get keyword is there
            else if(cmd.startsWith("get ")){
                //get the rest of the string after "get"
                String lab = cmd.substring(4);
                handleGet(username, password, lab, dataList);
            }
            //bad command
            else{
                System.out.println("Unknown command '" + cmd + "'.");
            }
        }
    }

    /**
     * helper method for labels cmd
     */
    private static void handleLabels(String username, String password, ArrayList<DataEntry> dataList){
        //loop through each
        for (DataEntry temp: dataList) {
            //username match check
            if (!temp.username.equals(username))
                continue;

            try {
                Encryptor enc = getEncryptor(temp.encalg);

                if (enc == null) {
                    System.out.println("Error! Encryption algorithm '" + temp.encalg + "' not supported.");
                    continue;
                }
                //decryption process
                enc.init(password.toCharArray());
                String plain = enc.decrypt(temp.ciphertext);

                int ind = plain.indexOf('_');
                if (ind == -1)
                    throw new Exception();

                //get label, up to the first '_'
                String label = plain.substring(0, ind);
                System.out.println(label);
            }
            catch (Exception e) {
                System.out.println("Error! corrupted entry '" + temp.ciphertext + "' in vault file.");
            }
        }
    }

    /**
     * helper method for get command
     */
    private static void handleGet(String username, String password, String targetLabel, ArrayList<DataEntry> dataList){
        //loop through datalist
        for (DataEntry temp: dataList) {
            if (!temp.username.equals(username))
                continue;

            try {
                Encryptor enc = getEncryptor(temp.encalg);

                //error check for bag alg name
                if (enc == null) {
                    System.out.println("Error! Encryption algorithm '" + temp.encalg + "' not supported.");
                    continue;
                }

                //decryption process
                enc.init(password.toCharArray());
                String plain = enc.decrypt(temp.ciphertext);


                int ind = plain.indexOf('_');
                if (ind == -1)
                    throw new Exception();

                //separate the label and the rest of the text
                String label = plain.substring(0, ind);
                String text = plain.substring(ind + 1);

                //get the right label, compare to target
                if (label.equals(targetLabel)) {
                    System.out.println(text);
                    return;
                }
            }
            catch (Exception e) {
                System.out.println("Error! corrupted entry '" + temp.ciphertext + "' in vault file.");
            }
        }
    }
    
    /**
     * main method
     */
    public static void main(String[] args) {
        //check command line arguments
        if (args.length < 1) {
            System.out.println("usage: java Vault [-au] <filename>");
            return;
        }
        //flag to check if we have -au
        boolean isAddUser = args[0].equals("-au");

        //ternary operator I know Dr. Brown likes
        String fname = isAddUser ? args[1] : args[0];

        Console con = System.console();

        //declare two lists
        ArrayList<UserInfo> users = new ArrayList<>();
        ArrayList<DataEntry> dataList = new ArrayList<>();

        //read in data and users
        if(!readFile(fname, users, dataList))
            return;
        
        //either add users or authenticate based on -au flag
        if(isAddUser)
            addUser(con, users,fname);
        else
            authenticate(con,users, dataList);
    }
}