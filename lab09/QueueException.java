/*
MIDN 3/C Soren Patel
Lab09
QueueException.java
*/
import java.util.*;

/**
 * QueueException class, extension of RuntimeException
 */
class QueueException extends RuntimeException
{
  //constructor method to throw exceptions
    public QueueException(String msg)
  {
    super(msg);
  }
}