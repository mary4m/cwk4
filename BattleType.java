package cwk4;
import java.io.*;
/**
 * Enumeration class BattleType to store and manage battle types
 *
 * @author Team 23
 * @version April 2023
 */
public enum BattleType implements Serializable
{
    SKIRMISH (" Skirmish"), AMBUSH(" Ambush"), FIGHT(" Fight") ;
    private String type;

    private BattleType(String ty)
    {
        type = ty;
    }

    /** Returns a String representation of the battle type
     * @return battle type as a String
     */
    public String toString()
    {
        return type;
    }
}