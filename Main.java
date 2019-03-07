// import java.util.*;


public class Main{




Output output = new Output();    

    //A Test to check if the File I/O is running properly
    public static void main(String[] args){ 
    String file1 = "Current_User_Accounts_File.txt";
    String file2 = "Available_Tickets_File.txt";
    String file3 = "Daily_Transaction_File.txt";
        
        new Output().fileReader(file1, file2, file3);

        //Just a Test to see if FileWriter works
        //Only Writes String Names 
        // new Output().fileWriter(file1,file2, file3);
    }     

}