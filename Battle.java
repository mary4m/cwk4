package cwk4;
import java.io.Serializable;

/**
 * Battles class to store and manage battle information
 *
 * @author Team 23
 * @version April 2023
 */
public class Battle implements Serializable
{
    //fields
    private int battleNo;
    private String Enemy;
    private int EStrength;
    private int Losses;
    private int Gains;
    private BattleType type;

    //constructor
    /** Creates a Battle
     * @param battleNo represents the battle number
     * @param type represents the battle type
     * @param Enemy represents the enemy of the battle
     * @param EStrength represents the strength of the enemy
     * @param Losses represents the losses of the battle
     * @param Gains represents the gains of the battle
     */
    public Battle(int battleNo, BattleType type, String Enemy, int EStrength, int Losses, int Gains)
    {
        this.battleNo = battleNo;
        this.type = type;
        this.Enemy = Enemy;
        this.EStrength = EStrength;
        this.Losses = Losses;
        this.Gains = Gains;
    }

    //methods
    /** Returns the battle number
     * @return battle number
     */
    public int getBattleNo()
    {
        return battleNo;
    }

    /** Returns strength of the enemy
     * @return enemy strength
     */
    public int getEnemyStrength()
    {
        return EStrength;
    }

    /** Returns the losses of the battle
     * @return battle losses
     */
    public int getLosses()
    {
        return Losses;
    }

    /** Returns the gains of the battle
     * @return battle gains
     */
    public int getGains()
    {
        return Gains;
    }

    /** Returns type of the battle
     * @return battle type
     */
    public BattleType getBattleType()
    {
        return type;
    }

    /** Returns a String representation of the battle information
     * @return the battle information as a String
     */
    @Override
    public String toString()
    {
        String s;
        s = "\nBattle Number: " + battleNo
                + "\nType: " + type
                + "\nEnemy: " + Enemy
                + "\nEnemy Strength: " + EStrength
                + "\nLosses: " + Losses
                + "\nGains: " + Gains;

        return s;
    }
}
