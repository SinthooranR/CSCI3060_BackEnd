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
    * Function takes in a line from the daily transaction file to create user
    *
    *@Param addLine is a line from the daily transaction file
    *       it has all the information to create a new user.
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
    *
    *
    */
    public void sellTickets(){

    }

    /*
    * Function takes in the current user line and the amount to add line 
    * adds the amount to the current user.
    *
    *  @param addLine this string is the line from the daily transaction file
    *                  breaks the line to get the amount to add. 
    *
    * @Parm currentLine this string takes in thr line from the current user file
    *                    it gets the usernameme, code, and amount. 
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
