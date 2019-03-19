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

   @Test
   public void testRefund(){
    user_file = new ArrayList<String>();

    user_file.add("SellerJonesSS   SS 012312.00");
    user_file.add("BobJonesNOADMIN FS 012312.00");

    th.refundUser("05 BobJonesNOADMIN SellerJonesSS  000010.00", user_file);
    th.refundUser("05 BobJoneJAJAJAJA SellerJonesSS  000010.00", user_file);
    th.refundUser("05 BobJonesNOADMIN SellerJonesSS  999999.99", user_file);

    //Successful Test Case
    assertEquals(true, user_file.contains("BobJonesNOADMIN FS 012322.00") && user_file.contains("SellerJonesSS   SS 012302.00"));
    //The User for Refund is Non-Existing
    assertEquals(false, user_file.contains("BobJoneJAJAJAJA FS 012312.00") && user_file.contains("SellerJonesSS   SS 012312.00"));
    //Refund causes User Credit to Exceed Maximum
    assertEquals(false, user_file.contains("BobJonesNOADMIN FS 012312.00") && user_file.contains("SellerJonesSS   SS 012312.00"));

   }
   @Test
   public void testAddCredit(){
    user_file = new ArrayList<String>();

    user_file.add("SellerJonesAA   AA 100000.00");

    th.addCredit("06 SellerJonesAA   AA 000100.00", user_file);
    th.addCredit("06 BobJonesSQA     AA 000100.00", user_file);
    th.addCredit("06 SellerJonesAA   AA 999999.99", user_file);

    //Successful Test Case
    assertEquals(true, user_file.contains("SellerJonesAA   AA 100100.00"));
    //The User for AddCredit is Non-Existing
    assertEquals(false, user_file.contains("BobJonesSQA     AA 000100.00"));
    //AddCredit causes User Credit to Exceed Maximum
    assertEquals(false, user_file.contains("SellerJonesAA   AA 100000.00"));

   }

}
