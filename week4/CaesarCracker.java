import edu.duke.*;

/**
 * This class provides an implementation of the Caesar cipher cracking (or breaking) algorithm that you learned about earlier. As with the CaesarCipher class, a few adjustments have been made:
 *
 *     The constructor takes a parameter for the most common letter, so it can be used for languages other than English.
 *     Finding the key has been separated from decrypting the message. You can use the method getKey to pass in an encrypted message and receive the key back.
 *     You can test these methods in the tester class by making a new CaesarCracker object and decrypting the file titus-small_key5.txt using no arguments for the constructor
 *     (default most common character ‘e’), and the file oslusiadas_key17.txt, noting that the most common character in Portuguese is ‘a’.
 */

public class CaesarCracker {
    char mostCommon;
    
    public CaesarCracker() {
        mostCommon = 'e';
    }
    
    public CaesarCracker(char c) {
        mostCommon = c;
    }
    
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++){
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }

    public int getKey(String encrypted){
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos-maxDex);
        }
        return dkey;
    }
    
    public String decrypt(String encrypted){
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);



        
    }
   
}
