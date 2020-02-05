import edu.duke.*;
import java.util.*;

/**
 * You will now modify the GladLib.java file to handle two additional categories—verbs and fruits.
 * Modify the program to handle replacing verbs with <verb> tags and fruits with <fruit> tags.
 * You will read in choices of verbs from the file verb.txt and choices for fruit from the file fruit.txt.
 */
public class GladLib {
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	/**
	 * Add private ArrayLists, one for verbs and one for fruits.
	 */
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	/**
	 * You should declare and initialize an additional private ArrayList to keep track of words that have been seen.
	 */
	private ArrayList<String> wordsAlreadySeen;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLib(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		wordsAlreadySeen=new ArrayList<String>();
	}
	
	public GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
		wordsAlreadySeen=new ArrayList<String>();
	}

	/**
	 * Modify the method initializeFromSource to read the data from these two files.
	 * @param source
	 */
	private void initializeFromSource(String source) {
		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");
		verbList = readIt(source+"/verb.txt");
		fruitList = readIt(source+"/fruit.txt");
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}

	/**
	 * Modify the method getSubstitute to handle the replacements of <verb> and <fruit>
	 * with random words of those types.
	 * @param label
	 * @return
	 */
	private String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			return randomFrom(timeList);
		}
		if (label.equals("verb")){
			return randomFrom(verbList);
		}
		if (label.equals("fruit")){
			return randomFrom(fruitList);
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return "**UNKNOWN**";
	}

	/**
	 * Now modify your program so that once it uses a word, it never uses that word again.
	 * You should declare and initialize an additional private ArrayList to keep track of words that have been seen.
	 * You will need to modify the method processWord
	 *  @param w
	 * @return
	 */
	
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
	 * Modify the file makeStory to read in the template file madtemplate2.txt that also uses the <verb> and <fruit> tags.
	 *  Modify your program to print out the total number of words that were replaced right after the story is printed.
	 */


	public void makeStory(){
		wordsAlreadySeen.clear();
	   	 //System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");

		printOut(story, 60);

		System.out.println("\nNumber of words that are replaced : "+wordsAlreadySeen.size());
	}


	public static void main(String args[]){

		GladLib obj=new GladLib();
		obj.makeStory();


	}


}
