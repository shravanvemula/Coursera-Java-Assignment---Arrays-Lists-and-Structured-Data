import java.io.File;
import java.text.StringCharacterIterator;
import java.util.*;
import edu.duke.*;

/**
 * Your first step in this mini-project is to write the three methods in the VigenereBreaker class.
 */
public class VigenereBreaker {

    /**
     * Write the public method breakVigenere with no parameters.It Should do 6 tasks
     *     Create a new FileResource (which displays a dialog for you to select a file to decrypt).
     *     Use the asString method to read the entire contents of the file into a String.
     *      Modify your breakVigenere method to read many dictionaries instead of just one.
     *      In particular, you should make a HashMap mapping Strings to a HashSet of Strings that will map
     *      each language name to the set of words in its dictionary. Then, you will want to r
     *      ead (using your readDictionary method) each of the dictionaries that we have provided
     *      (Danish, Dutch, English, French, German, Italian, Portuguese, and Spanish) and store the words in the HashMap you made.
     *      Reading all the dictionaries may take a little while, so you might add some print statements to reassure you that your
     *      program is making progress. Once you have made that change, you will want to call breakForAllLangs, passing in
     *      the message (the code to read in the message is unchanged from before), and the HashMap you just created.
     *     Finally, you should print out the decrypted message!
     */
    public void breakVigenere () {
        FileResource fr1=new FileResource();
        String encrypted=fr1.asString();
        DirectoryResource dr=new DirectoryResource();
        HashMap<String,HashSet<String>> languages=new HashMap<String,HashSet<String>>();

        for(File f:dr.selectedFiles()) {
            String language = f.getName();
            FileResource fr = new FileResource(f);

            HashSet<String> currDictionary=readDictionary(fr);
            languages.put(language,currDictionary);
            System.out.println(language+" Dictionary Created ");

        }

        String decrypted=breakForAllLangs(encrypted,languages);

        System.out.println("Decrypted Message is "+decrypted);

    }
    /**
     * In the VigenereBreaker class, write the public method readDictionary, which has one parameter—a FileResource fr.
     * This method should first make a new HashSet of Strings, then read each line in fr (which should contain exactly
     * one word per line), convert that line to lowercase, and put that line into the HashSet that you created.
     * The method should then return the HashSet representing the words in a dictionary.
     * @param fr
     * @return
     */

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary=new HashSet<String>();

