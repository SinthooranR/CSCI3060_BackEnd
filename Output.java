import java.io.*;
import java.util.*;

public class Output{

String line = "";
List<String> daily_trans = new ArrayList<String>();
List<String> user_file = new ArrayList<String>();
List<String> tickets_file = new ArrayList<String>();
    
    public Output(){

    }
    /*
    * Function that can read the User, Tickets, and Daily_Transaction Files
    * @param userFile This string should include
    *                 the name of the Old Current Users 
    *                 Account File.
    * @param ticketFile This string should include
    *                   the name of the Old Available
    *                   Tickets File.
    * @param dailyTransactionFile This string should include
    *                             the name of the Old Merged Daily
    *                             Transaction File.
    *
    */

    public void fileReader(String userFile, String ticketsFile, String dailyTransactionFile){
    try{
        //File Readers for each file respectively
        //FileReader for userFile
        File file = new File(userFile);
        FileReader fReader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(fReader);
        //FileReader for ticketsFile
        File file2 = new File(ticketsFile);
        FileReader fReader2 = new FileReader(file2);
        BufferedReader bReader2 = new BufferedReader(fReader2);
        //FileReader for dailyTransactionFile
        File file3 = new File(dailyTransactionFile);
        FileReader fReader3 = new FileReader(file3);
        BufferedReader bReader3 = new BufferedReader(fReader3);
       
        //Reads Current_User_Accounts_File
        while ((line = bReader.readLine()) != null){
            //Has the substrings for Users
            String usernames = line.substring(0,15); 
            user_file.add(line);
            System.out.println(line);
        } 
        bReader.close();

        //Reads Available_Tickets_File
        while ((line = bReader2.readLine()) != null){
            //Has the substrings for Event names 
            String eventnames = line.substring(0,25); 
            tickets_file.add(line);
            System.out.println(line);
        }
        bReader2.close();

        //Reads Merged Daily Transaction File
        while ((line = bReader3.readLine()) != null){
            //Still Needs Work
            daily_trans.add(line);
            System.out.println(line);
        }
        bReader3.close();
    }
    catch(Exception e){
        }
    }

    /*
    * Function can take in an arraylist with all the inputs
    *
    */
    public void createUser(){

    }

    /*
    *
    *
    */
    public void sellTickets(){

    }

    /*
    *
    *
    */
    public void addCredit(){

    }

    /*
    *
    *
    */
    public void deleteUser(){

    }

    /*
    *
    *
    */
    public void refundUser(){

    }

    public void buyTickets(){

    }

}
