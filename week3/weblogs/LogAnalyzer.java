import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class LogAnalyzer {

    /**
     * In the LogAnalyzer class you will need to complete the constructor to initialize records to an empty ArrayList
     * and complete the readFile method to create a FileResource and to iterate over all the lines in the file.
     * For each line, create a LogEntry and store it in the records ArrayList.
     */
    private ArrayList<LogEntry> records;
        public LogAnalyzer(){
            records=new ArrayList<LogEntry>();
        }

        public void readFile(String filename){

            FileResource fr=new FileResource(filename);
            for(String line:fr.lines()){

                LogEntry le=WebLogParser.parseEntry(line);
                records.add(le);

            }


    }
    void printAll(){
            System.out.println("Log Entries are:");
            for(int i=0;i<records.size();i++){
                System.out.println(records.get(i));
            }
    }

    /**
     * In the LogAnalyzer class, write the method countUniqueIPs that has no parameters.
     * This method should return an integer representing the number of unique IP addresses.
     * It should also assume that the instance variable records already has its ArrayList of Strings
     * read in from a file, and should access records in computing this value
     * @return
     */
    int countUniqueIPs(){

            ArrayList<String> uniqueIps=new ArrayList<String >();

            for(LogEntry le:records){
                String ipAddress=le.getIpAddress();
                if(!uniqueIps.contains(ipAddress)){
                    uniqueIps.add(ipAddress);
                }

            }
            return uniqueIps.size();
    }

    /**
     * In the LogAnalyzer class, write the void method printAllHigherThanNum that has one integer parameter num.
     * This method should examine all the web log entries in records and print those LogEntrys that have a status
     * code greater than num. Be sure to add code in the Tester class to test out this method with the file short-test_log.
     * @param num
     */

    void printAllHigherThanNum(int num)
    {
        for(LogEntry le:records){
            int status=le.getStatusCode();
            if(status>num){
                System.out.println(le);
            }

        }

    }

    /**
     * In the LogAnalyzer class, write the method uniqueIPVisitsOnDay that has one String parameter named someday in the format “MMM DD” where MMM is the first three characters of the month
     * name with the first letter capitalized and the others in lowercase, and DD is the day in two digits (examples are “Dec 05” and “Apr 22”). This method accesses the web logs in records
     * and returns an ArrayList of Strings of unique IP addresses that had access on the given day.
     * @param someday
     * @return
     */

    ArrayList<String> uniqueIPVisitsOnDay(String someday){

        ArrayList<String> uniqueIPs=new ArrayList<String>();
        for(LogEntry le:records){

            String currentDay=le.getAccessTime().toString();

            if(currentDay.indexOf(someday)!=-1){
                String currentIP=le.getIpAddress();
                if(!uniqueIPs.contains(currentIP)){
                    uniqueIPs.add(currentIP);
                }

            }
        }
        return uniqueIPs;
    }

    /**
     * In the LogAnalyzer class, write the method countUniqueIPsInRange that has two integer parameters named low and high.
     * This method returns the number of unique IP addresses in records that have a status code in the range from low to high, inclusive.
     * Be sure to test your program on several ranges.
     * @param low
     * @param high
     * @return
     */

    int countUniqueIPsInRange(int low,int high){
        ArrayList<String> uniqueIPs=new ArrayList<String>();
        for(LogEntry le:records){
            int status=le.getStatusCode();
            if(status>=low && status<=high){
                String currentIP=le.getIpAddress();
                if(!uniqueIPs.contains(currentIP)){
                    uniqueIPs.add(currentIP);
                }
            }

        }
        return uniqueIPs.size();
    }

    /**
     * In the LogAnalyzer class, write the method countVisitsPerIP, which has no parameters. This method returns a HashMap<String, Integer>
     *     that maps an IP address to the number of times that IP address appears in records, meaning the number of times this IP address visited
     *     the website. Recall that records stores LogEntrys from a file of web logs
     * @return
     */

    HashMap<String,Integer> countVisitsPerIP(){

        HashMap<String,Integer> counts=new HashMap<String,Integer>();


        for(LogEntry le:records) {

            String currentIP = le.getIpAddress();
            if (counts.containsKey(currentIP)) {
                counts.put(currentIP, counts.get(currentIP) + 1);
            } else {
                counts.put(currentIP, 1);
            }
        }
        return counts;


    }

    /**
     * In the LogAnalyzer class, write the method mostNumberVisitsByIP, which has one parameter, a HashMap<String, Integer> that maps an IP address to the number of times that IP address appears in the web log file. This method returns the maximum
     * number of visits to this website by a single IP address. For example, the call mostNumberVisitsByIP on a HashMap formed using the file weblog3-short_log returns 3.
     * @param counts
     * @return
     */

    int mostNumberVisitsByIP(HashMap<String,Integer> counts){
        int max=0;
        for(String key:counts.keySet()){
            if(counts.get(key)>max){
                max=counts.get(key);
            }
        }
        return max;
    }

    /**
     * In the LogAnalyzer class, write the method iPsMostVisits, which has one parameter, a HashMap<String, Integer> that maps an IP address to the number of times that IP address appears in the web log file.
     * This method returns an ArrayList of Strings of IP addresses that all have the maximum number of visits to this website. For example, the call iPsMostVisits on a HashMap formed using the file weblog3-short_log
     * returns the ArrayList with these two IP addresses, 61.15.121.171 and 84.133.195.161. Both of them visited the site three times, which is the maximum number of times any IP address visited the site.
     * @param counts
     * @return
     */

    ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
        int maxIPCount=mostNumberVisitsByIP(counts);

        ArrayList<String> mostVisitedIPs=new ArrayList<String>();
        for(String key:counts.keySet()){
            if(counts.get(key)==maxIPCount){
                mostVisitedIPs.add(key);
            }
        }

        return mostVisitedIPs;
    }

    /**
     * In the LogAnalyzer class, write the method iPsForDays, which has no parameters. This method returns a HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList
     * of IP addresses that occurred on that day (including repeated IP addresses). A day is in the format “MMM DD” where MMM is the first three characters of the month name with the first letter capital
     * and the others in lowercase, and DD is the day in two digits (examples are “Dec 05” and “Apr 22”). For example, for the file weblog3-short_log, after building this HashMap, if you print it out, you
     * will see that Sep 14 maps to one IP address, Sep 21 maps to four IP addresses, and Sep 30 maps to five IP addresses.In the LogAnalyzer class, write the method iPsForDays, which has no parameters.
     * This method returns a HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList of IP addresses that occurred on that day (including repeated IP addresses).
     * A day is in the format “MMM DD” where MMM is the first three characters of the month name with the first letter capital and the others in lowercase, and DD is the day in two digits (examples are “Dec 05” and “Apr 22”).
     * For example, for the file weblog3-short_log, after building this HashMap, if you print it out, you will see that Sep 14 maps to one IP address, Sep 21 maps to four IP addresses, and Sep 30 maps to five IP addresses.
     * @return
     */
    HashMap<String,ArrayList<String>> iPsForDays(){


        HashMap<String,ArrayList<String>>  daysAndIPs=new HashMap<String,ArrayList<String>>();

        for(LogEntry le:records){
            String currentDay=extractDayFromDate(le.getAccessTime());

            if(!daysAndIPs.containsKey(currentDay)){
                ArrayList<String> ips=new ArrayList<String>();
                ips.add(le.getIpAddress());
                daysAndIPs.put(currentDay,ips);
            }
            else
            {
                ArrayList<String> currList=daysAndIPs.get(currentDay);
                currList.add(le.getIpAddress());
                daysAndIPs.put(currentDay,currList);
            }


        }
        return daysAndIPs;

    }

    /**
     * In this method String "Sep 30" is extracted from Wed Sep 30 17:29:14 IST 2015
     * @param date
     * @return
     */


    String extractDayFromDate(Date date) {
        String dateStr = date.toString();
       return dateStr.substring(4,10);
    }
    /**
     * In the LogAnalyzer class, write the method dayWithMostIPVisits, which has one parameter that is a HashMap<String, ArrayList<String>>
     *     that uses records and maps days from web logs to an ArrayList of IP addresses that occurred on that day. This method returns the
     *     day that has the most IP address visits. If there is a tie, then return any such day.
     *     For example, if you use the file weblog3-short_log, then this method should return the day most visited as Sep 30.
     */

    String dayWithMostIPVisits(HashMap<String,ArrayList<String>> daysAndIPs){

        int max=0;
        String requiredDay="";
        for(String key:daysAndIPs.keySet()){
            if(daysAndIPs.get(key).size()>max){
                max=daysAndIPs.get(key).size();
                requiredDay=key;
            }
        }
        return requiredDay;
    }

    /**
     * In the LogAnalyzer class, write the method iPsWithMostVisitsOnDay, which has two parameters—the first one is a HashMap<String, ArrayList<String>>
     * that uses records and maps days from web logs to an ArrayList of IP addresses that occurred on that day, and the second parameter is a String
     * representing a day in the format “MMM DD” described above. This method returns an ArrayList<String> of IP addresses that had the most accesses on the given day.
     * For example, if you use the file weblog3-short_log, and the parameter for the day is “Sep 30”, then there are two IP addresses in the ArrayList returned: 61.15.121.171 and 177.4.40.87.
     */

     ArrayList<String> iPsWithMostVisitsOnDay( HashMap<String,ArrayList<String>> daysAndIPs,String day){


         HashMap<String,Integer> ipsAndCounts=new HashMap<String,Integer>();
         ipsAndCounts=countVisitsPerIP2(daysAndIPs.get(day));


         ArrayList<String> requiredIPs=new ArrayList<String>();

         int max=mostNumberVisitsByIP(ipsAndCounts);


         for(String key:ipsAndCounts.keySet()){
             if(ipsAndCounts.get(key)==max){
                 requiredIPs.add(key);
             }
         }
         return requiredIPs;


     }

    /**
     * This method is for creating a HashMap of IP addresses mapping to Number of Visits
     * @param ips
     * @return
     */

    HashMap<String,Integer> countVisitsPerIP2(ArrayList<String> ips){

        HashMap<String,Integer> counts=new HashMap<String,Integer>();


        for(int i=0;i<ips.size();i++) {

            String currentIP = ips.get(i);
            if (counts.containsKey(currentIP)) {
                counts.put(currentIP, counts.get(currentIP) + 1);
            } else {
                counts.put(currentIP, 1);
            }
        }
        return counts;

    }



}
