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
    * Function checkUser_exist check if the user exists. if the user does already exist
    * it will return true, if user doesnt exist it will return false
    *
    * @param username the name we are checking if it exist
    *
    * @param userLine takes in the useraccount lines and check with it for usernames
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
    *Function checkTicket_quantity checks if the number of tickets are available
    *
    * @param ticketQuantity the amount of tickets the user wants to buy
    *
    * @param buyerline goes through the ticket lines and checks if the number of tickets is available
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

    public String getUser(String username, List<String> user_file){
        for(String line: user_file){
            String line_username = line.substring(0, Math.min(15, line.length()));
            if(username.equals(line_username)){
                return line;
            }
        }
        return null;
    }

    public String getTickets(String event_name, List<String> tickets_file){
        for(String line: tickets_file){
            String line_event_name = line.substring(0, 25);
            if(event_name.equals(line_event_name)){
                return line;
            }
        }
        return null;
    }

    public String padCredit(double amount, String info){
        String format = "%6.2f";

        String credit_padded = String.format(format, amount);

        String pad = "0";

        credit_padded = pad.repeat(9 - credit_padded.length()) + credit_padded;

        String new_info = info.substring(0, 19);

        return new_info + credit_padded;

    }
}
