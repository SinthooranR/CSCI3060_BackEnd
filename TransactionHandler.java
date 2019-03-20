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
    public void sellTickets(String sellLine, List<String> tickets_file, List<String> user_file) {

        String ticket_to_add = sellLine.substring(3, sellLine.length());

        if (tran_validator.checkTicket_exists(ticket_to_add, tickets_file) == false && tran_validator.getUser(sellLine.substring(29, 44),  user_file) != null){
            tickets_file.add(ticket_to_add);
        } else {
            System.out.println("Error: Cannot sell tickets as these tickets is already being sold");
        }
    }

    /**
    * Function takes in the current user line and the amount to add line
    * adds the amount to the current user.
    *
    * @param addLine this string is the line from the daily transaction file
    *                breaks the line to get the amount to add.
    *
    * @param user_file this string takes in the line from the current user file
    *                  in the form of a list and, it gets the usernameme,
    *                  code, and  credit amount.
    */
    public void addCredit(String addLine, List<String> user_file){
        String userAccount = addLine.substring(3, 18);
        String userInfo = tran_validator.getUser(userAccount, user_file);

        if(userInfo != null){
            Double addCredit = Double.parseDouble(addLine.substring(21,30));
            Double userCredit = Double.parseDouble(userInfo.substring(18,27));

            if(userCredit + addCredit < 999999.99){
                Double newUserCredit = userCredit + addCredit;

                user_file.remove(userInfo);

                String format = "%6.2f";  // width == 6 and 2 digits after the dot

                String user_credit_padded = String.format(format, newUserCredit);

                String pad = "0";

                user_credit_padded = pad.repeat(9 - user_credit_padded.length()) + user_credit_padded;

                String updatedUserCred = userInfo.substring(0,19);
                updatedUserCred += user_credit_padded;

                user_file.add(updatedUserCred);
                //System.out.println(updatedUserCred);
            }
            else{
                System.out.println("Error: User Exceeds Maximum Credit");
            }

        } else {
            System.out.println("Error: Can't add credit");
        }

        //Check if User Exists

        //Check if User does not Exceed Max Credit
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
    public void deleteUser(String deleteLine, List<String> user_file, List<String> tickets_file){
        //String used to Update the deleted User in the Current Users File
        String user_to_delete = deleteLine.substring(3, deleteLine.length());
        String user_name = deleteLine.substring(3,18);
        if(tran_validator.checkUser_exist(user_to_delete, user_file) == true){
            user_file.remove(user_to_delete);


            Iterator<String> it = tickets_file.iterator();

            while(it.hasNext()){
                String line = it.next();
                String seller_name = line.substring(26,41);

                if(user_name.equals(seller_name)){
                    it.remove();
                }
            }

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
    * @param userFile takes in the buyer and seller information in the form of a list and
    *                 gets the amount of credits they have and refunds the buyer
    *                 whole taking money back from the seller
    */
    public void refundUser(String refundLine, List<String> user_file){
        //#TODO
        //Grab substrings for Buyer and Seller
        //System.out.println("Hi beter");
        String buyerAccount = refundLine.substring(3,18);
        String sellerAccount = refundLine.substring(19, 34);
        Double refundCredit = Double.parseDouble(refundLine.substring(35, 43));


        String buyerInfo = tran_validator.getUser(buyerAccount, user_file);
        String sellerInfo = tran_validator.getUser(sellerAccount, user_file);

        //Check if User Exists and Check if Seller Exists

        if(buyerInfo != null && sellerInfo != null){
            double getBuyerCredit = Double.parseDouble(buyerInfo.substring(18,27));
            double getSellerCredit = Double.parseDouble(sellerInfo.substring(18,27));

            //Check if Seller has enough Credit
            if(getBuyerCredit + refundCredit < 999999.99 && getSellerCredit > refundCredit){
                double newSellerCredit = getSellerCredit - refundCredit;
                double newBuyerCredit = getBuyerCredit + refundCredit;
                user_file.remove(buyerInfo);
                user_file.remove(sellerInfo);

                String updatedBuyerCred = tran_validator.padCredit(newBuyerCredit, buyerInfo);
                String updatedSellerCred = tran_validator.padCredit(newSellerCredit, sellerInfo);

                user_file.add(updatedBuyerCred);
                user_file.add(updatedSellerCred);

            } else {
                System.out.println("Seller does not have enough Credit for Refund");
            }


        } else {
            System.out.println("Either Buyer, Seller or Both Do not Exist");
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
    *
    * @param user_file gets the details of the Users and stores it into a List and 
    *                  Updates the User Information Once transaction is complete  
    *
    * @param tickets_file gets the updated
    *
    * @param init_tickets_file 
    */
    public void buyTickets(String ticketLine, String userLine, List<String> user_file, List<String> tickets_file, List<String> init_tickets_file){
        String event_name = ticketLine.substring(3, 28);
        String seller_username = ticketLine.substring(29, 44);

        String ticket_info = ticketLine.substring(3, ticketLine.length());
        String remaining_ticket_info = tran_validator.getTickets(event_name, tickets_file);
        String init_ticket_info = tran_validator.getTickets(event_name, init_tickets_file);

        String buyer_info = tran_validator.getUser(userLine.substring(3, 18), user_file);
        Double buyer_credit = Double.parseDouble(buyer_info.substring(19, 28));

        String seller_info = tran_validator.getUser(seller_username, user_file);

        if (ticket_info != null && remaining_ticket_info != null){

            int tickets_sold = Integer.parseInt(init_ticket_info.substring(42,45)) - Integer.parseInt(ticket_info.substring(42,45));
            int tickets_left = Integer.parseInt(remaining_ticket_info.substring(42,45));
            if (tickets_left > tickets_sold) {
                tickets_left -= tickets_sold;
                if (seller_info != null){

                    double seller_credit = Double.parseDouble(seller_info.substring(19,28));
                    double ticket_price = Double.parseDouble(init_ticket_info.substring(46, 51));
                    double total_price = tickets_sold * ticket_price;


                    if (total_price < buyer_credit && seller_credit + total_price < 999999.99){

                        buyer_credit -= total_price;
                        seller_credit += total_price;

                        String new_buyer_info = tran_validator.padCredit(buyer_credit, buyer_info); // width == 6 and 2 digits after the dot
                        String new_seller_info = tran_validator.padCredit(seller_credit, seller_info); // width == 6 and 2 digits after the dot

                        user_file.remove(buyer_info);
                        user_file.remove(seller_info);

                        user_file.add(new_buyer_info);
                        user_file.add(new_seller_info);

                        //System.out.println(remaining_ticket_info);

                        String ticket_price_string = init_ticket_info.substring(46, 52);
                        String rem_tickets = Integer.toString(tickets_left);

                        String pad = "0";

                        rem_tickets = pad.repeat(3 - rem_tickets.length()) + rem_tickets;

                        String new_ticket_info = init_ticket_info.substring(0,40);

                        new_ticket_info += "  " + rem_tickets + " " + ticket_price_string;

                        tickets_file.remove(remaining_ticket_info);
                        tickets_file.add(new_ticket_info);

                    } else {
                        System.out.println("Error: buyer does not have enough credit remaining or Seller exceeded mac credit");
                    }
                } else {
                    System.out.println("Error: Seller no longer exists in this data set");
                }
            } else {
                System.out.println("Error: Not enough tickets remaining");
            }
        } else {
            System.out.println("Error: Ticket information was not found");
        }
    }





}
