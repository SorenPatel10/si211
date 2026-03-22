/*
MIDN Soren Patel
Proj02
PadCutHash.java
*/

/**
 * padcut algorithm class, implements hasher
 */
public class PadCutHash implements Hasher{
    
    /**
     * method returns alg name
     */
    public String getAlgName(){
        return "padcut";
    }

    /**
     * even though key is not necessary for hashing, still check
     */
    public void init(char[] key) throws Exception{
        //error check key
        if(key != null){
            for(char c : key){
                if(c < 42 || c>122)
                    throw new Exception("Character in key out of range [42,122].");
            }
        }
    }

    /**
     * hashing algorithm method (first 16 chars or string + x's)
     */
    public String hash(char[] password) throws Exception{

        //error check
        for(char c: password){
            if(c < 42 || c > 122)
                throw new Exception("Character out of range [42,122].");
        }
        String res = new String(password);
        //chop first 16 case
        if(res.length() >= 16)
            return res.substring(0,16);
        //whole string case, add enough x's
        else{
            while(res.length() < 16)
                res += "x";
            return res;
        }
    }
}