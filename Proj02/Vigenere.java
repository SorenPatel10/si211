/*
MIDN Soren Patel
Proj02
Vigenere.java
*/

/**
 * vigenere shift class, implements encryptor interface
 */
public class Vigenere implements Encryptor{

    //field to store key
    private char[] key;

    /**
     * method returns algorithm name
     */
    public String getAlgName(){
        return "vigenere";
    }

    /**
     * method processes the encryption key
     */
    public void init(char[] key) throws VaultExceptions.EmptyKeyException, VaultExceptions.IllegalCharacterException{
        //error check
        if(key == null || key.length == 0)
            throw new VaultExceptions.EmptyKeyException();

        //error check
        for(char c: key){
            if(c < 42 || c > 122)
                throw new VaultExceptions.IllegalCharacterException(c, 42, 122);
        }

        //store key
        this.key=key;
    }

    /**
     * method to encrypt plaintext
     */
    public String encrypt(String plain) throws VaultExceptions.IllegalCharacterException{
        String res = "";
        int n = key.length;

        for(int i = 0; i < plain.length(); i++){
            char pc = plain.charAt(i); 

            //error check
            if(pc < 42 || pc > 122)
                throw new VaultExceptions.IllegalCharacterException(pc, 42, 122);
            
            //encryption calculations
            char sc = key[i % n];
            int k = sc - 42;
            int p = pc - 42;
            int c = (p + k) % 81;

            char cc = (char)(42 + c);
            res += cc;
        }
        return res;
    }

    /**
     * method to decrypt ciphertext
     */
    public String decrypt(String cipher) throws VaultExceptions.IllegalCharacterException{
        String res = "";
        int n = key.length;

        for(int i = 0; i < cipher.length(); i++){
            char cc = cipher.charAt(i);

            //error check
            if(cc < 42 || cc > 122)
                throw new VaultExceptions.IllegalCharacterException(cc, 42, 122);

            //decryption calculations
            char sc = key[i % n];
            int k = sc - 42;
            int c = cc - 42;
            int p = (c + (81 - k)) % 81;

            char pc = (char)(42 + p);
            res += pc;
        }
        return res;
    }

}