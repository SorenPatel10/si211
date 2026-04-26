/**
 * Soren Patel
 * Proj03
 * GameListener.java
 */

/**
 * purpose of this interace is to remove the cyclic dependency between Board and GameFrame
 */
public interface GameListener{
    void gameFinished();
}