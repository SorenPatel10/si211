// This matches the basic in/out functionality of Part 1.
// As you implement more Encryptors, you just put an "add"
// line in for them, and they become part of thie test driver.
import java.util.*;
public class TestEncryptors {
  public static void main(String[] args) throws Throwable {
    // Create ArrayList of all supported encryptors
    ArrayList<Encryptor> E = new ArrayList<Encryptor>();
    E.add(new Clear());
    E.add(new Caesar());
    E.add(new Vigenere());

    // Get alg,psw,msg from user
    System.out.print("algorithm: ");
    String encalg = System.console().readLine();
    System.out.print("password : ");
    char[] password = System.console().readPassword();
    System.out.print("message  : ");
    String plaintext = System.console().readLine();
    
    // Find encryptor (throw exception if not found)
    int i = 0;
    while(i < E.size() && !E.get(i).getAlgName().equals(encalg))
      i++;
    if (i == E.size())
      throw new Exception("Unknown algorithm '"+encalg+"'.");
    Encryptor enc = E.get(i);

    // Encrypt, decrypt print sumamry of results
    enc.init(password);
    String ciphertext = enc.encrypt(plaintext);
    String hopefully = enc.decrypt(ciphertext);
    System.out.println("plain : " + plaintext);
    System.out.println("cipher: " + ciphertext);
    System.out.println("decryp: " + hopefully);
  }
}