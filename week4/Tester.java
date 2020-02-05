import edu.duke.FileResource;

public class Tester {
    /**
     * You should test this CaesarCipher in a tester class that creates a CaesarCipher object and attempts to encrypt
     * and decrypt an entire message (such as titus-small.txt), as well as individual characters.
     */
    public void testCaesarCipher(){

        CaesarCipher obj=new CaesarCipher(15);
        FileResource fr=new FileResource();
        String message=fr.asString();

        String enpryted=obj.encrypt(message);
        System.out.println("Enctypted Messags is "+enpryted);

        String decrypted=obj.decrypt(enpryted);
        System.out.println("The Decrypted Message is "+decrypted);

        System.out.println(obj.decryptLetter('N'));

    }

    /**
     * You can test these methods in the tester class by making a new CaesarCracker object and decrypting the file titus-small_key5.txt
     * using no arguments for the constructor (default most common character ‘e’), and the file oslusiadas_key17.txt, noting that the most
     * common character in Portuguese is ‘a’.
     */
    public void testCaesarCracker(){

        CaesarCracker engObj=new CaesarCracker();
        FileResource fr=new FileResource();
        String encrypted=fr.asString();
        String decrypted=engObj.decrypt(encrypted);
        System.out.println("The Decrypted Message is "+decrypted);




    }

    /**
     * Testing VigenereCipher's methods in the tester class by creating a VigenereCipher object
     * with the key “rome”, which is {17, 14, 12, 4} in integers and encrypting and decrypting
     * the file titus-small.txt, the encrypted first line of which is “Tcmp-pxety mj nikhqv htee mrfhtii tyv”.
     */
    public void testVigenereCipher(){
        int[] keys={17,14,12,4};
        VigenereCipher obj=new VigenereCipher(keys);
        FileResource fr=new FileResource();
        String message=fr.asString();
        String encrypted=obj.encrypt(message);
        System.out.println("The encrypted Message is "+encrypted);

        String decrypted=obj.decrypt(encrypted);
        System.out.println("The decrypted Message is "+decrypted);
    }

    /**
     * Testing VigenereCipher's methods in the tester class by creating a VigenereCipher object
     */
    public void testVigenereBreaker(){

        VigenereBreaker obj=new VigenereBreaker();
        obj.breakVigenere();


    }


    public static void main(String args[]){
        Tester obj=new Tester();
        //obj.testCaesarCipher();
        //obj.testCaesarCracker();
        // obj.testVigenereCipher();
        obj.testVigenereBreaker();

    }
}
