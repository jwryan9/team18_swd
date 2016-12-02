import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * RunDecryptor takes an encrypted message and prints the original message.
 * @author Adam Gary
 * @version 1.0
 *
 */
public class RunDecryptor {
    /**
     * character array to hold the alphabet.
     */
    final char alphabet [] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
    /**
     * string that holds the encrypted message.
     */
    private String encryptedString = "";
    /**
     * character array that holds the encrypted message by each letter.
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


    public RunDecryptor(String id, int startPosition) throws FileNotFoundException{

        this.startPosition = startPosition;
        encryptedString = id.toUpperCase();
        charCipher = new char[encryptedString.length()];
        charCipher = encryptedString.toCharArray();
        String keyFileLocation = "keys.txt";

        try {
        File keyFile = new File(keyFileLocation);
        Scanner fileScan = new Scanner(keyFile);
        //startPosition = Integer.parseInt(fileScan.nextLine()); // parse the starting position to make it a integer
        String keysFromFileString = fileScan.nextLine();
        fileScan.close();

        keys = new int[id.length()]; // create an integer array the length of the message without spaces
        String[] keyArray = keysFromFileString.split(",");
        int j = startPosition;

            for (int i = 0; i < encryptedString.length(); i++) { // loop through the character with no commas
                keys[i] = Integer.parseInt(keyArray[j]); // parse the keys from string to integers and input them into the keys array
                j = j + 1; // increment the position in the keyArray
                if (j == keyArray.length) { // if the position in the key array is the length of the keys start back at the 0 key position
                    j = 0;
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("IOException decrytper");
        }
    }

    /**
     * Method that decodes the encrypted message and prints the original message to the screen.
     */
    public String decodeMessage(){ // function to decode the messages
        int cipherN;
        int keyCounter = 0;
        for(int i = 0;i < charCipher.length;i++){ // loop through each term in the cipher message
            char currentChar = charCipher[i];
            if(currentChar == ' '){ // if there is a space in the message add a space to the charCipher array
                charCipher[i] = ' ';
            }
            else { // if there is not a space

                for (int j = 0; j < alphabet.length; j++) { // loop through the alphabet to get the new value for each number
                    if (currentChar == alphabet[j]) {
                        cipherN = j - keys[keyCounter]; // the cipher number minus the key value is the new character
                        if (cipherN < 0) {
                            while (cipherN < 0) {
                                cipherN = cipherN + 36; // if the key is over 26 reduce it down to the value needed to decrypted
                            }
                        }
                        charCipher[i] = alphabet[cipherN]; // set the char cipher character to the decoded character
                    }
                }

                keyCounter = keyCounter + 1; // go to the next key
            }
        }
        System.out.println(charCipher); // print the decrypted message
        return new String(charCipher);
    }

}
