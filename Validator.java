import java.util.*;

/**
* The Input Class is used for Grabbing the data from the Output class
* and run validation tests to see if the Users exists, the ticket quantities
* have been updated, and if the User's credit has been updated so that
* if they run the same transactions again those values would be updated.
*
* @author  Sinthooran Ravinathan
* @author  Samuel Pilkey
* @author  Hamza Naseer
* @version 1.0
* @since   2019-03-04
*/

public class Validator{

    public Validator(){

    }

    /**
    * Function checkUser_exist check if the user exists. If the user does already exist
    * it will return true, if user doesnt exist it will return false
    *
    * @param userLine inputs the user from the daily transaction and grabs the specific 
    *                 specific substring for just the username   
    *
    * @param user_file uses the list and checks if the user in the daily transaction 
    *                  file exists it will return true
    */
    public boolean checkUser_exist(String userLine, List<String> user_file){
        String username = userLine.substring(0,15);
        for(String line: user_file){
            String line_username = line.substring(0, Math.min(15, line.length()));
            if(username.equals(line_username)){
                return true;
            }
        }
        return false;
    }

    /**
    * Function checkTicket_exists check if the user exists. If the ticket does already exist
    * it will return true, if tivket doesnt exist it will return false
    *
    * @param ticketLine inputs the ticket from the daily transaction and grabs the specific 
    *                   specific substring for just the eventname 
    *
    * @param tickets_file uses the list and checks if the ticket in the daily transaction 
    *                     file exists it will return true
    */
    public boolean checkTicket_exists(String ticketLine, List<String> tickets_file){

        for(String line: tickets_file){
            String event_name = line.substring(0, Math.min(25, line.length()));
            if(ticketLine.substring(0, Math.min(25, line.length())).equals(event_name)){
                return true;
            }
        }
        return false;
    }

    /**
    * Function getUser gets the user from the list and places all the details
    *
    * @param username holds a line from the transaction file with the username
    *
    * @param user_file used to see if the username in the transaction is the same as the 
    *                  username in the users file 
    */
    public String getUser(String username, List<String> user_file){
        for(String line: user_file){
            String line_username = line.substring(0, Math.min(15, line.length()));
            if(username.equals(line_username)){
                return line;
            }
        }
        return null;
    }

    /**
    * Function getTickets gets the tickets from the list and places all the details
    *
    * @param event_name holds a line from the transaction file with the eventname
    *
    * @param user_file used to see if the eventname in the transaction is the same as the 
    *                  eventname in the tickets file 
    */
    public String getTickets(String event_name, List<String> tickets_file){
        for(String line: tickets_file){
            String line_event_name = line.substring(0, 25);
            if(event_name.equals(line_event_name)){
                return line;
            }
        }
        return null;
    }

    /**
    * Function padCredit grabs the new credit and information and formats it properly
    *
    * @param amount gets updated version of credit after transactions take place
    *
    * @param info gets updated information for user and ticket after transaction take place
    */
    public String padCredit(double amount, String info){
        String format = "%6.2f";

        String credit_padded = String.format(format, amount);

        String pad = "0";

        credit_padded = pad.repeat(9 - credit_padded.length()) + credit_padded;

        String new_info = info.substring(0, 19);

        return new_info + credit_padded;
    }
}
