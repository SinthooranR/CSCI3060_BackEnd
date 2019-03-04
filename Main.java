import java.util.*;


public class Main{



private Output output = new Output();    

    //A Test to check if the File I/O is running properly
    public static void main(String[] args){ 
        System.out.print("Enter the Name of a Txt File (include .txt): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        new Output().fileReader(input);
        scanner.close();
    }

}