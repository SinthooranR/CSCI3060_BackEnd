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
    * Function that can read the DailyTransaction File line by line
    *
    */

    public void fileReader(String filename){
    try{
        File file = new File(filename);
        int count = 0;
        FileReader fReader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(fReader);

        while ((line = bReader.readLine()) != null){
            count++; //Checks if the File is reading line by line
            //Still Needs Work
            String usernames = line.substring(3,18); //Has the substrings for Users
            String eventnames = line.substring(3,28); //Has the suibstrings for Event names
            String ticketPrices = line. 
            System.out.println(lines);

        }
        System.out.println("Total Lines in File:" + " " + count); //Shows how many lines exist in the file

        bReader.close();
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
