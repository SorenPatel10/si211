/*
MIDN Soren Patel
Proj02
Hasher.java
*/

/**
 * Hasher interface
 */
public interface Hasher{
    public String getAlgName();
    public String hash(char[] password) throws Exception;
}