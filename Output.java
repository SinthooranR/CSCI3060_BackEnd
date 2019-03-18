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
              user_file.add(line);
              //System.out.println(line);
          }
          bufferedReader.close();

          //Reads Available_Tickets_File
          while ((line = bufferedReader2.readLine()) != null){
              tickets_file.add(line);
              //System.out.println(line);
          }
          bufferedReader2.close();

          //Reads Merged Daily Transaction File
          while ((line = bufferedReader3.readLine()) != null){
              daily_trans.add(line);
              //System.out.println(line);
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
     public void fileWriter(){
      File file = new File("New_User_Accounts_File.txt");
      File file2 = new File("New_Avaiable_Tickets_File.txt");
      File file3 = new File("New_Daily_Transaction_File");
         try{
         //FileWriter for new userFile

        //  File file = new File("New_User_Accounts_File.txt");
        //  FileWriter fileWriter = new FileWriter(file);
        //  BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

         //FileReader for new ticketsFile
        //  File file2 = new File("New_Avaiable_Tickets_File.txt");
        //  FileWriter fileWriter2 = new FileWriter(file2);
        //  BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);

         //FileReader for new dailyTransactionFile
        //  File file3 = new File("New_Daily_Transaction_File");
        //  FileWriter fileWriter3 = new FileWriter(file3);
        //  BufferedWriter bufferedWriter3 = new BufferedWriter(fileWriter3);
             
        //Checks if New User File Exists. If not it will generate it, and then write to it.
             if(!file.exists()){
                 file.createNewFile();
             }
             FileWriter writer = new FileWriter(file); 
             for(String user: user_file) {
             writer.write(user);
             writer.write("\n");
            }
             writer.close();

             //Checks if New Tickets File Exists. If not it will generate it, and then write to it.
              if(!file2.exists()){
                 file2.createNewFile();
             }
             FileWriter writer2 = new FileWriter(file2); 
             for(String tickets: tickets_file) {
             writer2.write(tickets);
             writer2.write("\n");
            }
             writer2.close();

             //Checks if New Daily Transaction File Exists. If not it will generate it, and then write to it.
              if(!file3.exists()){
                 file3.createNewFile();
             }
             FileWriter writer3 = new FileWriter(file3); 
             for(String transaction: daily_trans) {
             writer3.write(transaction);
             writer3.write("\n");
            }
             writer3.close();
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
            transactionhandler.deleteUser(trans, user_file, tickets_file);
            //correct for now
            break;

          case "03":
          // check seller exists
            transactionhandler.sellTickets(trans, tickets_file, user_file);

            break;

          case "04":

              transactionhandler.buyTickets(trans, curr_user_logout, user_file, tickets_file, init_tickets_file);
              break;

          case "05":
            // TODO get buyer and seller info to one string
            transactionhandler.refundUser(trans, user_file);
            break;

          case "06":

            // TODO get user information preferably making a function in Input passing the user array List
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
