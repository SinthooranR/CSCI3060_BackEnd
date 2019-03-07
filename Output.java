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
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //FileReader for ticketsFile
        File file2 = new File(ticketsFile);
        FileReader fileReader2 = new FileReader(file2);
        BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
        //FileReader for dailyTransactionFile
        File file3 = new File(dailyTransactionFile);
        FileReader fileReader3 = new FileReader(file3);
        BufferedReader bufferedReader3 = new BufferedReader(fileReader3);
       
        //Reads Current_User_Accounts_File
        while ((line = bufferedReader.readLine()) != null){
            //Has the substrings for Users
            String usernames = line.substring(0,15); 
            user_file.add(line);
            System.out.println(line);
        } 
        bufferedReader.close();

        //Reads Available_Tickets_File
        while ((line = bufferedReader2.readLine()) != null){
            //Has the substrings for Event names 
            String eventnames = line.substring(0,25); 
            tickets_file.add(line);
            System.out.println(line);
        }
        bufferedReader2.close();

        //Reads Merged Daily Transaction File
        while ((line = bufferedReader3.readLine()) != null){
            //Still Needs Work
            daily_trans.add(line);
            System.out.println(line);
        }
        bufferedReader3.close();
       
    }
    catch(Exception e){
        }
    }



    public void fileWriter(String userFile, String ticketsFile, String dailyTransactionFile){
        try{
        File file = new File("New_User_Accounts_File.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        File file2 = new File("New_Avaiable_Tickets_File.txt");
        FileWriter fileWriter2 = new FileWriter(file2);
        BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);

        File file3 = new File("New_Daily_Transaction_File");
        FileWriter fileWriter3 = new FileWriter(file3);
        BufferedWriter bufferedWriter3 = new BufferedWriter(fileWriter3);

            if(!file.exists()){
                file.createNewFile();
            }

            bufferedWriter.write(userFile);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();


             if(!file2.exists()){
                file2.createNewFile();
            }

            bufferedWriter2.write(ticketsFile);
            bufferedWriter2.newLine();
            bufferedWriter2.flush();
            bufferedWriter2.close();



             if(!file3.exists()){
                file3.createNewFile();
            }

            bufferedWriter3.write(dailyTransactionFile);
            bufferedWriter3.newLine();
            bufferedWriter3.flush();
            bufferedWriter3.close();
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
