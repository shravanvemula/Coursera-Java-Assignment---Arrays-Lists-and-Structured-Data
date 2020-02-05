/**
 * In this assignment, you will put together the CaesarCipherTwo class that encrypts a message with two keys (the same way as the previous lesson:
 *  key1 is used to encrypt every other letter, starting with the first, and key2 is used to encrypt every other letter, starting with the second),
 *  and also decrypts the same message.
 * In addition you will create a second class, TestCaesarCipherTwo to test examples that use the CaesarCipherTwo class, including writing a
 * method that will automatically decrypt an encrypted file by determining the two keys that were used to encrypt it.
 */

public class CaesarCipherTwo
{
    /**
     * Include private fields for the alphabet, shiftedAlphabet1, and shiftedAlphabet2.
     * Write a constructor CaesarCipherTwo that has two int parameters key1 and key2.
     * This method should initialize all the private fields.
     */


    private String alphabet;
    private String shiftedAlphabet1;
    private  String shiftedAlphabet2;
    private  int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1,int key2){

        alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1=alphabet.substring(key1)+alphabet.substring(0,key1);
        shiftedAlphabet2=alphabet.substring(key2)+alphabet.substring(0,key2);
        mainKey1=key1;
        mainKey2=key2;

    }

    /**
     * Write an encrypt method that has one String parameter named input.
     * This method returns a String that is the input encrypted using the two shifted alphabets.
     * @param input
     * @return
     */

    String encrypt(String input){

        String encrypted="";

        CaesarCipher cc1=new CaesarCipher(mainKey1);
        CaesarCipher cc2=new CaesarCipher(mainKey2);

        for(int i=0;i<input.length();i++){

            if(i%2==0){

                String s1=Character.toString(input.charAt(i));
                s1=cc1.encrypt(s1);
                encrypted=encrypted+s1;

            }
            else{

                String s1=Character.toString(input.charAt(i));
                s1=cc2.encrypt(s1);
                encrypted=encrypted+s1;

            }


        }
        return encrypted;

    }

    /**
     * Write a decrypt method that has one String parameter named input. This method returns a String that is the encrypted String decrypted using the key1 and key2
     * associated with this CaesarCipherTwo object. You might want to add more private fields to the class.
     * @param encrypted
     * @return
     */

    String decrypt(String encrypted){


        String decrypted="";

        CaesarCipher cc1=new CaesarCipher(mainKey1);
        CaesarCipher cc2=new CaesarCipher(mainKey2);

        for(int i=0;i<encrypted.length();i++){

            if(i%2==0){

                String s1=Character.toString(encrypted.charAt(i));
                s1=cc1.decrypt(s1);
                decrypted=decrypted+s1;

            }
            else{

                String s1=Character.toString(encrypted.charAt(i));
                s1=cc2.decrypt(s1);
                decrypted=decrypted+s1;

            }


        }
        return decrypted;


    }


    public static void main(String args[]) {


        CaesarCipherTwo cc = new CaesarCipherTwo(23, 17);
        String message = "First Legion";
        String encrypted = cc.encrypt(message);
        System.out.println(("Encrypted Message is " + encrypted));

        String decrypted = cc.decrypt(encrypted);
        System.out.println(("Decrypted Message is " + decrypted));
    }




}
