package cwk4;
import java.io.*;
/**
 * Enumeration class ForceState 
 *
 * @author Team 23
 * @version April 2023
 */
public enum ForceState implements Serializable
{
    // fields
    DOCKED(" In dock"), ACTIVE(" Active"), DESTROYED (" destroyed");
    private String state;

    /** Sets the state of the force
     * @param st represents the state of the force
     */
    private ForceState(String st)
    {
        state = st;
    }

    /** Returns a String representation of the force state
     * @return the force state as a String
     */
    public String toString()
    {
        return state;
    }
}