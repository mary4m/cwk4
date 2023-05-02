package cwk4;
import java.io.*;
import java.util.*;

/**
 * Provide a command line user interface
 *
 * @author Team 23
 * @version April 2023
 */
public class GameUI
{
    private Scanner myIn = new Scanner(System.in);

    private void playGame()
    {
        // fields
        int choice;
        String admiralName;
        int result = -1;
        System.out.println("Enter admiral's name");
        String s = myIn.nextLine();
        WIN gp = new SpaceWars(s);
        choice = 100;
        while (choice != 0 )
        {
            choice = getMenuItem();
            if (choice == 1)  //All forces
            {
                System.out.println(gp.getAllForces());
            }
            else if (choice == 2) //List all battles
            {
                System.out.println(gp.getAllBattles());
            }
            else if (choice == 3) //get Force
            {
                System.out.println("Enter Force reference");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                System.out.println(gp.getForceDetails(ref));
            }
            else if (choice == 4) //activate Force
            {
                System.out.println("Enter Force Reference to be Activated");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                int ans = gp.activateForce(ref);
                System.out.println(this.activation(ans));
                System.out.println("New Balance: " + gp.getWarchest() + "\n");

            }
            else if (choice == 5) //List ASFleet
            {
                System.out.println(gp.getASFleet());
            }
            else if (choice == 6) //engage in a battle
            {
                System.out.println("Enter Battle Number to Engage");
                myIn.nextLine();
                int number = Integer.parseInt(myIn.nextLine().trim());
                result = gp.doBattle(number);
                if(result == -1){
                    System.out.print("No such battle exists");
                }
                if(result == 0){
                    System.out.println("Well done! You Won!");
                    System.out.println("Your new balance: " + gp.getWarchest() + "\n");
                }
                if(result == 1){
                    System.out.println("No Suitable Force Available, You Lost the Battle!");
                    System.out.println("Your new balance: " + gp.getWarchest() + "\n");
                }
                if(result == 2){
                    System.out.println("Your Ship Was not Strong Enough and was destroyed!" + "\n" + "You Lose the Battle!");
                    System.out.println("Your new balance: " + gp.getWarchest() + "\n");
                }
                if(result == 3){
                    System.out.println("Your Ship Was not Strong Enough and was destroyed!");
                    System.out.println("You are out of Ships and are Completely Defeated!");
                }
                if(result == 4){
                    System.out.println("Your Strengths were the same, You win the battle!");
                    System.out.println("Your new balance: " + gp.getWarchest() + "\n");
                }
            }

            else if (choice == 7) //recall force
            {
                System.out.println("Enter Force Reference to be Recalled");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                gp.recallForce(ref);

            }

            else if (choice==8) //view game state
            {
                System.out.println(gp.toString());
            }
            // Uncomment after task 3.5

            else if (choice == 9){
                System.out.println("Write to file");
                ((SpaceWars) gp).saveGame("Olenka.txt");
            }
            else if (choice == 10) // Task 3.5 only
            {
                System.out.println("Restore from file");
                ((SpaceWars) gp).restoreGame("Olenka.txt");
                System.out.println(gp.toString());
            }
        }
        System.out.println("Thank-you");
    }

    /** Prints the menu to the screen
     * @return the menu
     */
    private int getMenuItem()
    {
        int choice = 100;
        System.out.println("Main Menu");
        System.out.println("0. Quit");
        System.out.println("1. List all forces");
        System.out.println("2. List all battles");
        System.out.println("3. View details of a force");
        System.out.println("4. Activate a force into the Active Star  fleet");
        System.out.println("5. List forces in Active Star Fleet");
        System.out.println("6. Engage in a battle");
        System.out.println("7. Recall a force");
        System.out.println("8. View the state of the game");
        //For Task 3.5 only
        System.out.println("9. Save this game");
        System.out.println("10. Restore a game");

        while (choice < 0 || choice  > 10)
        {
            System.out.println("Enter the number of your choice");
            choice =  myIn.nextInt();
        }
        return choice;
    }

    /** Returns a suitable message for activation
     * @param code represents the code
     * @return a suitable message for activation
     */
    private String activation(int code)
    {
        switch (code)
        {
            case 0:return "force is activated";
            case 1:return "force is not in the UFFDock";
            case 2:return "not enough money";
            case 3:return "no such force";
            default: return "Error";
        }
    }

    public static void main(String[] args)
    {
        GameUI myGame = new GameUI();
        myGame.playGame();
    }
}