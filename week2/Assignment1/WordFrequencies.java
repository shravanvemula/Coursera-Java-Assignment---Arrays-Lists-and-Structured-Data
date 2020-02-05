import java.util.ArrayList;
import edu.duke.*;

/**
 * Create a new class called WordFrequencies
 */
public class WordFrequencies
{
    /**
     * Create two private variables. One is called myWords and should be an ArrayList of type String to store unique words from a file,
     * and one is called myFreqs and should be an ArrayList of type Integer. The kth position in myFreqs
     * should represent the number of times the kth word in myWords occurs in the file.
     * Write a constructor WordFrequencies, and initialize the private variables.
     */
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords=new ArrayList<String>();
        myFreqs=new ArrayList<Integer>();

    }

    /**
     * Write a void method findUnique that has no parameters. This method should first clear both myWords and myFreqs, using the .clear() method.
     * Then it selects a file and then iterates over every word in the file, putting the unique words found into myWords.
     * For each word in the kth position of myWords, it puts the count of how many times that word occurs from the selected file
     * into the kth position of myFreqs, as was demonstrated in the lesson.
     */
    void findUnique() {
        myFreqs.clear();
        myWords.clear();

        FileResource fr = new FileResource();

        for (String word : fr.words()) {

            word=word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            }
            else
                {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }

        }


    }

    /**
     * Write the method findIndexOfMax that has no parameters. This method returns an int
     * that is the index location of the largest value in myFreqs. If there is a tie, then return the first such value.
     * @return
     */
    int findIndexOfMax(){
       int i,max=0;
        for(i=0;i<myFreqs.size();i++){

            if(myFreqs.get(i)>myFreqs.get(max)){
                max=i;
            }
        }
        return  max;
    }

    /**
     * Write a void tester method that has no parameters. This method should call findUnique.
     * Then print out the number of unique words, and for each unique word, print the frequency of each word and the word
     *
     * */
    void tester() {
        findUnique();
        for(int i=0;i<myWords.size();i++) {

            //System.out.println(myWords.get(i)+":"+myFreqs.get(i));
        }
        int maxIndex=findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "+myWords.get(maxIndex)+" "+myFreqs.get(maxIndex));
        System.out.println("Number of unique words in the file "+myWords.size());
    }

    public static void main(String args[]){

        WordFrequencies obj=new WordFrequencies();
        obj.tester();

    }








}
