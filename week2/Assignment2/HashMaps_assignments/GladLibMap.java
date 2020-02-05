import edu.duke.*;
import java.util.*;

/**
 * Start with your GladLibs program you completed earlier in this lesson. Make a copy of it and call it GladLibMap.java. Now modify this program to use one HashMap
 * that maps word types to ArrayList of possible words to select. Your program should still work for the additional categories verbs and fruits and should not use
 * duplicate words from a category. Specifically, you should make the following adjustments to this program:
 */

public class GladLibMap {

    /**
     * Replace the ArrayLists for adjectiveList, nounList, colorList, countryList, nameList, animalList, timeList, verbList, and fruitList
     * with one HashMap myMap that maps a String representing a category to an ArrayList of words in that category.
     */
    HashMap<String,ArrayList<String>> myMap;

    private ArrayList<String> wordsAlreadySeen;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){

        myMap=new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordsAlreadySeen=new ArrayList<String>();
    }

    public GladLibMap(String source){
        myMap=new HashMap<String,ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        wordsAlreadySeen=new ArrayList<String>();
    }

    /**
     * Modify the method initializeFromSource to create an Array of categories and then iterate over this Array.
     * For each category, read in the words from the associated file, create an ArrayList of the words (using the method readIt),
     * and put the category and ArrayList into the HashMap.
     * @param source
     */
    private void initializeFromSource(String source) {

        String[] words={"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};


        for(int i=0;i<words.length;i++){
            ArrayList<String> currList=readIt(source+'/'+words[i]+".txt");
            myMap.put(words[i],currList);
        }


    }


    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    /**
     * Modify the method getSubstitute to replace all the if statements that use category labels with
     * one call to randomFrom that passes the appropriate ArrayList from myMap.
     * @param label
     * @return
     */

    private String getSubstitute(String label) {

        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        else{
            for(String s:myMap.keySet()){
                if(s.equals(label)){
                    return randomFrom(myMap.get(s));
                }
            }
        }
        return "**UNKNOWN**";
    }



    private String processWord(String w){
        int count=0;
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));


        if(wordsAlreadySeen.size()!=0) {

            while(true) {

                if (wordsAlreadySeen.contains(sub)) {
                    sub = getSubstitute(w.substring(first+1,last));
                } else {
                    wordsAlreadySeen.add(sub);

                    break;
                }

            }
        }
        else if(wordsAlreadySeen.size()==0){
            wordsAlreadySeen.add(sub);
        }
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;

        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    /**
     * Write a new method named totalWordsInMap with no parameters. This method returns the total number of words in all the ArrayLists in the HashMap.
     * After printing the GladLib, call this method and print out the total number of words that were possible to pick from.
     * @return
     */
    int totalWordsInMap(){
        int count=0;
        for(String s:myMap.keySet()){
            count=count+myMap.get(s).size();
        }
        return count;
    }



    public void makeStory(){
        wordsAlreadySeen.clear();
        //System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");

        printOut(story, 60);

        System.out.println("\nNumber of words that are replaced : "+wordsAlreadySeen.size());

        System.out.println("\n Number of words in total Arraylists:"+totalWordsInMap());
    }


    public static void main(String args[]){

        GladLibMap obj=new GladLibMap();
        obj.makeStory();


    }


}

