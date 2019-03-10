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

public class Input{

    public Input(){
    
    }

    /**
    * Function checkUser_exist check if the user exists. if the user does already exist
    * it will return true, if user doesnt exist it will return false
    *
    * @param username the name we are checking if it exist
    *
    * @param userLine takes in the useraccount lines and check with it for usernames  
    */

    public boolean checkUser_exist(String username, String userLine){
        
        if (username ==  userLine.substring(0,15))
        {
            return true;
        }
        else if (username !=  userLine.substring(0,15))
        {
            return false;
        }
        return true;
    }

    /** 
    *Function checkTicket_quantity checks if the number of tickets are available
    *
    * @param ticketQuantity the amount of tickets the user wants to buy
    *
    * @param buyerline goes through the ticket lines and checks if the number of tickets is available  
    */
    public boolean checkTicket_quantity(int ticketQuantity, String buyerline){
        String tickets = buyerline.substring(3,41);
        
        if (ticketQuantity >= Integer.parseInt(tickets)){
            return true;
        }
        else if (ticketQuantity < Integer.parseInt(tickets)){
            return false;
        }
        return true;
    }

    /** 
    *Function checkUser_money checks if the user has enough credit to buy
    *
    * @param credit the amount of credit to check
    *
    * @param userLine goes through the user lines and checks if the number of credit is available  
    */
    public boolean checkUser_credit(double credit, String userLine){
        String credString = userLine.substring(9,20);
        
        if (credit >= Double.parseDouble(credString)){
            return true;
        }
        else if (credit < Double.parseDouble(credString)){
            return false;
        }
        return true;
    }
}