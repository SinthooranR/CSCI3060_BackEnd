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

    /** 
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

   /** 
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

    /**
    * Function takes in a line from the daily transaction file to create user
    *
    * @param addLine is a line from the daily transaction file
    *                it has all the information to create a new user.
    */
    public void createUser(String addLine){
        String newLine;
        // get the user information
        String code = addLine.substring(0,2);
        String username = addLine.substring(2,15);
        String type = addLine.substring(20,2);
        String amount = addLine.substring(29,9);

        newLine = username + type + amount;
    }

    /** 
    * Function takes in a line from the daily transaction file to create an event
    *
    * @param sellLine is a line from the daily transaction file
    *                it has all the information to create events.
    */
    public void sellTickets(String sellLine){
        String newLine;
        // Gets the Event Information Inputted by the Seller
        String sellCode = sellLine.substring(0,2);
        String eventname = sellLine.substring(3,28);
        String sellername = sellLine.substring(29,44);
        String tickets = sellLine.substring(45,48);
        String price = sellLine.substring(49,55);

        newLine = eventname + sellername + tickets + price;
    }

    /** 
    * Function takes in the current user line and the amount to add line 
    * adds the amount to the current user.
    *
    * @param addLine this string is the line from the daily transaction file
    *                breaks the line to get the amount to add. 
    *
    * @param currentLine this string takes in thr line from the current user file
    *                    it gets the usernameme, code, and amount. 
    */
    public void addCredit(String addLine, String currentline){
        double newAmount;
        String newString;

        // get the amount to add
        String addCode = addLine.substring(0,2);
        String addUsername = addLine.substring(2,15);
        double addAmount = Double.parseDouble(addLine.substring(39,9));

        // get the current users amount
        String usernameCode = currentline.substring(0,19);
        double cuAmount = Double.parseDouble(currentline.substring(20,9));
        // new amount to add to user account file
        newAmount = addAmount + cuAmount;
        // new stirng to replace in file
        newString = usernameCode + Double.toString(newAmount);
    }

    /** 
    * Function takes in the current user line and deletes that User
    * from the System.
    *
    * @param deleteLine this string grabs the line from the daily transaction file
    *                then gets the substrings for the respective attributes
    *
    * @param currentline this string takes in thr line from the current user file
    *                    and gets substrings for respective attributes 
    */ 
    public void deleteUser(String deleteLine, String currentLine){
        //String used to Update the deleted User in the Current Users File
        String updateUser = " ";
        //String used to create the new line with details to be replaced
        String newLine;

        // Gets the information from Daily Transaction File
        String deleteCode = deleteLine.substring(0,2);
        String user = deleteLine.substring(3,18);
        String account = deleteLine.substring(19,21);
        String credit = deleteLine.substring(22,31);

        // Gets the information from Current User File
        String username = deleteLine.substring(3,18);
        String accountType = deleteLine.substring(19,21);
        String creditAmount = deleteLine.substring(22,31);

        //Updates the String
        newLine = username + accountType + creditAmount;
        newLine.replace(newLine, updateUser);  
    }

    /** 
    * Function takes in the refind line from the daily transaction file
    * then it takes the lines from the user file and gives credit to the 
    * buyer and takes away credits from the seller
    *
    * @param refundLine takes in the line needed to be refunded from the transaction file
    *                   and gives the buyer username and seller name and amount
    *
    * @param userLine takes in the buyer and seller information
    *                 gets the amount of credits they have and refunds the buyer
    *                 whole taking money back from the seller    
    */
    public void refundUser(String refundLine, String userLine){
        
        String newBuyerString; 
        String newSellerString;
        String sellerName = refundLine.substring(4,16);
        String buyerName = refundLine.substring(20,12);
        Double refundAmount = Double.parseDouble(refundLine.substring(36,9));

        if (buyerName == userLine.substring(0,15))
        {
            String buyerinfo = userLine.substring(0,19);
            double buyerAmount = Double.parseDouble(userLine.substring(20,9));

            buyerAmount += refundAmount;
            newBuyerString = buyerinfo +  Double.toString(buyerAmount);
        }
        else if (sellerName == userLine.substring(0,15))
        {
            String sellerinfo = userLine.substring(0,19);
            double sellerAmount = Double.parseDouble(userLine.substring(20,9));

            sellerAmount -= refundAmount;

            newSellerString = sellerinfo + Double.toString(sellerAmount);
        }
    }

    /** 
    * Function takes in the buying ticket line from the file
    * then checks if the number of tickets a available.
    * then checks if the user has enough credit to buy the tickets
    *
    * @param ticketLine gets the line where it tells the function which 
    *                   user wants to buy and how much tickets
    *
    * @param userLine gets the userline that would give the user information
    *                 checks username and if the user has enough credits.
    */
    public void buyTickets(String ticketLine, String userLine){
    }

}
