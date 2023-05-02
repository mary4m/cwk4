package cwk4;
import java.io.Serializable;
/**
 * The Wing class represents a Force object.
 * Created with a number of strikers determined by a parameter value
 * @author Team 23
 * @version April 2023
 */
public class Wing extends Force implements Serializable
{
    // extra fields
    private int Strikers;

    /** Extends a force class with strikers
     *
     * @param Ref represents reference of the force
     * @param Name represents the name of the force
     * @param Strength represents the strength of the force
     * @param Fee represents the fee of the force
     * @param Strikers represents the strikers
     */
    public Wing(String Ref, String Name, int Strength, int Fee, int Strikers)
    {
        super(Ref, Name, Strength, Fee, ForceState.DOCKED, "Wing");
        this.Strikers = Strikers;
    }

    /** Returns the strikers
     * @return strikers
     */
    public int getStrikers()
    {
        return Strikers;
    }

    /** Returns a String representation of the wing information
     * @return the wing information as a String
     */
    public String toString()
    {
        String s = "";
        s = s + super.toString();
        s = s + "\nStrikers: " + getStrikers();
        return s;
    }
}