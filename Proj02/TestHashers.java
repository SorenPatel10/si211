/*
MIDN Soren Patel
Proj02
TestHashers.java
*/

import java.util.*;

/**
 * Driver file to test hash algorithms
 * written in the same style as the given TestEncryptors.java
 */
public class TestHashers {
    public static void main(String[] args) throws Throwable {

        // Create ArrayList of all supported hashers
        ArrayList<Hasher> H = new ArrayList<Hasher>();
        H.add(new PadCutHash());
        H.add(new ShiftCaesarHash());
        H.add(new ShiftVigenereHash());

        // Get algorithm and password from user
        System.out.print("algorithm: ");
        String hashalg = System.console().readLine();
        System.out.print("password : ");
        //hidden input
        char[] password = System.console().readPassword();

        //Show password read for test output
        System.out.println("password read : " + (new String(password)));

        // Find hasher (throw exception if not found)
        int i = 0;
        while(i < H.size() && !H.get(i).getAlgName().equals(hashalg))
            i++;
        if(i == H.size())
            throw new Exception("Unknown hash algorithm '" + hashalg + "'.");

        Hasher hasher = H.get(i);

        //perform hash
        String hashvalue = hasher.hash(password);
        System.out.println("hash computed : " + hashvalue);
    }
}