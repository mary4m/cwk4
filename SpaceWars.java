package cwk4;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.io.*;

/**
 * This class implements the behaviour expected from a WIN
 system as required for 5COM2007 - March 2023
 *
 * @author Team 23
 * @version April 2023
 */

public class SpaceWars implements WIN
{
    // fields
    private int warchest = 1000;
    private String Admiral;
    private String filename;
    private ArrayList<Force> allForces = new ArrayList<>();
    private ArrayList<Battle> allBattles = new ArrayList<>();

//****** WIN **********
    /** Constructor requires the name of the admiral
     * @param admiral represents the name of the admiral
     */
    public SpaceWars(String admiral)
    {
        this.Admiral = admiral;

        setupForces();
        setupBattles();
    }

    /** Second constructor - task 3.5
     *  To be added for task 3.5
     */
    public SpaceWars(String admiral, String fname)
    {
        this.Admiral = admiral;
        this.filename = fname;

        setupForces();
        readBattles("battles.txt");
    }

    /**Returns a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the
     * Active Star Fleet(ASF),(or, "No forces" if Star Fleet is empty)
     * @return a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the
     * Star Fleet,(or, "No forces" if Active Star Fleet is empty)
     **/
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Admiral: ").append(getAdmiral()).append("\n");
        sb.append("War Chest: $").append(getWarchest()).append("\n");
        sb.append("Defeated: ").append(isDefeated()).append("\n");

        boolean activeFleetExists = false;
        for (Force force : allForces) {
            if (force.getState() == ForceState.ACTIVE) {
                if (!activeFleetExists) {
                    activeFleetExists = true;
                }
                sb.append(getASFleet()).append(", ");
            }
        }

        if (!activeFleetExists) {
            sb.append("No forces");
        } else {
            sb.setLength(sb.length() - 2); // Remove the last ", "
        }

