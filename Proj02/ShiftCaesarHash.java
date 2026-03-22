/*
MIDN Soren Patel
Proj02
ShiftCaesarHash.java
*/

/**
 * shift+caesar hashing algorithm
 */
public class ShiftCaesarHash implements Hasher {

    // field to store password
    private char[] password;

    /**
     * returns hash algorithm name
     */
    public String getAlgName() {
        return "shift+caesar";
    }

    /**
     * calculations to get hash
     */
    public String hash(char[] password) throws Exception {
        ///error check
        if(password == null || password.length == 0)
            throw new Exception("Hasher not initialized with password.");

        //default starting value
        String x = "GO_NAVY_2018^mid";
        Caesar caesar = new Caesar();
        caesar.init(password);

        //16 iter loop
        for(int i = 0; i < 16; i++) {
            char c = x.charAt(i);
            int shift = ((int)c) % 16;

            //left shift x
            x = x.substring(shift) + x.substring(0, shift);

            //encrypt with caesar
            x = caesar.encrypt(x);
        }
        return x;
    }
}