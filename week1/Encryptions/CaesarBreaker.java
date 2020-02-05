import edu.duke.FileResource;

import java.io.File;

public class CaesarBreaker
{

    /**
     * This method calculates number of occurences of each character in the string encrypted and
     * stores them in an array.
     * @param encrypted
     * @return
     */

    int[] countLetters(String encrypted){


        int[] counts=new int[26];
        String alpha="abcdefghijklmnopqrstuvwxyz";

        String tempEncrypted=encrypted.toLowerCase();

        for(int i=0;i<encrypted.length();i++){


            int index=alpha.indexOf(tempEncrypted.charAt(i));
            if(index!=-1){
                counts[index]++;
            }

        }
        return counts;
    }

    /**
     * This method returns the index that has maximum value of occurences in the array counts.
     *
     * @param counts
     * @return
     */
    int indexOfMax(int[] counts){

        int maxValue = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > counts[maxValue]) {

                maxValue = i;


            }
        }
        return maxValue;
    }

    /**
     * Complete the decryption method shown in the lesson by creating a CaesarBreaker class with the methods
     * countLetters, maxIndex, and decrypt. Recall that the decrypt method
     * creates a CaesarCipher object in order to use the encrypt method you wrote for the last lesson.
     * @param encrpyted
     * @return
     */

    String decrypt(String encrpyted){
        CaeserCipher cc=new CaeserCipher();
        int[] counts=countLetters(encrpyted);

        int maxIndex=indexOfMax(counts);

         int dKey=maxIndex-4;
         if(maxIndex<4){
             dKey=26-(4-maxIndex);

         }

         return cc.encrypt(encrpyted,26-dKey);

    }



    /**
     * Write the method halfOfString in the CaesarBreaker class that has two parameters, a String parameter named message and an int parameter named start.
     * This method should return a new String that is every other character from message starting with the start position.
     * For example, the call halfOfString(“Qbkm Zgis”, 0) returns the String “Qk gs” and the call halfOfString(“Qbkm Zgis”, 1)
     * returns the String “bmZi”. Be sure to test this method with a small example.
     * @param message
     * @param start
     * @return
     */

    String  halfOfString(String message,int start){

        String newString="";
        while(start<message.length()){

            newString=newString+message.charAt(start);
            start+=2;
        }
        return newString;

    }

    /**
     * Write the method getKey in the CaesarBreaker class that has one parameter, a String s. This method should
     * call countLetters to get an array of the letter frequencies in String s and then use maxIndex to calculate
     * the index of the largest letter frequency, which is the location of the encrypted letter ‘e’, which leads to the key,
     * which is returned.
     *
     * @param s
     * @return
     */
    int getKey(String s){
        int[] counts=new int[26];

        counts=countLetters(s);

        int maxIndex=indexOfMax(counts);

        return maxIndex;

    }

    /**
     * Write the method decryptTwoKeys in the CaesarBreaker class that has one parameter, a String parameter named encrypted
     * that represents a String that was encrypted with the two key algorithm discussed in the previous lesson. This method attempts to determine the two keys
     * used to encrypt the message, prints the two keys, and then returns the decrypted String with those two keys.
     *
     * @param encrypted
     * @return
     */

    String decryptTwoKeys(String encrypted){

        CaeserCipher cc=new CaeserCipher();
        String s1=halfOfString(encrypted,0);
        String s2=halfOfString(encrypted,1);


        int s1MaxIndex=getKey(s1);

        int s2MaxIndex=getKey(s2);

        int s1dKey=s1MaxIndex-4;
        if(s1MaxIndex<4){
            s1dKey=26-(4-s1MaxIndex);

        }

        System.out.println(" The Key1 of "+s1+" is "+s1dKey);

        int s2dKey=s2MaxIndex-4;
        if(s2MaxIndex<4){
            s2dKey=26-(4-s2MaxIndex);

        }
        System.out.println(" The Key2 of "+s2+" is "+s2dKey);

        return cc.encryptTwoKeys(encrypted,26-s1dKey,26-s2dKey);


    }

    /**
     * Write a testDecrypt method in the CaesarBreaker class that prints the decrypted message,
     * and make sure it works for encrypted messages that were encrypted with one key.
     */
    void testDecrypt(){
        //FileResource fr=new FileResource();
        String encrpyted= "CFOPQ IBDFLK XQQXZH BXPQ CIXKH!";

        /*fr.asString();*/

        System.out.println("Decrypted Message is "+decrypt(encrpyted));

        System.out.println(halfOfString("Qbkm Zgis", 0)+" "+halfOfString("Qbkm Zgis", 1));

        System.out.println("The decrypted message is "+decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));


    }
    public  static void main(String args[]){


        CaesarBreaker obj=new CaesarBreaker();
        obj.testDecrypt();
    }


}
