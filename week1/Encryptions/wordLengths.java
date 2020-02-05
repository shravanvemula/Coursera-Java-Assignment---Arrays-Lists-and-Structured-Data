import edu.duke.FileResource;

/**
 * Create a new class called WordLengths
 */
public class wordLengths {


    /**
     * Write a void method countWordLengths that has two parameters, a FileResource named resource and an integer array named counts.
     * This method should read in the words from resource and count the number of words of each length for all the words in resource,
     * storing these counts in the array counts.
     * If a word has a non-letter as the first or last character, it should not be counted as part of the word length.
     * For example, the word And, would be considered of length 3 (the comma is not counted), the word “blue-jeans” would be considered of length 10
     * Note that we will miscount some words, such as “Hello,” which will be counted as 6 since we don’t count
     * the double quotes but will count the comma, but that is OK as there should not be many words in that category.

     * @param fr
     * @param counts
     * @return
     */
    int[] countWordLengths(FileResource fr,int[] counts) {

        for (String s : fr.words()) {
            int length = s.length();

            if (length > 0) {
                if (!(Character.isLetter(s.charAt(length - 1)))) {
                    length--;
                }
                if (!(Character.isLetter(s.charAt(0)))) {
                    length--;
                }

                if (length >= 30) {
                    counts[30]++;
                } else {
                    counts[length]++;
                }


            }
        }
        return counts;
    }

    /**
     * Write a method indexOfMax that has one parameter named values that is an integer array. This method returns the index position of the largest element in values.
     * Then add code to the method testCountWordLengths to call indexOfMax to determine the most common word length in the file.
     * @param counts
     * @return
     */
     int indexOfMax(int[] counts){

            int maxValue = -1;
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] > maxValue) {

                    maxValue = counts[i];


                }
            }
            return maxValue;
        }

    /**
     * Write a void method testCountWordLengths that creates a FileResource so you can select a file,
     * and creates a counts integer array of size 31. This method should call countWordLengths with a file and
     * then print the number of words of each length. Test it on the small file smallHamlet.txt shown below.

     */
    void testCountWordLengths(){
            FileResource fr=new FileResource();

            int[] counts=new int[31];

            countWordLengths(fr,counts);

            int frequentLength=indexOfMax(counts);

            System.out.println("The Frequent Word Length is "+frequentLength);


        }


    public static void main(String args[]){

        wordLengths obj=new wordLengths();
        obj.testCountWordLengths();
    }




}
