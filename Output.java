import java.io.*;

public class Output{

    public Output(){

    }


    /*
    * Function that can read the DailyTransaction File line by line
    *
    */

    public void fileReader(String filename){
    try{
        File file = new File(filename);
        FileReader fReader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(fReader);

        String line = "";
        while ((line = bReader.readLine()) != null){
            System.out.println(line);
        }
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