        for(String word:fr.lines()){
            word=word.toLowerCase();
            dictionary.add(word);
        }
        return dictionary;
    }
    /**
     * In the VigenereBreaker class, write the public method breakForAllLangs, which has two parameters—a String encrypted, and a HashMap, called languages,
     * mapping a String representing the name of a language to a HashSet of Strings containing the words in that language.
     * Try breaking the encryption for each language, and see which gives the best results! You will want to use the breakForLanguage
     * and countWords methods that you already wrote to do most of the work (it is slightly inefficient to re-count the words here, but it is simpler, and the inefficiency is not significant).
     * You will want to print out the decrypted message as well as the language that you identified for the message.
     *
     * A language with maximum valid words is considered as best . So we should find Valid words of the decrypted message in each language
     * using countWords() methods and then printing the decrypted message.
     * @param encrypted
     * @param languages
     * @return
     */
    public String breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages) {
        int maxValidWords=0;
        String bestLanguage="";
        String requiredDecryptedString="";
        for(String language:languages.keySet()){
            HashSet<String> currDictionary=languages.get(language);
            System.out.println("Message is Decrypted using "+language);
            String currDecryptedString = breakForLanguage(encrypted,currDictionary);
            int currValidWords=countWords(currDecryptedString,currDictionary);

            if(currValidWords>maxValidWords){
                maxValidWords=currValidWords;
                bestLanguage=language;
                requiredDecryptedString=currDecryptedString;
            }
        }
        System.out.println("\nBest language for Decryption is "+bestLanguage);
        return requiredDecryptedString;
    }


    /**
     * In the VigenereBreaker class, write the public method breakForLanguage, which has two parameters—a String encrypted,
     * and a HashSet of Strings dictionary. This method should try all key lengths from 1 to 100 (use your tryKeyLength
     * method to try one particular key length) to obtain the best decryption for each key length in that range.
     * For each key length, your method should decrypt the message (using VigenereCipher’s decrypt method as before),
     * and count how many of the “words” in it are real words in a language, based on the dictionary passed in (use the countWords method you just wrote).
     * This method should figure out which decryption gives the largest count of real words, and return that String decryption. Note that there is
     * nothing special about 100; we will just give you messages with key lengths in the range 1–100. If you did not have this information,
     * you could iterate all the way to encrypted.length(). Your program would just take a bit longer to run.
     *
     * This method should make use of mostCommonCharIn() method to find the common character in a language
     * @param encrypted
     * @param dictionary
     * @return
     */
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){

        int maxCount=0;
        String requiredDecryptedString="";
        char mostCommon=mostCommomCharIn(dictionary);
        System.out.println(("Most Common Character in this language is "+mostCommon));
        for(int i=1;i<=100;i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher cipher=new VigenereCipher(keys);
            String currDecryptedString=cipher.decrypt(encrypted);
              int currCount=countWords(currDecryptedString,dictionary);

            if(currCount>maxCount){
                maxCount=currCount;
              requiredDecryptedString=currDecryptedString;
            }


       }


        return requiredDecryptedString;
    }
    /**
     * In the VigenereBreaker class, write the public method mostCommonCharIn, which has one parameter—a HashSet of Strings dictionary.
     * This method should find out which character, of the letters in the English alphabet, appears most often in the words in dictionary.
     * It should return this most commonly occurring character.
     * @param dictionary
     * @return
     */
    public char mostCommomCharIn(HashSet<String> dictionary){

        String alphabets="abcdefghijklmnopqrstuvwxyz";
        int[] counts=new int[26];
        Arrays.fill(counts, 0);
        for(String word:dictionary){
            for(int i=0;i<word.length();i++){
                int index=alphabets.indexOf(word.charAt(i));
                if(index!=-1){
                    counts[index]++;
                }
            }
        }

        int maxCountIndex=findFrequentCharacterIndex(counts);
        return alphabets.charAt(maxCountIndex);
    }

    /**
     * This method is used to find the index of maximum value in the counts array.
     * This index in alphabets array gives the most frequent character in a particular language
     * @param counts
     * @return
     */
    public int findFrequentCharacterIndex(int[] counts){
        int maxIdx=0;
        int maxValue=0;
        for(int i=0;i<26;i++){
            if(counts[i]>maxValue){
                maxValue=counts[i];
                maxIdx=i;
            }
        }
        return maxIdx;
    }

    /**
     * Write the public method tryKeyLength, which takes three parameters—a String encrypted that
     * represents the encrypted message, an integer klength that represents the key length, and
     * a character mostCommon that indicates the most common character in the language of the message.
     * This method should make use of the CaesarCracker class, as well as the sliceString method, to find
     * the shift for each index in the key. You should fill in the key (which is an array of integers) and return it.
     * @param encrypted
     * @param klength
     * @param mostCommon
     * @return
     */

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];

        for(int i=0;i<klength;i++){
            String currentSlice=sliceString(encrypted,i,klength);
            CaesarCracker cracker=new CaesarCracker(mostCommon);
            int currentKey=cracker.getKey(currentSlice);
            key[i]=currentKey;
        }

        return key;
    }
    /**
     * Write the public method sliceString, which has three parameters—a String message, representing the encrypted message,
     * an integer whichSlice, indicating the index the slice should start from, and an integer totalSlices, indicating the
     * length of the key. This method returns a String consisting of every totalSlices-th character from message,
     * starting at the whichSlice-th character.
     * sliceString("abcdefghijklm", 0, 3) should return "adgjm"
     * sliceString("abcdefghijklm", 1, 3) should return "behk"
     * @param message
     * @param whichSlice
     * @param totalSlices
     * @return
     */
    public String sliceString(String message, int whichSlice, int totalSlices) {

        String requiredSlice = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            requiredSlice = requiredSlice + message.charAt(i);
        }

        return requiredSlice;
    }


    /**
     * In the VigenereBreaker class, write the public method countWords, which has two parameters—a String message,
     * and a HashSet of Strings dictionary. This method should split the message into words (use .split(“\\W+”),
     * which returns a String array), iterate over those words, and see how many of them are “real words”—that is,
     * how many appear in the dictionary. Recall that the words in dictionary are lowercase. This method should
     * return the integer count of how many valid words it found.
     * @param message
     * @param dictionary
     * @return
     */
    public int countWords(String message,HashSet<String> dictionary){
        int countOfWordsOccured=0;
        for(String word:message.split("\\W+")){
            word=word.toLowerCase();
            if(dictionary.contains((word))){
                countOfWordsOccured++;
            }
        }
        return countOfWordsOccured;
    }




}
