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

    public boolean checkUser_exist(String username){
        return true; //Just left this here
    }

    public boolean checkTicket_quantity(int ticketQuantity){
        return true;
    }

    public boolean checkUser_money(double userCredit){
        return true;
    }

 

}