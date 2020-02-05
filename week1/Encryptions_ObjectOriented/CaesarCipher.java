/**
 * In this assignment, you will put together the CaesarCipher class from the lesson and add a decrypt method to decrypt with the same key.
 * In addition you will create a second class, TestCaesarCipher to test examples that use the CaesarCipher class, including writing a method that
 * will automatically decrypt an encrypted file by determining the key and then decrypting with that key.
 */



public class CaesarCipher
{
    /**
     * Create the CaesarCipher class with the following parts:
     *
     *     Private fields for the alphabet and shiftedAlphabet
     *     Write a constructor CaesarCipher that has one int parameter key.
     *     This method should initialize all the private fields of the class.
     */
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key){

        alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet=alphabet.substring(key)+alphabet.substring(0,key);
        mainKey=key;
    }

    /**
     *  Write an encrypt method that has one String parameter named input.
     *  This method returns a String that is the input encrypted using shiftedAlphabet.
     * @param input
     * @return
     */

    public String encrypt(String input){

        StringBuilder encrypted=new StringBuilder(input);

        for(int i=0;i<encrypted.length();i++){

            char currChar=input.charAt(i);
            char tempCurrChar=Character.toUpperCase(currChar);

            int index = alphabet.indexOf(tempCurrChar);
            if(index!=-1) {

                char charToBeReplaced=shiftedAlphabet.charAt(index);
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
     * Write a decrypt method that has one String parameter named input. This method returns a String that is the
     * encrypted String decrypted using the key associated with this CaesarCipher object.
     * One way to do this is to create another private field mainKey, which is initialized to be the value of key.
     * @param encrypted
     * @return
     */



    public String decrypt(String encrypted){

        CaesarCipher cc=new CaesarCipher(26-mainKey);
        String decrypted=cc.encrypt(encrypted);

        return decrypted;


    }

    public static void main(String args[]){


        CaesarCipher cc=new CaesarCipher(23);
        String message="ATTACKATONCE";
        String encrypted=cc.encrypt(message);
        System.out.println(("Encrypted Message is "+encrypted));

        String decrypted=cc.decrypt(encrypted);
        System.out.println(("Decrypted Message is "+decrypted));


    }






}
