// interface for objects that provide
// encryption functionality.
public interface Encryptor {
  public String getAlgName();
  public void   init(char[] key) throws Exception;
  public String encrypt(String plain) throws Exception;
  public String decrypt(String cipher) throws Exception;
}