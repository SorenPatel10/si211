/*
Essentially the exact same as RecogJPG, this also extends RecogASCII
byte values changed to reflect a PDF instead of a JPG
*/


/**
 * JPG file header is 25 50 44 46
 * This recognizer checks whether the first 4 bytes
 * match this header.
 */
public class RecogPDF extends RecogASCII
{
  // NOTE: 0x starts of hex integer literals, so 0x25 is the int 37.
  private static int[] header = 
  { 0x25, 0x50, 0x44, 0x46};
  // 0     1     2     3   
  private int i = 0;

  public String getName() { return "PDF"; }

  public void feed(int nextByte)
  {
    if (getState() == 2) // 2 : unkown
    {
      if (header[i] >= 0 && nextByte != header[i])
	setState(0); // 0 : not match
    }
    i++;
    if (i > 3 && getState() != 0)
      setState(1); // 1 : match
  }
  boolean decision() { return getState() == 1; }
}