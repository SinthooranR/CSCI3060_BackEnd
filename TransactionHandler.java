import java.util.*;
/**
* The Transaction Class Uses the codes from the Transaction File
* to gather the important information from Current User File
* and Available Ticket File to make changes in the Ticket Service
* by updating necessary information.
*
* @author  Sinthooran Ravinathan
* @author  Samuel Pilkey
* @author  Hamza Naseer
* @version 1.0
* @since   2019-03-04
*/

public class TransactionHandler{
    private
        Validator tran_validator;
    public TransactionHandler(){
        tran_validator = new Validator();
    }

    /**
    * Function takes in a line from the daily transaction file to create user
    *
    * @param addLine is a line from the daily transaction file
    *                it has all the information to create a new user.
    *
    * @param user_file is a
    */
    public void createUser(String addLine, List<String> user_file){

        String user_to_add = addLine.substring(3, addLine.length());

        if (tran_validator.checkUser_exist(user_to_add, user_file) == false){
            user_file.add(user_to_add);
        } else {
            System.out.println("Error: Cannot add user as user already exist");
        }
    }

    /**
    * Function takes in a line from the daily transaction file to create an event
    *
    * @param sellLine is a line from the daily transaction file
    *                it has all the information to create events.
    */
    public void sellTickets(String sellLine, List<String> tickets_file) {

        String ticket_to_add = sellLine.substring(3, sellLine.length());
        if (tran_validator.checkTicket_exists(ticket_to_add, tickets_file) == false){
            tickets_file.add(ticket_to_add);
        } else {
            System.out.println("Error: Cannot sell tickets as these tickets is already being");
        }
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
    public void deleteUser(String deleteLine, List<String> user_file){
        //String used to Update the deleted User in the Current Users File
        String user_to_delete = deleteLine.substring(3, deleteLine.length());

        if(tran_validator.checkUser_exist(user_to_delete, user_file) == true){
            user_file.remove(user_to_delete);
        }else{
            System.out.println("Error: User to be deleted was not found");
        }
        //Updates the String
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
    public void refundUser(String refundLine, String userLine, String sellerLine){

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
    public void buyTickets(String ticketLine, String userLine, String sellerLine){

    }

}
