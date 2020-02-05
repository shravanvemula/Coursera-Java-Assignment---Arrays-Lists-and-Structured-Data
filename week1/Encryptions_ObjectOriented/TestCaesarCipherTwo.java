import edu.duke.FileResource;

public class TestCaesarCipherTwo
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
     * Write the void method simpleTests that has no parameters. This method should read in a file as a String,
     * create a CaesarCipherTwo object with keys 17 and 3, encrypt the String using the CaesarCipherTwo object,
     * print the encrypted String, and decrypt the encrypted String using the decrypt method.
     */
    void simpleTests(){

        FileResource fr=new FileResource();
        String message=fr.asString();

        CaesarCipherTwo cct=new CaesarCipherTwo(17,3);
        String encrypted = cct.encrypt(message);
        System.out.println(("Encrypted Message is " + encrypted));

        String decrypted = cct.decrypt(encrypted);
        System.out.println(("Decrypted Message is " + decrypted));

        decrypted=breakCaesarCipher(encrypted);
        System.out.println(("Decrypted Message derived using BreakCaeserCipher " + decrypted));




    }
    /**
     * Write the method getKey in this class that has one parameter, a String s. This method should
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
     * Write the method breakCaesarCipher that has one String parameter named input. This method should figure out which keys
     * were used to encrypt this message (in a similar manner as before),
     * then create a CaesarCipherTwo object with that key and decrypt the message.
     */

    String breakCaesarCipher(String input){


        String s1=halfOfString(input,0);
        String s2=halfOfString(input,1);


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
        CaesarCipherTwo cct=new CaesarCipherTwo(26-s1dKey,26-s2dKey);

        return cct.encrypt(input);

    }

    public static void main(String args[]){


        TestCaesarCipherTwo tcct=new TestCaesarCipherTwo();
        tcct.simpleTests();

    }

}
