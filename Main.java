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
    String file1 = "Current_User_Accounts_File.txt";
    String file2 = "Available_Tickets_File.txt";
    String file3 = "Daily_Transaction_File.txt";
        
        new Output().fileReader(file1, file2, file3);
    }     
}