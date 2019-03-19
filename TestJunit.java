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
   public void testBuy(){
       user_file = new ArrayList<>();
       tickets_file = new ArrayList<>();

       user_file.add("SellerJonesSS   SS 012312.00");
       user_file.add("BuyerJonesBS    BS 190000.00");


       tickets_file.add("SQALabsTime               SellerJonesSS   040 110.00");

       List<String> init_tickets_file = new ArrayList<>(tickets_file);

       th.buyTickets("04 SQALabsTime               SellerJonesSS   036 110.00",
                     "00 BuyerJonesBS    BS 190000.00",
                     user_file,
                     tickets_file,
                     init_tickets_file);

       assertEquals(true, (tickets_file.contains("SQALabsTime               SellerJonesSS   036 110.00") &&
                           user_file.contains("BuyerJonesBS    BS 189560.00") &&
                           user_file.contains("SellerJonesSS   SS 012752.00")));
       tickets_file.clear();
       user_file.clear();

       tickets_file.add("SQALabsTime               SellerJonesSS   003 110.00");
       user_file.add("SellerJonesSS   SS 012312.00");
       user_file.add("BuyerJonesBS    BS 190000.00");


       th.buyTickets("04 SQALabsTimesssssss        SellerJonesSS   036 110.00",
                     "00 BuyerJonesBS    BS 190000.00",
                     user_file,
                     tickets_file,
                     init_tickets_file);

       assertEquals(true, (tickets_file.contains("SQALabsTime               SellerJonesSS   003 110.00") &&
                          user_file.contains("SellerJonesSS   SS 012312.00") &&
                          user_file.contains("BuyerJonesBS    BS 190000.00")));

       th.buyTickets("04 SQALabsTime               SellerJonesSS   004 110.00",
                "00 BuyerJonesBS    BS 190000.00",
                user_file,
                tickets_file,
                init_tickets_file);

       assertEquals(true, (tickets_file.contains("SQALabsTime               SellerJonesSS   003 110.00") &&
                     user_file.contains("SellerJonesSS   SS 012312.00") &&
                     user_file.contains("BuyerJonesBS    BS 190000.00")));

       user_file.remove("SellerJonesSS   SS 012312.00");
       tickets_file.remove("SQALabsTime               SellerJonesSS   003 110.00");

       tickets_file.add("SQALabsTime               SellerJonesSS   040 110.00");

       th.buyTickets("04 SQALabsTime               SellerJonesSS   036 110.00",
                     "00 BuyerJonesBS    BS 190000.00",
                     user_file,
                     tickets_file,
                     init_tickets_file);

       assertEquals(true, (tickets_file.contains("SQALabsTime               SellerJonesSS   040 110.00") &&
                          user_file.contains("SellerJonesSS   SS 012312.00") == false &&
                          user_file.contains("BuyerJonesBS    BS 190000.00")));



       user_file.add("SellerJonesSS   SS 000002.00");
       th.buyTickets("04 SQALabsTime               SellerJonesSS   036 110.00",
                    "00 BuyerJonesBS    BS 190000.00",
                    user_file,
                    tickets_file,
                    init_tickets_file);

       assertEquals(true, (tickets_file.contains("SQALabsTime               SellerJonesSS   040 110.00") &&
                         user_file.contains("SellerJonesSS   SS 000002.00") &&
                         user_file.contains("BuyerJonesBS    BS 190000.00")));



  }
}
