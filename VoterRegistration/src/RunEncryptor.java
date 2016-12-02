import java.io.*;
import java.util.Scanner;

/**
 * @author Adam Gary
 * @version 1.0
 *
 */
public class RunEncryptor {
    /**
     * character array to hold the alphabet.
     */
    final char alphabet [] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
    /**
     * string that holds the user entered message.
     */
    private String idString = "";
    /**
     * character array that holds the user entered message by each letter.
     */
    private char[] charMessage;
    /**
     * character array that holds each encrypted letter.
     */
    private char[] charCipher;
    /**
     * integer array that holds the keys for each character in the message string.
     */
    private int[] keys;
    /**
     * integer that holds the starting position in the key file.
     */
    int startPosition;


     /**
     * Constructor to initialize the RunEncryptor variables.
     * @param id
     * @throws IOException
     */
    public RunEncryptor(String id) throws IOException{

        String keyFileLocation = "keys.txt";

        try{
            File keyFile = new File(keyFileLocation);
            Scanner fileScan = new Scanner(keyFile);
            startPosition = Integer.parseInt(fileScan.nextLine()); // parse the starting position to make it a integer
            String keysFromFileString = fileScan.nextLine();
            fileScan.close();

            keys = new int[id.length()]; // create an integer array the length of the message without spaces
            String[] keyArray = keysFromFileString.split(",");
            int j = startPosition;

            for(int i = 0;i<id.length();i++){ // loop through the keyArray
                keys[i]=Integer.parseInt(keyArray[j]); // enter the integer value of each key into the key array
                j=j+1; // go to the next key from the key file
                if(j == keyArray.length){ // if the end of the key file is reached restart at the key in the 0 position
                    j = 0;
                }
            }



            FileWriter fWriter = new FileWriter(keyFile);
            PrintWriter printKeys = new PrintWriter(fWriter);
            printKeys.println(Integer.toString(j)); // update the start position of the key file
            printKeys.println(keysFromFileString);  // print the string of keys into the key file
            printKeys.close();

            //keys = keyArray;
            //startPosition = startPos;
            idString = id.toUpperCase();
            charCipher = new char[idString.length()];
            charMessage = idString.toCharArray();
        }
        catch (FileNotFoundException e){
            System.out.println("Key File not Found");
        }

    }



    /**
     * Method to encode the users message and print the message to a new encrypted file.
     */
    public String encodeMessage() { // function to encode the message
        int cipherN;
        int n;
        int keyCounter = 0;
        for(int i = 0;i < charMessage.length;i++){ // loop through the message
            char currentChar = charMessage[i];
            if(currentChar == ' '){ // if there is a space in the message add a space to the cipher message array
                charCipher[i] = ' ';
            }
            else{
                for(int j = 0;j < alphabet.length;j++) { // loop through the alphabet array
                    if (currentChar == alphabet[j]) { // if the current character matches one in the alphabet
                        n = keys[keyCounter]; // the n value is updated to the key counter value
                        cipherN = j + n; // the cipher n is the current place in the alphabet plus the key value
                        if (cipherN > 25) { // if the key is over 25 then reduce it to the actual place in the alphabet array
                            cipherN = cipherN % 26;
                        }
                        charCipher[i] = alphabet[cipherN]; // update the cipher message with the correct character
                        keyCounter = keyCounter + 1; // go to the next key
                    }
                }
            }
        }
        System.out.println("ID: " + idString); // print the message
        System.out.print("Keys: " + keys[0]); // print the keys
        for(int k=1; k<keys.length;k++){
            System.out.print("," + keys[k]);
        }
        System.out.println("\nCipher Text: "+ new String(charCipher)); // print the cipher text
        String encryptedID = new String(charCipher);
        return (encryptedID + "," + startPosition);


    }
}
