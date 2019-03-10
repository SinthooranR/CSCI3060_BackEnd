/**
* The Output Class is used for Reading the Old_User_Accounts_File,
* Old_Available_Tickets_File, and Old_Merged_Daily_Transaction_File and use that data
* To Update the System with the updates from the transactions to the New_User_Accounts,
* New_Available_Tickets, and New_Merged_Daily_Transaction Files.
*
* @author  Sinthooran Ravinathan
* @author  Samuel Pilkey
* @author  Hamza Naseer
* @version 1.0
* @since   2019-03-04
*/

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

   /*
    * Function that can write the New User, Tickets, and Daily_Transaction Files
    * @param userFile This string will grab the data 
    *                 to update the New User
    *                 Accounts File
    * @param ticketFile This string will grab the data
    *                   to update the New Available
    *                   Tickets File.
    * @param dailyTransactionFile This string will grab the data
    *                             to update the New Merged Daily
    *                             Transaction File.
    *
    */
    public void fileWriter(String userFile, String ticketsFile, String dailyTransactionFile){
        try{
        //FileWriter for new userFile
        File file = new File("New_User_Accounts_File.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        //FileReader for new ticketsFile
        File file2 = new File("New_Avaiable_Tickets_File.txt");
        FileWriter fileWriter2 = new FileWriter(file2);
        BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
        //FileReader for new dailyTransactionFile
        File file3 = new File("New_Daily_Transaction_File");
        FileWriter fileWriter3 = new FileWriter(file3);
        BufferedWriter bufferedWriter3 = new BufferedWriter(fileWriter3);
            //Checks if New User File Exists. If not it will generate it, and then write to it.
            if(!file.exists()){
                file.createNewFile();
            }
            bufferedWriter.write(userFile);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();

            //Checks if New Tickets File Exists. If not it will generate it, and then write to it.
             if(!file2.exists()){
                file2.createNewFile();
            }
            bufferedWriter2.write(ticketsFile);
            bufferedWriter2.newLine();
            bufferedWriter2.flush();
            bufferedWriter2.close();

            //Checks if New Daily Transaction File Exists. If not it will generate it, and then write to it.
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
    * Function takes in a line from the daily transaction file to create user
    *
    * @param addLine is a line from the daily transaction file
    *                it has all the information to create a new user.
    */
    public void createUser(String addLine){
        Stirng newLine;
        // get the user information
        String code = addLine.substring(0,2);
        String username = addLine.substring(2,15);
        String type = addLine.substring(20,2);
        String amount = addLine.substring(29,9);

        newLine = username + type + amount;

    }

     /*
    * Function takes in a line from the daily transaction file to create an event
    *
    * @param addLine is a line from the daily transaction file
    *                it has all the information to create events.
    */
    public void sellTickets(String addLine){
        Stirng newLine;
        // Gets the Event Information Inputted by the Seller
        String sellCode = addLine.substring(0,2);
        String eventname = addLine.substring(3,28);
        String sellername = addLine.substring(29,44);
        String tickets = addLine.substring(45,48);
        String price = addLine.substring(49,55);

        newLine = eventname + sellername + tickets + price;
    }

    /*
    * Function takes in the current user line and the amount to add line 
    * adds the amount to the current user.
    *
    * @param addLine this string is the line from the daily transaction file
    *                 breaks the line to get the amount to add. 
    *
    * @param currentLine this string takes in thr line from the current user file
    *                   it gets the usernameme, code, and amount. 
    */
    public void addCredit(String addLine, String currentline){
        double newAmount;
        String newString;

        // get the amount to add
        String addCode = addLine.substring(0,2);
        String addUsername = addLine.substring(2,15);
        double addAmount = Double.parseDouble(addLine.substring(39,9);

        // get the current users amount
        String usernameCode = currentline.substring(0,19);
        double cuAmount = Double.parseDouble(currentline.substring(20,9);
        // new amount to add to user account file
        newAmount = addAmount + cuAmount;
        // new stirng to replace in file
        newString = usernameCode + Double.toString(newAmount);

    }

    /*
    * Function takes in the current user line and removes that User
    * from the System.
    *
    *  @param getLine this string grabs the line from the daily transaction file
    *                 then gets the substrings for the respective attributes
    *
    *  @param currentLine this string takes in thr line from the current user file
    *                     and gets substrings for respective attributes 
    */
    public void deleteUser(String getLine, String currentline){
        //String used to Update the deleted User in the Current Users File
        String updateUser = " ";
        //String used to create the new line with details to be replaced
        String newLine;

        // Gets the information from Daily Transaction File
        String deleteCode = addLine.substring(0,2);
        String user = addLine.substring(3,18);
        String account = addLine.substring(19,21);
        String credit = addLine.substring(22,31);

        // Gets the information from Current User File
        String username = addLine.substring(3,18);
        String accountType = addLine.substring(19,21);
        String credit = addLine.substring(22,31);

        //Updates the String
        newLine = username + accountType + credit;
        newLine.replace(newLine, updateUser);  

    }

    /*
    *
    *
    */
    public void refundUser(){

    }

    /*
    *
    *
    */
    public void buyTickets(){

    }

}
