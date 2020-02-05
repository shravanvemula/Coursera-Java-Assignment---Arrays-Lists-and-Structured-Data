public class WordPlay
{

    /**
     * Write a method isVowel that has one Char parameter named ch. This method returns true
     * if ch is a vowel (one of 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) and false otherwise.
     * You should write a tester method to see if this method works correctly. For example, isVowel(‘F’)
     * should return false,and isVowel(‘a’) should return true.
     *
     * @param ch
     * @return
     */

     boolean isVowel(char ch){

         ch=Character.toLowerCase(ch);

        if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'){
            return true;
        }
        return false;


    }

    /**
     * Write a method replaceVowels that has two parameters, a String named phrase and a Char named ch.
     * This method should return a String that is the string phrase with all the vowels (uppercase or lowercase)
     * replaced by ch. For example, the call replaceVowels(“Hello World”, ‘*’) returns the string “H*ll* W*rld”.
     * Be sure to call the method isVowel that you wrote and also test this method.

     * @param phrase
     * @param ch
     * @return
     */

    String replaceVowels(String phrase,char ch){



         StringBuilder givenPhrase=new StringBuilder(phrase);
         for(int i=0;i<givenPhrase.length();i++){

             if(isVowel(givenPhrase.charAt(i))){

                    givenPhrase.setCharAt(i,ch);

             }
         }

         return givenPhrase.toString();

    }

    /**
     * Write a method emphasize with two parameters, a String named phrase and a character named ch.
     * This method should return a String that is the string phrase but with the Char ch (upper- or lowercase) replaced by
     * ‘*’ if it is in an odd number location in the string (e.g. the first character has an odd number location but an even index, it is at index 0), or
     * ‘+’ if it is in an even number location in the string (e.g. the second character has an even number location but an odd index, it is at index 1).

     * @param phrase
     * @param ch
     * @return
     */

    String emphasize(String phrase,char ch){


         StringBuilder replacedString=new StringBuilder(phrase);

        for(int i=0;i<phrase.length();i++){

            if(Character.toUpperCase(phrase.charAt(i))==ch || Character.toLowerCase(phrase.charAt(i))==ch ) {

                if (i % 2 == 0) {


                    replacedString.setCharAt(i, '*');

                } else if (i % 2 == 1) {

                    replacedString.setCharAt(i, '+');

                }
            }

        }

        return replacedString.toString();


    }




    void tester(){

         if(isVowel('A')){
             System.out.println("It is a Vowel");

         }
         else
         {
             System.out.println("It is not a vowel");
         }


         String replacedString=replaceVowels("Helloworld",'*');
         System.out.println("Replaced String is "+replacedString);



         System.out.println("Emphasized String is "+emphasize("dna ctgaaactga",'a'));

    }


    public static void main(String args[]){

         WordPlay obj=new WordPlay();
         obj.tester();

    }









}
