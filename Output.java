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

public TransactionHandler transactionhandler = new TransactionHandler();

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

    public void proccessOnceSessionTrans(List<String> curr_session, String curr_user_logout){
      for (String trans: curr_session){

        String code = trans.substring(0, Math.min(trans.length(), 2));

        // TODO need to fix up all the individual command functions and fill in buy
        switch (code){
          case "01":
            transactionhandler.createUser(trans, user_file);
            //correct for now
            break;

          case "02":
            transactionhandler.deleteUser(trans, user_file);
            //correct for now
            break;

          case "03":
            transactionhandler.sellTickets(trans, user_file);

            break;

          case "04":
            // TODO get seller info to be sent as one string
            transactionhandler.buyTickets(trans, curr_user_logout, null);
            break;

          case "05":
            // TODO get buyer and seller info to one string
            transactionhandler.refundUser(trans, null, null);
            break;

          case "06":

            // TODO get user information preferably making a function in Input passing the user array List
            transactionhandler.addCredit(trans, null);
            break;
          default:
            break;
        }
      }
    }

    public void processAllTrans(){

      List<String> curr_session = new ArrayList<String>();


      for (String trans: daily_trans){
        String code = trans.substring(0, Math.min(trans.length(), 2));

        if (code.equals("00")){
          proccessOnceSessionTrans(curr_session, trans);
          curr_session.clear();

        } else {
          curr_session.add(trans);

        }
      }
    }
}