        return sb.toString();
    }

    /** returns true if war chest <=0 AND the active Star Fleet(ASF) has no
     * forces which can be recalled.
     * @returns true if war chest <=0 and the active Star Fleet(ASF) has no
     * forces which can be recalled.
     */
    public boolean isDefeated()
    {
        // Check if war chest is empty
        if (getWarchest() <= 0) {
            // Check if there is any active forces
            for (Force force : allForces) {
                if (force.getState() == ForceState.ACTIVE) {
                    // Return false if there is an active force
                    return false;
                }
            }
            // Return true if war chest is empty and no active force
            return true;
        } else {
            // Return false if war chest is not empty
            return false;
        }
    }


    /** returns the number of bit coins in the war chest
     * @returns the number of bit coins in the war chest
     */
    public int getWarchest()
    {
        return warchest;
    }

    /** Returns a list of all forces in the system by listing :
     * All forces in the Active Star Fleet(ASF), or "No forces in ASF")
     * All forces remaining in the UFF dock, or "No forces in UFF dock
     * All forces destroyed as a result of losing a battle, or "No destroyed forces"
     */
    public String getAllForces()
    {
        String s = "\n***** All Forces *****\n";
        s += getASFleet() + "\n";
        s += getForcesInDock() + "\n";
        s += getDestroyedForces() + "\n";
        return s;
    }

    /**Returns true if force is in the United Forces Fleet(UFF), else false
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFDock(String ref)
    {
        // Check if the force with the given reference code is in the docked state
        for (Force force : allForces) {
            if (force.getRef().equals(ref)) {
                return true;
            }
        }
        return false;
    }

    /**Returns a String representation of all forces in the United Forces Fleet(UFF) dock.
     * Does not include destroyed forces
     * @return a String representation of all forces in the United Forces Fleet(UFF) dock.
     **/
    public String getForcesInDock()
    {
        String s = "\n\n***** Forces available in UFFleet Dock ***\n";
        boolean foundDockedForces = false;
        for (Force force : allForces) {
            if (force.getState() == ForceState.DOCKED && force.getState() != ForceState.DESTROYED) {
                s += force.toString() + "\n";
                foundDockedForces = true;
            }
        }
        if (!foundDockedForces) {
            s += "No forces in UFF dock\n";
        }
        return s;
    }

    /** Returns a list of all destroyed forces in the system
     * @return all destroyed forces
     */
    public String getDestroyedForces()
    {
        String s = "\n*** Destroyed Forces **\n";
        boolean foundDestroyedForces = false;
        for (Force force : allForces) {
            if (force.getState() == ForceState.DESTROYED) {
                s += force.toString() + "\n";
                foundDestroyedForces = true;
            }
        }
        if (!foundDestroyedForces) {
            s += "No forces are currently destroyed.\n";
        }
        return s;
    }

    /** Returns details of the force with the given reference code, or "No such force"
     * @param ref the reference of the force
     * @return details of the force with the given reference code
     **/
    public String getForceDetails(String ref)
    {
        // Loop through the list of forces to find a force with matching reference code
        for (Force force : allForces) {
            if (force.getRef().equalsIgnoreCase(ref)) {
                // If force details are found, return the details as a string
                return "Force details for reference code " + ref + ":\n" + force.toString();
            }
        }

        // If no force details are found, return "No such force"
        return "No such force";
    }

    // ****** Active Star Fleet Forces *********
    /** Allows a force to be activated into the Active Star Fleet(ASF), but
     * only if there are enough resources for the activation fee.The force's
     * state is set to "active"
     * @param ref represents the reference code of the force
     * @return 0 if force is activated, 1 if force is not in the UFF dock or is destroyed
     * 2 if not enough money, -1 if no such force
     **/
    public int activateForce(String ref)
    {
        boolean found = false;
        for (Force force : allForces) { // use Fleet.getFleet() to get all forces in the fleet
            if (force.getRef().equals(ref)) {
                if (force.getState() == ForceState.ACTIVE || force.getState() == ForceState.DESTROYED) { // check if force is docked or destroyed
                    System.out.println("Force with reference " + ref + " is already Active or destroyed.");
                    return 1; // Return 1 to indicate force already active/destroyed
                } else {
                    if (getWarchest() >= force.getFee()) {
                        found = true;
                        // Deduct the fee from the warchest
                        updateWarchest(-force.getFee()); // Call private updateWarchest() method
                        // Set activeStatus to true
                        force.changeState(ForceState.ACTIVE);
                        break;
                    } else {
                        System.out.println("Not enough funds in the warchest to activate Force with reference " + ref + ".");
                        return 2; // Return 2 to indicate insufficient funds
                    }
                }
            }
        }
        if (found) {
            System.out.println("Force with reference " + ref + " activated.");
            return 0; // Return 0 to indicate success
        } else {
            System.out.println("Force with reference " + ref + " not found.");
            return -1; // Return -1 to indicate force not found
        }
    }




    /** Returns true if the force with the reference code is in
     * the Active Star Fleet(ASF), false otherwise.
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref)
    {
        for (Force force : allForces) {
            if (force.getRef().equals(ref) && force.getState() == ForceState.ACTIVE) {
                return true;
            }
        }
        return false;
    }


    /**Returns a String representation of the forces in the active
     * Star Fleet(ASF), or the message "No forces activated"
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet()
    {
        String s = "\n\n*** Forces in the Active Star Fleet ***\n";
        boolean foundActiveForces = false;
        for (Force force : allForces) {
            if (force.getState() == ForceState.ACTIVE)  {
                s += force.toString() + "\n";
                foundActiveForces = true;
            }
        }
        if (!foundActiveForces) {
            s += "Currently no forces in the active star fleet.\n";
        }

        return s;
    }

    /** Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only
     * if it is in the Active Star Fleet(ASF)
     * @param ref is the reference code of the force
     **/
    public void recallForce(String ref)
    {
        boolean found = false;
        // Check if the force with the given reference code is state active
        for (Force force : allForces) {
            if (force.getRef().equalsIgnoreCase(ref)) {
                if (force.getState() == ForceState.ACTIVE) {
                    force.changeState(ForceState.DOCKED);
                    int fee = force.getFee() / 2; // calculate the fee for recalling the force
                    warchest += fee; // add half of the activation fee to the war chest
                    // Print a message indicating the force has been recalled and the fee added to the war chest
                    System.out.println("Force with reference code " + ref + " has been recalled to the UFF dock. "
                            + "Half of its activation fee (" + fee + " credits) has been added to the war chest.");
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            System.out.println("Force with reference code " + ref + " recalled.");
        } else {
            System.out.println("Force with reference code " + ref + " is not in the Active Star Fleet(ASF). Cannot recall..");
        }
    }

//*******Battles********
    /** returns true if the number represents a battle
     * @param num is the number of the required battle
     * @returns true if the number represents a battle
     **/
    public boolean isBattle(int num)
    {
        for (Battle battle : allBattles) {
            if (battle.getBattleNo() == num) {
                return true; // If num is found in battleList, return true
            }
        }
        return false; // If num is not found in battleList, return false
    }


    /** Provides a String representation of a battle given by
     * the battle number
     * @param num the number of the battle
     * @return returns a String representation of a battle given by
     * the battle number
     **/
    public String getBattle(int num)
    {
        for (Battle battle : allBattles) {
            if (battle.getBattleNo() == num) {
                return battle.toString(); // calls toString() from the battles class
            }
        }
        return "No such battle";
    }

    /** Provides a String representation of all battles
     * @return returns a String representation of all battles
     **/
    public String getAllBattles()
    {
        String s = "\n***** All Battles *****\n";

        for (Battle battle : allBattles) {
            s += battle.toString() + "\n"; // Append the string representation of each battle to s
        }

        return s;
    }


    /** Retrieves the battle represented by the battle number.Finds
     * a force from the Active Star Fleet which can engage in the battle.The
     * results of battle will be one of the following:
     * 0 - Battle won, battle gains added to the war chest,
     * 1 - Battle lost as no suitable force available, battle losses
     * deducted from war chest
     * 2 - Battle lost on battle strength , battle losses
     * deducted from war chest and force destroyed
     * 3 - If a battle is lost and admiral completely defeated (no resources and
     * no forces to recall)
     * -1 - no such battle
     * @param battleNo is the number of the battle
     * @return an int showing the result of the battle (see above)
     */
    public int doBattle(int battleNo)
    {
        Battle battle = null;
        for (Battle b : allBattles) {
            if (b.getBattleNo() == battleNo) {
                battle = b;
                break;
            }
        }
        if (battle == null) {
            return 999;
        }

        // this is the fighting conditions so that certain forces cant fight in certain battles
        Force force = null;
        for (Force f : allForces) {
            if (f.getState() == ForceState.ACTIVE) {
                if (f instanceof Wing && (battle.getBattleType() == BattleType.SKIRMISH || battle.getBattleType() == BattleType.AMBUSH)) {
                    force = f;
                    break;
                } else if (f instanceof Starship && battle.getBattleType() != BattleType.AMBUSH) {
                    force = f;
                    break;
                } else if (f instanceof Warbird && battle.getBattleType() == BattleType.AMBUSH && ((Warbird) f).isCloaking()) {
                    force = f;
                    break;
                } else if (f instanceof Warbird && battle.getBattleType() == BattleType.FIGHT) {
                    force = f;
                    break;
                }

            }
        }
        // if a valid force is given it will calculate the outcome on 3 conditions given before 0,1,2
        if (force != null) {
            int battleResult = Force.BattleResult(battle, force.getStrength());
            if (battleResult == 0) {
                int gains = battle.getGains();
                updateWarchest(gains);
            } else if (battleResult == 1 || battleResult == 2) {
                int losses = battle.getLosses();
                Warchestlosses(losses);

                if (battleResult == 2) {
                    String ref = force.getRef();
                    force.changeState(ForceState.DESTROYED); // Change force state to DESTROYED
                    System.out.println(force.getRef() + " has been destroyed.");
                }
            }


            return battleResult;
        } else {
            int battleResult = 1;
            int losses = battle.getLosses();
            Warchestlosses(losses);

            // Check if the player is defeated
            if (isDefeated()) {
                battleResult = 3;
                System.out.println("Defeated");

            }

            return battleResult;
            // Call nullForce and return its result
        }
    }

    //***************************
    /** Sets up forces */
    private void setupForces(){
        // Add forces to the forces ArrayList
        allForces.add(new Wing("IW1", "Twister", 200, 200, 10));
        allForces.add(new Starship("SS2", "Enterprise",200, 300,10,20));
        allForces.add(new Warbird("WB3", "Droop", 100, 300, false));
        allForces.add(new Wing("IW4", "Winger", 200, 200, 20));
        allForces.add(new Warbird("WB5", "Hang", 300, 400, true));
        allForces.add(new Starship("SS6", "Voyager", 450, 450, 15, 10));
        allForces.add(new Starship("SS7", "Explorer", 120, 120, 4, 5));
        allForces.add(new Warbird("WB9", "Hover", 300, 300, false));
        allForces.add(new Wing("IW10", "Flyer", 200, 200, 5));
    }

    /** Sets up battles */
    private void setupBattles()
    {
        allBattles.add(new Battle(1, BattleType.FIGHT, "Borg", 200, 300, 100));
        allBattles.add(new Battle(2, BattleType.SKIRMISH, "Kardassians", 700, 200, 120));
        allBattles.add(new Battle(3, BattleType.AMBUSH, "Ferengi", 100, 400, 150));
        allBattles.add(new Battle(4, BattleType.FIGHT, "Ewoks", 600, 600, 200));
        allBattles.add(new Battle(5, BattleType.AMBUSH, "Borg", 500, 400, 90));
        allBattles.add(new Battle(6, BattleType.SKIRMISH, "Groaners", 150, 100, 100));
        allBattles.add(new Battle(7, BattleType.FIGHT, "Borg", 150, 500, 300));
        allBattles.add(new Battle(8, BattleType.AMBUSH, "Wailers", 300, 300, 300));
    }

    //*********Add your own private methods here ********

    /** Returns the admiral
     * @return admiral
     */
    private String getAdmiral()
    {
        return Admiral;
    }

    /** Sets the war chest
     * @param warchest represents the warchest
     */
    private void setWarchest(int warchest)
    {
        this.warchest = warchest;
    }

    /** Deducts the value from warchest
     * @param value represents the value
     */
    private void Warchestlosses(int value)
    {
        setWarchest(getWarchest() - value);
    }

    /** Updates the warchest
     * @param value represents the value
     */
    private void updateWarchest(int value)
    {
        setWarchest(getWarchest() + value);
    }
    //***************************

    //These methods are not needed until Task 3.5. Uncomment them to complete task 3.5
    // *****   file write/read  *******

    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame (String fname)
    {   // uses object serialisation
        try {
            FileOutputStream file = new FileOutputStream(fname);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this.Admiral);
            out.close();
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.out.println(e);
        }

        try {
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this); // writes the current object (the game) to the file
            oos.close();
            fos.close();
            System.out.println("Game saved successfully to " + fname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /** reads all information about the game from the specified file
     * and returns a SpaceWars object
     * @param fname name of file storing the game
     * @return the game (as a SpaceWars object)
     */
    public SpaceWars restoreGame(String fname)
    {
        try {
            FileInputStream fileIn = new FileInputStream(fname);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            Object obj = in.readObject();
            if (obj instanceof SpaceWars) {
                return (SpaceWars) obj;
            } else {
                throw new ClassCastException("Object read from file is not of type SpaceWars");
            }
            // SpaceWars game = (SpaceWars) in.readObject();
            // in.close();
            // fileIn.close();
            // return game;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("SpaceWars class not found");
            c.printStackTrace();
            return null;
        }



        catch (ClassCastException e) {
            // Use a logging framework to log the exception information
            e.printStackTrace();
            return null;
        }

        catch (NullPointerException e) {
            // Use a logging framework to log the exception information
            e.printStackTrace();
            return null;
        }
    }


    /** Reads information about battles from the specified file into the appropriate collection
     * @param "the" name of the file
     */
    private void readBattles(String fname)
    {
        try {
            Scanner scanner = new Scanner(new File(fname));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                int battleNo = Integer.parseInt(fields[0]);
                BattleType type = BattleType.valueOf(fields[1]);
                String enemy = fields[2];
                int enemyStrength = Integer.parseInt(fields[3]);
                int losses = Integer.parseInt(fields[4]);
                int gains = Integer.parseInt(fields[5]);
                Battle battle = new Battle(battleNo, type, enemy, enemyStrength, losses, gains);
                allBattles.add(battle);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fname);
        } catch (Exception e) {
            System.out.println("Error reading battles from file: " + e.getMessage());
        }
    }
}