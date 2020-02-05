import edu.duke.FileResource;

import java.util.*;

/**
 * Write a program to find out how many times each codon occurs in a strand of DNA based on reading frames.
 * A strand of DNA is made up of the symbols C, G, T, and A. A codon is three consecutive symbols in a strand
 * of DNA such as ATT or TCC. A reading frame is a way of dividing a strand of DNA into consecutive codons..
 */


public class CodonCount {

    /**
     * Create a private variable to store a HashMap to map DNA codons to their count.
     * Write a constructor to initialize the HashMap variable.
     */

    private HashMap<String,Integer> myMap;

        public CodonCount(){
            myMap=new HashMap<String, Integer>();
        }

    /**
     * Write a void method named buildCodonMap that has two parameters, an int named start and a String named dna.
     * This method will build a new map of codons mapped to their counts from the string dna with the reading frame
     * with the position start (a value of 0, 1, or 2).
     * @param start
     * @param dna
     */
    void buildCodonMap(int start,String dna){

           int currIdx=start;
            while(currIdx+3<dna.length()){
                String currCodon=dna.substring(currIdx,currIdx+3);

                if(!myMap.containsKey(currCodon)){
                    myMap.put(currCodon,1);
                }
                else{
                    myMap.put(currCodon,myMap.get(currCodon)+1);
                }

                currIdx=currIdx+3;

            }

        }

    /**
     * Write a method named getMostCommonCodon that has no parameters. This method returns a String, the codon in a
     * reading frame that has the largest count. If there are several such codons, return any one of them.
     * This method assumes the HashMap of codons to counts has already been built.
     * @return
     */
    String getMostCommonCodon(){
            int max=0;
            String mostCommonCodon="";
            for(String s:myMap.keySet()){
               if(myMap.get(s)>max){
                   max=myMap.get(s);
                   mostCommonCodon=s;
               }
            }
            return mostCommonCodon;
        }

    /**
     * This method is used to find codons that is only once occurred
     * @return
     */
    int countUniqueCodons(){
            int unique=0;
            for(String s:myMap.keySet()){
                if(myMap.get(s)==1){
                    unique++;
                }
            }
            return unique;

        }

    /**
     * Write a void method named printCodonCounts that has two int parameters, start and end.
     * This method prints all the codons in the HashMap along with their counts
     * if their count is between start and end, inclusive.
     * @param start
     * @param end
     */
        void printCodonCounts(int start,int end){
            System.out.println("Codons having count between "+start+" and "+end);
            for(String s:myMap.keySet()){
                if(myMap.get(s)>=start && myMap.get(s)<=end){

                    System.out.println(s+":"+myMap.get(s));

                }
            }
        }

    /**
     * Write a tester method that prompts the user for a file that contains a DNA strand (could be upper or lower case letters in the file, convert them all to uppercase, since case should not matter).
     * Then for each of the three possible reading frames, this method builds a HashMap of codons to their number of occurrences in the DNA strand,
     * prints the total number of unique codons in the reading frame, prints the most common codon and its count, and prints the codons and their number of occurrences for those codons
     * whose number of occurrences in this reading frame are between two numbers inclusive.
     */
    void tester(){

            FileResource fr=new FileResource();
            String dna=fr.asString();
            dna=dna.toUpperCase();
            for(int i=0;i<3;i++){

                myMap.clear();
                buildCodonMap(i,dna);
                System.out.println("Reading Frame starting with "+i+" results in "+countUniqueCodons()+" unique codons");
                String mostCommonCodon=getMostCommonCodon();
                System.out.println("Most common codon is "+mostCommonCodon);

                printCodonCounts(1,6);
            }


        }


        public static void main(String args[]){
            CodonCount obj=new CodonCount();
            obj.tester();
        }


}
