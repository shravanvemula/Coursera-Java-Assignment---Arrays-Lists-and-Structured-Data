import edu.duke.FileResource;

import java.util.ArrayList;

/**
 * Create a class named CharactersInPlay.
 */
public class CharactersInPlay {

    private ArrayList<String> myCharacters;
    private ArrayList<Integer> myFreqs;

    public CharactersInPlay() {
        myCharacters = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();

    }

    /**
     * Write a void method named update that has one String parameter named person. This method should update the two ArrayLists,
     * adding the character’s name if it is not already there, and counting this line as one speaking part for this person.
     * @param person
     */

    void update(String person) {
        int index = myCharacters.indexOf(person);
        if (index == -1) {
            myCharacters.add(person);
            myFreqs.add(1);
        } else {
            int value = myFreqs.get(index);
            myFreqs.set(index, value + 1);
        }
    }

    /**
     * Write a void method called findAllCharacters that opens a file, and reads the file line-by-line.
     * For each line, if there is a period on the line, extract the possible name of the speaking part,
     * and call update to count it as an occurrence for this person. Make sure you clear the appropriate
     * instance variables before each new file.
     */
    void findAllCharacters() {
        myCharacters.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();

        for (String line : fr.lines()) {

            int indexOfPeriod = line.indexOf('.');
            if (indexOfPeriod != -1) {
                update(line.substring(0, indexOfPeriod));
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
     * Write the method findIndexOfMin that has no parameters. This method returns an int
     * that is the index location of the minimal value in myFreqs. If there is a tie, then return the first such value.
     * @return
     */
    int findIndexOfMin(){
        int i,min=0;
        for(i=0;i<myFreqs.size();i++){

            if(myFreqs.get(i)<myFreqs.get(min)){
                min=i;
            }
        }
        return  min;
    }

    /**
     * Write a void method called charactersWithNumParts that has two int parameters named num1 and num2,
     * where you can assume num1 should be less than or equal to num2. This method should print out the names of all those characters
     * that have exactly number speaking parts, where number is greater than or equal to num1 and less than or equal to num2.
     * Add code in tester to test this method out.
     * @param num1
     * @param num2
     */
    void charactersWithNumParts(int num1,int num2){

        for(int i=0;i<myFreqs.size();i++){
            if(myFreqs.get(i)>num1 && myFreqs.get(i)<=num2){
                System.out.println(myCharacters.get(i) + ":" + myFreqs.get(i));
            }
        }

    }

    /**
     * Write a void method called tester that has no parameters. This method should call findAllCharacters, and then for each main character,
     * print out the main character, followed by the number of speaking parts that character has.
     * A main character is one who has more speaking parts than most people. You’ll have to estimate what that number should be.
     */

    void tester() {

        findAllCharacters();

        for (int i = 0; i < myCharacters.size(); i++) {
            System.out.println(myCharacters.get(i) + ":" + myFreqs.get(i));
        }
        System.out.println("Characters are :");
        int num1=myFreqs.get(findIndexOfMin());
        int num2=myFreqs.get(findIndexOfMax());
        charactersWithNumParts(num1,num2);




    }

    public static void main(String args[]) {

        CharactersInPlay obj = new CharactersInPlay();
        obj.tester();


    }
}
