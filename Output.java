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

import jdk.jfr.StackTrace;

public class Output{

String line = "";
List<String> daily_trans = new ArrayList<String>();
List<String> user_file = new ArrayList<String>();
List<String> tickets_file = new ArrayList<String>();
List<String> init_tickets_file;

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
    */
    public void fileReader(String userFile, String ticketsFile, String dailyTransactionFile) {
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
              user_file.add(line);
          }
          bufferedReader.close();

          //Reads Available_Tickets_File
          while ((line = bufferedReader2.readLine()) != null){
              tickets_file.add(line);
          }
          bufferedReader2.close();

          //Reads Merged Daily Transaction File
          while ((line = bufferedReader3.readLine()) != null){
              daily_trans.add(line);
          }
          bufferedReader3.close();
      }
      catch(Exception e){
          e.printStackTrace();
          }
    }

     /**
     * Function that can write the New User, Tickets, and Daily_Transaction Files
     * @param user_filename This string will grab the data
     *                      to update the New User
     *                      Accounts File
     * @param tickets_filename This string will grab the data
     *                         to update the New Available
     *                         Tickets File.
     */
     public void fileWriter(String user_filename, String tickets_filename){
      File file = new File(user_filename);
      File file2 = new File(tickets_filename);
         try{
             //Checks if New User File Exists. If not it will generate it, and then write to it.
             if (!file.exists()){
                  file.createNewFile();
             }
             FileWriter writer = new FileWriter(file);
             for(String user: user_file) {
             writer.write(user);
             writer.write("\n");
            }
             writer.close();

            //Checks if New Tickets File Exists. If not it will generate it, and then write to it.
            if (!file2.exists()){
                 file2.createNewFile();
             }
             FileWriter writer2 = new FileWriter(file2);
             for(String tickets: tickets_file) {
             writer2.write(tickets);
             writer2.write("\n");
            }
             writer2.close();
         }
         catch(Exception e){
         }
     }

    /**
    * Function that can write the New User, Tickets, and Daily_Transaction Files
    * @param curr_session reads through daily transaction file and runs transaction
    *                     based off the order and the transaction id
    *
    * @param curr_user_logout used whenever a new transaction with a new user takes place
    *                         and logs the old user out and new user begins transaction
    */
    public void proccessOnceSessionTrans(List<String> curr_session, String curr_user_logout){

      for (String trans: curr_session){

        String code = trans.substring(0, Math.min(trans.length(), 2));

        switch (code){
          case "01":
            transactionhandler.createUser(trans, user_file);
            break;

          case "02":
            transactionhandler.deleteUser(trans, user_file, tickets_file);
            break;

          case "03":
            transactionhandler.sellTickets(trans, tickets_file, user_file);
            break;

          case "04":
              transactionhandler.buyTickets(trans, curr_user_logout, user_file, tickets_file, init_tickets_file);
              break;

          case "05":
              transactionhandler.refundUser(trans, user_file);
              break;

          case "06":
              transactionhandler.addCredit(trans, user_file);
              break;
            default:
              break;
            }
        }
    }

    public void processAllTrans(){

      List<String> curr_session = new ArrayList<String>();
      init_tickets_file = new ArrayList<String>(tickets_file);

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
