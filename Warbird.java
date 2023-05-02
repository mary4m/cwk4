package cwk4;
import java.io.Serializable;
/**
 * The Warbird class represents a Force object.
 * Created with a cloaking value determined by a parameter value
 * @author Team 23
 * @version April 2023
 */
public class Warbird extends Force implements Serializable
{
    // extra fields
    private boolean Cloaking;

    /** Extends the force class with Cloaking field
     * @param Ref represents reference of the force
     * @param Name represents the name of the force
     * @param Strength represents the strength of the force
     * @param Fee represents the fee of the force
     * @param Cloaking represents if force is cloaking
     */
    public Warbird(String Ref, String Name, int Strength, int Fee, boolean Cloaking)
    {
        super(Ref, Name, Strength, Fee, ForceState.DOCKED, "Warbird");
        this.Cloaking = Cloaking;
    }

    /** Returns true if the force is cloaking
     * @return cloaking
     */
    public boolean isCloaking()
    {
        return Cloaking;
    }

    /** Returns a String representation of the wing information
     * @return the wing information as a String
     */
    public String toString()
    {
        String s = "";
        s = s + super.toString();
        s = s + "\nCloaking: " + isCloaking();
        return s;
    }
}