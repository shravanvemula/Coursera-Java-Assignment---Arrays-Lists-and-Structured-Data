import java.io.File;
import edu.duke.*;

/**
 * Create a new class called CaesarCipher.

 */
public class CaeserCipher
{


    /**
     * Write the method encrypt that has two parameters, a String named input and an int named key.
     * This method returns a String that has been encrypted using the Caesar Cipher algorithm .
     * For example, the call encrypt(“FIRST LEGION ATTACK EAST FLANK!”, 23)
     * should return the string
     * “CFOPQ IBDFLK XQQXZH BXPQ CIXKH!”
     *
     * Modify the encrypt method to be able to handle both uppercase and lowercase letters.
     * For example, encrypt(“First Legion”, 23) should return “Cfopq Ibdflk”,
     * and encrypt(“First Legion”, 17) should return “Wzijk Cvxzfe”. Be sure to test the encrypt method.
     * @param input
     * @param key
     * @return
     */
    String encrypt(String input,int key){

        StringBuilder encrypted=new StringBuilder(input);

        String alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String converted_alphabets=alphabets.substring(key)+alphabets.substring(0,key);


        for(int i=0;i<encrypted.length();i++){

            char currChar=encrypted.charAt(i);
            char tempCurrChar=Character.toUpperCase(currChar);

            int index = alphabets.indexOf(tempCurrChar);
            if(index!=-1) {

                char charToBeReplaced=converted_alphabets.charAt(index);
                if(Character.isUpperCase(currChar)) {
                    encrypted.setCharAt(i, charToBeReplaced);
                }
                else{
                    encrypted.setCharAt(i,Character.toLowerCase(charToBeReplaced));
                }

            }

        }

        return encrypted.toString();


    }

    /**
     *Write the method encryptTwoKeys that has three parameters, a String named input, and two integers named key1 and key2.
     * This method returns a String that has been encrypted using the following algorithm.
     * Parameter key1 is used to encrypt every other character with the Caesar Cipher algorithm, starting with the first character,
     * and key2 is used to encrypt every other character, starting with the second character.
     * For example, the call encryptTwoKeys(“First Legion”, 23, 17) should return “Czojq Ivdzle”.
     * Note the ‘F’ is encrypted with key 23, the first ‘i’ with 17, the ‘r’ with 23, and the ‘s’ with 17, etc.
     * Be sure to test this method.
     * @param input
     * @param key1
     * @param key2
     * @return
     */
    String encryptTwoKeys(String input,int key1,int key2){

        String encrypted="";
        for(int i=0;i<input.length();i++){

            if(i%2==0){

                String s1=Character.toString(input.charAt(i));
                s1=encrypt(s1,key1);
                encrypted=encrypted+s1;

            }
            else{

                String s1=Character.toString(input.charAt(i));
                s1=encrypt(s1,key2);
                encrypted=encrypted+s1;

            }


        }

        return encrypted;



    }

    /**
     * Write the void method testCaesar that has no parameters.
     * This method should read a file and encrypt the complete file using the Caesar Cipher algorithm,
     * printing the encrypted message.
     */

    void testCaeser(){

        /*FileResource fr=new FileResource();

        String message=fr.asString();

        */
        //String encrypted=encrypt("ATTACKATONCE", 4);



        //System.out.println("Encrypted Message is "+encrypted);
        //System.out.println("Decrypted Message is "+encrypt(encrypted,26-4));


        System.out.println("After encryption "+encryptTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees",23,2));


    }

    public  static void main(String args[]){

        CaeserCipher obj=new CaeserCipher();
        obj.testCaeser();
    }














}
