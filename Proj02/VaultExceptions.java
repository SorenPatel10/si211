/*
MIDN Soren Patel
Proj02
VaultExceptions.java
*/

/**
 * various types of exceptions which could occur
 */
public class VaultExceptions {

    /**
     * text contains illegal characters out of range min-max
     */
    public static class IllegalCharacterException extends Exception {
        public IllegalCharacterException(char c, int min, int max) {
            super("Illegal character '" + c + "' found. Allowed ASCII range is [" + min + "," + max + "].");
        }
    }

    /**
     * label is invalid
     */
    public static class InvalidLabelException extends Exception {
        public InvalidLabelException(String label) {
            super("Label '" + label + "' is invalid.");
        }
    }

    /**
     * vault entry is corrupted (cannot be decrypted or lacks '_')
     */
    public static class CorruptedEntryException extends Exception {
        public CorruptedEntryException(String ciphertext) {
            super("Corrupted entry '" + ciphertext + "' in vault file.");
        }
    }

    /**
     * encryption algorithm is not supported
     */
    public static class UnsupportedEncryptionException extends Exception {
        public UnsupportedEncryptionException(String encalg) {
            super("Encryption algorithm '" + encalg + "' not supported.");
        }
    }

    /**
     * hash algorithm is not supported
     */
    public static class UnsupportedHashException extends Exception {
        public UnsupportedHashException(String hashalg) {
            super("Hash algorithm '" + hashalg + "' not supported.");
        }
    }

    /**
     * empty encryption/decryption key
     */
    public static class EmptyKeyException extends Exception {
        //constructor for default message
        public EmptyKeyException() {
            super("Key is empty.");
        }

        // constructor with custom message
        public EmptyKeyException(String msg) {
            super(msg);
        }
    }
}