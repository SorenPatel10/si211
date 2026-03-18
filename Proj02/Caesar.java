/*
MIDN Soren Patel
Proj02
Caesar.java
*/

/**
 * caesar shift class, implements encryptor interface
 */
public class Caesar implements Encryptor{

    //field defining amount of caesar shift
    private int k;

    /**
     * method returns algorithm name
     */
    public String getAlgName(){
        return "caesar";
    }

    /**
     * method processes the encryption key
     */
    public void init(char[] key) throws Exception{
        //error check
        if(key == null || key.length == 0)
            throw new Exception("Key is empty.");
        
        //start at 18, error check
        int sum = 18;
        for(char c: key){
            if(c < 42 || c > 122)
                throw new Exception("Character in key out of range [42,122].");

            sum += (c-42);
        }

        //calc and store value into field k so it will carry across methods
        int sc = 42 + (sum%81);
        k = sc-42;
    }

    /**
     * method to encrypt plaintext
     */
    public String encrypt(String plain) throws Exception{
        String res = "";

        for(int i = 0; i < plain.length(); i++){
            char pc = plain.charAt(i); 

            //error check
            if(pc < 42 || pc > 122)
                throw new Exception("Character out of range [42,122].");
            
            //encryption calculations
            int p = pc-42;
            int c = (p+k) % 81;
            char cc = (char)(42+c);
            res += cc;
        }
        return res;
    }

    /**
     * method to decrypt ciphertext
     */
    public String decrypt(String cipher) throws Exception{
        String res = "";

        for(int i = 0; i < cipher.length(); i++){
            char cc = cipher.charAt(i);

            //error check
            if(cc < 42 || cc > 122)
                throw new Exception("Character out of range [42,122].");

            //decryption calculations
            int c = cc-42;
            int p = (c+(81-k)) % 81;
            char pc = (char)(42+p);
            res += pc;
        }
        return res;
    }

}