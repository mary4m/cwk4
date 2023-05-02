package cwk4;

import java.io.Serializable;
/**
 * Force class to store and manage force information
 *
 * @author Team 23
 * @version April 2023
 */
public class Force implements Serializable
{
    //fields
    private String Ref;
    private String Name;
    private int Fee;
    private int Strength;
    private String Type;
    private ForceState State;

    // constructor
    /** Creates a Force
     * @param Ref represents the reference of the force
     * @param Name represents the name of the force
     * @param Strength represents the strength of the force
     * @param Fee represents the fee of the force
     * @param State represents the state of the force
     * @param Type represents the type of the force
     */
    public Force(String Ref, String Name, int Strength, int Fee, ForceState State, String Type)
    {
        this.Ref = Ref;
        this.Name = Name;
        this.Fee = Fee;
        this.Strength = Strength;
        this.Type = Type;
        this.State = State;
    }

    /** Returns the reference of the force
     * @return force reference
     */
    public String getRef()
    {
        return Ref;
    }

    /** Returns the fee of the force
     * @return force fee
     */
    public int getFee()
    {
        return Fee;
    }

    /** Returns the strength of the force
     * @return force strength
     */
    public int getStrength()
    {
        return Strength;
    }

    /** Changes state of the force
     * @param newState represents the new state of the force
     */
    public void changeState(ForceState newState)
    {
        this.State = newState;
    }

    /** Returns state of the force
     * @return force state
     */
    public ForceState getState()
    {
        return this.State;
    }

    /** Returns the battle result
     * @param battle represents the battle
     * @param forceStrength represents the strength of the force
     * @return battle result
     */
    public static int BattleResult(Battle battle, int forceStrength)
    {
        int enemyStrength = battle.getEnemyStrength();
        if (forceStrength >= enemyStrength) {
            return 0; // Battle won
        } else if (forceStrength < enemyStrength / 2) {
            return 2; // Battle lost due to enemy Strength
        } else {
            return 2; // Battle lost due to insufficient force Strength
        }
    }

    public String toString()
    {
        String s;
        s = "\nReference: " + Ref
                + "\nName: " + Name
                + "\nFee: " + Fee
                + "\nStrength: " + Strength
                + "\nState: " + State
                + "\nType: " + Type;

        return s;
    }
}