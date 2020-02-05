import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tester {




    public void testLogEntry(){

        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    /**
     * In the Tester class you will need to complete the testLogAnalyzer method, which creates a LogAnalyzer object,
     * calls readFile on the data file short-test_log, and then calls printAll to print all the web logs.
     */

    public void testLogAnalyzer(){

        LogAnalyzer la=new LogAnalyzer();
        la.readFile("weblog1_log");

        la.printAll();
        //testing countUniqueIps;
        System.out.println("No of unique IP addresses are "+la.countUniqueIPs());

        //testing printALlHighetThanNum
        System.out.println("Log Entries having status code greater than 300 are");
        la.printAllHigherThanNum(300);

        //testing uniqueIPVisitsOnaday
        ArrayList<String> uniqueIPs=la.uniqueIPVisitsOnDay("Sep 30");
        System.out.println("Unique IP visits on Sep 30 are "+uniqueIPs.size());
        for(int i=0;i<uniqueIPs.size();i++){
            System.out.println(uniqueIPs.get(i));
        }

        //testing No of countUniqueIPsInRange
        System.out.println("No of unique IPs having status code between 200 and 299 are "+la.countUniqueIPsInRange(200,299));


        //testing countVisitsPerIp

        HashMap<String,Integer> counts=la.countVisitsPerIP();

        System.out.println("Count of IPs visited "+counts.size()+" and they are ");
        System.out.println(counts);

        //testing mostVisitsByIP
        int maxVisits=la.mostNumberVisitsByIP(counts);
        System.out.println("Maximum number of Visits by an IP is "+maxVisits);

        //testing iPsMostVisits

        ArrayList<String> mostVisitedIPs=la.iPsMostVisits(counts);

        System.out.println("Mostly Visited IPs are "+mostVisitedIPs);

        //testing IPsForDays
        System.out.println("Days and their IPs are:");
        HashMap<String,ArrayList<String>> daysAndIPs=la.iPsForDays();
        System.out.println(daysAndIPs);

        //testing dayWithMostIPVisits
        String dayWithMostNumberIPVisits=la.dayWithMostIPVisits(daysAndIPs);
        System.out.println("\nDay with the most visited IPs is "+dayWithMostNumberIPVisits);

        //testing iPsWithMostVisitsOnaDay
        ArrayList<String> requiredIPs=la.iPsWithMostVisitsOnDay(daysAndIPs,dayWithMostNumberIPVisits);
        System.out.println("Most Visited IPs on "+dayWithMostNumberIPVisits+" are "+requiredIPs);

    }


    public static void main(String args[]){
        Tester obj=new Tester();
        obj.testLogEntry();
        obj.testLogAnalyzer();


    }
}
