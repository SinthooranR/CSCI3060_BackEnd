import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
public class TestJunit {
TransactionHandler th = new TransactionHandler();
List<String> user_file;
List<String> tickets_file;
   @Test
   // used to test the createUser function
   public void testCreate() {
       user_file = new ArrayList<String>();
       user_file.add("Seller Account1 SS 000000.00");

       th.createUser("01 Seller Account1 SS 000000.00", user_file);
       th.createUser("01 User01122123123 SS 000000.00", user_file);

       assertEquals(1, Collections.frequency(user_file ,"Seller Account1 SS 000000.00"));
       assertEquals(true, user_file.contains("User01122123123 SS 000000.00"));

   }
   @Test
   public void testSell(){
       user_file = new ArrayList<String>();
       tickets_file = new ArrayList<String>();

       user_file.add("SellerJonesSS   SS 012312.00");
       tickets_file.add("SQALabsTime               SellerJonesSS   040 110.00");

       th.sellTickets("04 SQALabsTime               SellerJonesSS   040 110.00",tickets_file, user_file);
       th.sellTickets("04 SQALabsTimessss           SellerJonesSS   040 110.00",tickets_file, user_file);

       assertEquals(1, Collections.frequency(tickets_file, "SQALabsTime               SellerJonesSS   040 110.00"));
       assertEquals(true, tickets_file.contains("SQALabsTimessss           SellerJonesSS   040 110.00"));
   }
}
