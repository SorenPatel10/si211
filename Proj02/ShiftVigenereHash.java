/*
MIDN Soren Patel
Proj02
ShiftVigenereHash.java
*/

/**
 * shift+vigenere hashing algorithm
 */
public class ShiftVigenereHash implements Hasher {

    // field to store password
    private char[] password;

    /**
     * returns hash algorithm name
     */
    public String getAlgName() {
        return "shift+vigenere";
    }


    /**
     * calcualtions to get the hash
     */
    public String hash(char[] password) throws Exception {
        //error check
        if(password == null || password.length == 0)
            throw new Exception("Hasher not initialized with password.");

        //starting value
        String x = "GO_NAVY_2018^mid";
        Vigenere vig = new Vigenere();
        vig.init(password);

        //16 iter loop
        for(int i = 0; i < 16; i++) {
            char c = x.charAt(i);
            int shift = ((int)c) % 16;

            //left shift x
            x = x.substring(shift) + x.substring(0, shift);

            // encrypt with vigenere to get final hash
            x = vig.encrypt(x);
        }
        return x;
    }
}