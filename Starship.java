package cwk4;
import java.io.Serializable;
/**
 * The Starship class represents a Force object.
 * Created with a number of laser cannons and photon torpedoes determined by parameter values
 * @author Team 23
 * @version April 2023
 */
public class Starship extends Force implements Serializable
{
    // extra fields
    private int LaserCannons;
    private int PhotonTorpedoes;

    /** Extends the force class with laser cannons and photon torpedoes
     * @param Ref represents reference of the force
     * @param Name represents the name of the force
     * @param Strength represents the strength of the force
     * @param Fee represents the fee of the force
     * @param LaserCannons represents the laser cannons of the force
     * @param PhotonTorpedoes represents the photon torpedoes of the force
     */
    public Starship(String Ref, String Name, int Strength, int Fee, int LaserCannons, int PhotonTorpedoes)
    {
        super(Ref, Name, Strength, Fee, ForceState.DOCKED, "Starship");
        this.LaserCannons = LaserCannons;
        this.PhotonTorpedoes = PhotonTorpedoes;
    }

    /** Returns the laser cannons of the force
     * @return laser cannons
     */
    public int getLaserCannons()
    {
        return LaserCannons;
    }

    /** Returns the photon torpedoes of the force
     * @return photon torpedoes
     */
    public int getPhotonTorpedoes()
    {
        return PhotonTorpedoes;
    }

    /** Returns a String representation of the starship information
     * @return the starship information as a String
     */
    public String toString()
    {
        String s = "";
        s = s + super.toString();
        s = s + "\nLaser Cannons: " + getLaserCannons()
                + "\nPhoton Torpedoes: " + getPhotonTorpedoes();
        return s;
    }
}