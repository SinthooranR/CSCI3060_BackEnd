import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

public class TestJunit {

TransactionHandler th = new TransactionHandler();
List<String> user_file;
List<String> tickets_file;

   @Test
   public void testCreate() {
       user_file = new ArrayList<String>();
       user_file.add("Seller Account1 SS 000000.00");

       th.createUser("01 Seller Account1 SS 000000.00", user_file);
       th.createUser("01 User01122123123 SS 000000.00", user_file);

       assertEquals("Error (create) added user when username already exist",1, Collections.frequency(user_file ,"Seller Account1 SS 000000.00"));
       assertEquals("Error (create) user was not added into array list",true, user_file.contains("User01122123123 SS 000000.00"));

   }

   @Test
   public void testSell(){
       user_file = new ArrayList<String>();
       tickets_file = new ArrayList<String>();

       user_file.add("SellerJonesSS   SS 012312.00");
       tickets_file.add("SQALabsTime               SellerJonesSS   040 110.00");

       th.sellTickets("04 SQALabsTime               SellerJonesSS   040 110.00",tickets_file, user_file);
       th.sellTickets("04 SQALabsTimessss           SellerJonesSS   040 110.00",tickets_file, user_file);

       assertEquals("Test Error (sell) added ticket when event name already exist",1, Collections.frequency(tickets_file, "SQALabsTime               SellerJonesSS   040 110.00"));
       assertEquals("Test Error (sell) ticket was not added/not found",true, tickets_file.contains("SQALabsTimessss           SellerJonesSS   040 110.00"));
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
       user_file.remove("BuyerJonesBS    BS 190000.00");
       user_file.add("BuyerJonesBS    BS 000002.00");
       th.buyTickets("04 SQALabsTime               SellerJonesSS   036 110.00",
                    "00 BuyerJonesBS    BS 000002.00",
                    user_file,
                    tickets_file,
                    init_tickets_file);

       assertEquals(true, (tickets_file.contains("SQALabsTime               SellerJonesSS   040 110.00") &&
                         user_file.contains("SellerJonesSS   SS 000002.00") &&
                         user_file.contains("BuyerJonesBS    BS 000002.00")));

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
    //Refund cause
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

   //this funciton does decision and loop coverage
   @Test
   public void testDelete(){
       //errror did not delete the tickets
       user_file = new ArrayList<>();
       tickets_file = new ArrayList<>();

       tickets_file.clear();
       user_file.clear();

       user_file.add("SellerJonesSS   SS 014502.00");
       th.deleteUser("02 SellerJonesSS   SS 014502.00", user_file, tickets_file);
       th.deleteUser("02 SellerBBosdaasd SS 000000.00", user_file, tickets_file);

       assertEquals(false, user_file.contains("SellerJonesSS   SS 014502.00"));

       user_file.add("SellerJonesSSs  SS 014502.00");
       tickets_file.add("SQALabsTime               SellerJonesSS   040 110.00");
       th.deleteUser("02 SellerJonesSSs  SS 014502.00", user_file, tickets_file);

       assertEquals(false, user_file.contains("SellerJonesSSs  SS 014502.00"));

       tickets_file.add("SQALabsTime               SellerJonesSSs  040 110.00");
       user_file.add("SellerJonesSSs  SS 014502.00");
       th.deleteUser("02 SellerJonesSSs  SS 014502.00", user_file, tickets_file);

       assertEquals(false, (tickets_file.contains("SQALabsTime               SellerJonesSSs  040 110.00") &&  user_file.contains("SellerJonesSSs  SS 014502.00")));



   }

   @Test
   public void testFileReader(){

       List<String> expected_daily_trans = new ArrayList<>();
       expected_daily_trans.add("01 BOOBY SMITH     FS 123000.23");
       expected_daily_trans.add("03 SQALabsTimesss            SellerJonesSS   036 110.00");
       expected_daily_trans.add("05 BobJonesNOADMIN SellerJonesSS  000010.00");
       expected_daily_trans.add("06 SellerJonesAA   AA 000100.00");
       expected_daily_trans.add("00 John Doe 123456 AA 330000.00");

       List<String> expected_user_file = new ArrayList<>();
       expected_user_file.add("Non Admin User  FS 000000.00");
       expected_user_file.add("John Doe 123456 AA 330000.00");
       expected_user_file.add("Seller Account1 SS 000000.00");
       expected_user_file.add("RecieverAccount FS 000000.00");

       List<String> expected_tickets_file = new ArrayList<>();
       expected_tickets_file.add("SQAFunTimes               SellerJonesSS   100 010.00");
       expected_tickets_file.add("SQALabsTime               SellerJonesSS   040 110.00");


       Output out = new Output();

       out.fileReader("Test_files/Test_UserFile.txt", "Test_files/Test_TicketsFile.txt", "Test_files/Test_DailyTransFile.txt");

       assertEquals("Test Error (fileReader) user lists do not match ",true,expected_user_file.equals(out.user_file));
       assertEquals("Test Error (fileReader) ticket lists do not match",true,expected_tickets_file.equals(out.tickets_file));
       assertEquals("Test Error (fileReader) daily trans lists do not match",true,expected_daily_trans.equals(out.daily_trans));

       Output wrongout = new Output();
       wrongout.fileReader("","","");
       assertEquals("Test Error something was read from a file when nothing should have been", 0, wrongout.user_file.size() +  wrongout.tickets_file.size() + wrongout.daily_trans.size());

   }

   @Test
   public void testProcTrans_ProcCurrentTrans(){
       Output out = new Output();

       out.fileReader("Test_files/TestProc_user","Test_files/TestProc_tickets","Test_files/TestProc_daily");
       out.processAllTrans();

       String[] expected_users =
       {"Non Admin User  FS 000000.00",
       "John Doe 123456 AA 330000.00",
       "Seller Account1 SS 000000.00",
       "RecieverAccount FS 000000.00",
       "NonAdminUser123 FS 000000.00",
       "BobJonesADMIN   AA 190000.00",
       "BuyerJonesSS    SS 190000.00",
       "SellerJonesAAsd FS 123000.23",
       "BuyerJonesBS    BS 189560.00",
       "BobJonesNOADMIN FS 012412.00",
       "SellerJonesSS   SS 012652.00",
       "SellerJonesAA   AA 100100.00"};

       String[] expected_tickets =
       {"SQAFunTimes               SellerJonesSS   100 010.00",
       "SQALabsTimes              SellerJonesSS   000 110.00",
       "SQALabsTimesss            SellerJonesSS   036 110.00",
       "SQALabsTime               SellerJonesSS   036 110.00"};
       List<String> expected_user_list = new ArrayList<String>(Arrays.asList(expected_users));
       List<String> expected_tickets_list = new ArrayList<String>(Arrays.asList(expected_tickets));

       assertEquals("Test Error (testProc) users list was not updated correctly", true, expected_user_list.equals(out.user_file));
       assertEquals("Test Error (testProc) tickets list was not updated correctly", true ,expected_tickets_list.equals(out.tickets_file));


   }

}
