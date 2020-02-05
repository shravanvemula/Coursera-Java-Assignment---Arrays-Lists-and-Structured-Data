import edu.duke.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Write a program to determine which words occur in the greatest number of files, and for each word, which files they occur in.
 *
 * For example, consider you are given the four files: brief1.txt, brief2.txt, brief3.txt, and brief4.txt.
 *
 * brief1.txt is:
 *
 * cats are funny and cute
 *
 * brief2.txt is:
 *
 * dogs are silly
 *
 * brief3.txt is:
 *
 * love animals cats and dogs
 *
 * brief4.txt is:
 *
 * love birds and cats
 *
 * The greatest number of files a word appears in is three, and there are two such words: “cats” and “and”.
 *
 * “cats” appears in the files: brief1.txt, brief3.txt, brief4.txt
 *
 * “and” appears in the files: brief1.txt, brief3.txt, brief4.txt
 *
 */
public class WordsInFiles {

    /**
     * Create a new class called WordsInFiles. Put all the remaining listed items in this class.
     * Create a private variable to store a HashMap that maps a word to an ArrayList of filenames.
     * Write a constructor to initialize the HashMap variable.
     */

    private HashMap<String,ArrayList<String>> myMap;

    public WordsInFiles()
    {
        myMap=new HashMap<String,ArrayList<String>>();

    }

    /**
     * Write a private void method named addWordsFromFile that has one parameter f of type File. This method should add all the words from f into the map. If a word is not in the map, then you must create a new ArrayList
     * of type String with this word, and have the word map to this ArrayList. If a word is already in the map, then add the current filename to its ArrayList,
     * unless the filename is already in the ArrayList. You can use the File method getName to get the filename of a file.
     * @param f
     */
    void addWordsFromFiles(File f){
        FileResource fr=new FileResource(f);


        for(String word:fr.words()){

            if(myMap.containsKey(word)){

                ArrayList<String> currList=myMap.get(word);
                if(!currList.contains(f.getName())){
                    myMap.get(word).add(f.getName());
                }
            }
            else{
                ArrayList<String> tempList=new ArrayList<String>();
                tempList.add(f.getName());
                myMap.put(word,tempList);
            }

        }


    }

    /**
     * Write a void method named buildWordFileMap that has no parameters. This method first clears the map, and then uses a DirectoryResource to select a group of files. For each file,
     * it puts all of its words into the map by calling the method addWordsFromFile. The remaining methods to write all assume that the HashMap has been built.
     */
    void buildWordFileMap(){
        myMap.clear();
        DirectoryResource dr=new DirectoryResource();

        for(File f:dr.selectedFiles()){

            addWordsFromFiles(f);
        }

    }

    /**
     * Write a void method named buildWordFileMap that has no parameters. This method first clears the map, and then uses a DirectoryResource to select a group of files. For each file, it puts all of its
     * words into the map by calling the method addWordsFromFile. The remaining methods to write all assume that the HashMap has been built.
     * @return
     */

    int maxNumber(){

        int max=0;
        for(String s:myMap.keySet()){

            if(myMap.get(s).size()>max){
                max=myMap.get(s).size();
            }
        }
        return max;
    }

    /**
     * Write the method wordsInNumFiles that has one integer parameter called number.
     * This method returns an ArrayList of words that appear in exactly number files.
     * In the example above, the call wordsInNumFiles(3) would return an ArrayList with
     * the words “cats” and “and”, and the call wordsInNumFiles(2) would return an ArrayList
     * with the words “love”, “are”, and “dogs”, all the words that appear in exactly two files.
     * @param number
     * @return
     */
    ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words=new ArrayList<String>();

        for(String s:myMap.keySet()){

            if(myMap.get(s).size()==number){
                words.add(s);
            }
        }
        return words;


    }

    /**
     * Write the void method printFilesIn that has one String parameter named word.
     * This method prints the names of the files this word appears in, one filename per line. For example,
     * in the example above, the call printFilesIn(“cats”) would print the
     * three filenames: brief1.txt, brief3.txt, and brief4.txt, each on a separate line.
     *
     * @param word
     */

    void printFilesIn(String word){

        System.out.println(word+" is present in the Files:");
        for(String s:myMap.keySet()){

            if(s.equals(word)){
                ArrayList<String> currList=myMap.get(s);
                for(int i=0;i<currList.size();i++){
                    System.out.println(currList.get(i));
                }
            }
        }
    }

    /**
     * Write the void method tester that has no parameters. This method should call buildWordFileMap to select a group of files
     * and build a HashMap of words, with each word mapped to an ArrayList of the filenames this word appears in,
     * determine the maximum number of files any word is in, considering all words, and determine all the words that are in
     * the maximum number of files and for each such word, print the filenames of the files it is in. (optional) If the map is
     * not too big, then you might want to print out the complete map, all the keys, and for each key its ArrayList.
     * This might be helpful to make sure the map was built correctly.
     */
    void tester()
    {
        buildWordFileMap();
        /*printing map
        System.out.println("Contents of Map ");
        for(String s:myMap.keySet()){
            System.out.println(s+":");
            ArrayList<String> currList=myMap.get(s);
            for(int i=0;i<currList.size();i++){
                System.out.println(currList.get(i));
            }
        }

         */

        System.out.println("\nMaximum Occurences of a word in a file: "+maxNumber());

       int number=maxNumber();

        ArrayList<String> currList=wordsInNumFiles(number);
        System.out.println("\nWords that appear in exactly "+number+" files are "+currList.size());
        for(int i=0;i<currList.size();i++){
            System.out.println(currList.get(i));
        }


    }

    public static void main(String args[]){

        WordsInFiles obj=new WordsInFiles();
        obj.tester();
    }

}
