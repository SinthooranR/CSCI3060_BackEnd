/**
* The Main Class is used as the driver code so it puts the file names for
* the File Reader in the Output class to run.
*
* @author  Sinthooran Ravinathan
* @author  Samuel Pilkey
* @author  Hamza Naseer
* @version 1.0
* @since   2019-03-04
*/

public class Main{
    //A Test to check if the File I/O is running properly
    public static void main(String[] args){
    String oldUser = "Current_User_Accounts_File.txt";
    String oldTickets = "Available_Tickets_File.txt";
    String dailyTrans = "Daily_Transaction_File.txt";
    String newUser = "New_User_Accounts_File.txt";
    String newTickets = "New_Available_Tickets_File.txt";
    Output out = new Output();

    //Calls File Reader Function
    out.fileReader(oldUser, oldTickets, dailyTrans);
    out.processAllTrans();
    out.fileWriter(newUser, newTickets);
    }
}
