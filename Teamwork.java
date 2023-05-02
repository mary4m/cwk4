package cwk4; 


/**
 * Details of your team
 * 
 * @author Team 23
 * @version April 2023
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "23";
        
        details[1] = "Abdullaev";
        details[2] = "Samandar";
        details[3] = "21006383";

        details[4] = "Ahad";
        details[5] = "Maryam";
        details[6] = "20038432";

        details[7] = "El Mojahed";
        details[8] = "Yasmine";
        details[9] = "20051518";


        details[10] = "Qureshi";
        details[11] = "Safah";
        details[12] = "20050762";

    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
