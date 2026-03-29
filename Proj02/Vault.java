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
     * method to read users from a vault file
     */
    private static ArrayList<UserInfo> readUsers(String fname) {
        ArrayList<UserInfo> users = new ArrayList<>();
        //open file
        try (Scanner sc = new Scanner(new File(fname))) {
            int lineNumber = 0;
            while (sc.hasNextLine()) {
                lineNumber++;
                String line = sc.nextLine().trim();
                if (line.isEmpty())
                    continue;

                //split line by whitespace
                String[] tokens = line.split("\\s");
                //check file formatting
                if (tokens.length != 4 || !tokens[0].equals("user")) {
                    System.out.println("Error! File '" + fname + "' improperly formatted.");
                    return null;
                }

                //create user info and add to list
                users.add(new UserInfo(tokens[1], tokens[2], tokens[3]));
            }
        }
        //file not found
        catch (FileNotFoundException f) {
            System.out.println("Error! File '" + fname + "' could not be opened.");
            return null;
        }
        return users;
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
     * method to authenticate a user
     */
    private static void authenticate(Console con, ArrayList<UserInfo> users) {
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
            //compute hash and compare
            if ((algChoice.hash(pswd.toCharArray())).equals(match.hash)) {
                System.out.println("Access granted!");

                //command loop
                while (true) {
                    String cmd = con.readLine("> ");
                    if (cmd.equals("quit"))
                        break;
                }
            } else {
                System.out.println("Access denied!");
            }
        } catch (Exception e) {
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

        //read users from vault file
        ArrayList<UserInfo> users = readUsers(fname);
        if (users == null)
            return;

        //either add user or authenticate depending what mode program is in
        if(isAddUser)
            addUser(con, users, fname);
        else
            authenticate(con, users);
    }
}