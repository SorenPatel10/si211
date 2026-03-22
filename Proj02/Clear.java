// Encryptor providing the clear algorithm, which is to
// not change the plaintext at all.
public class Clear implements Encryptor {
  
  public String getAlgName() { return "clear"; }

  public void   init(char[] key)throws Exception{
    //error checking
    if(key==null || key.length==0)
      throw new Exception("Key is empty.");

    for(char c: key){
      if(c<42 || c>122)
        throw new Exception("Character in key out of range [42,122].");
    }
  }

  public String encrypt(String plain) throws Exception{
    
    for(int i = 0; i < plain.length(); i++){
      char pc = plain.charAt(i);
      
      //error checking
      if(pc < 42 || pc > 122)
        throw new Exception("Character out of range [42,122].");
    }
    return plain;
  }

  public String decrypt(String cipher) throws Exception{
    for(int i = 0; i < cipher.length(); i++){
      char cc = cipher.charAt(i);
      
      //error checking
      if(cc < 42 || cc > 122)
        throw new Exception("Character out of range [42,122].");
    }
    return cipher;
  }
}