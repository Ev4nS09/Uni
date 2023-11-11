/** EncrypterSmith is a "method class" this means it only as methods and not a constructor. The class has 2 public 
 *  methods wich, encrypt and decrypt a message in a specific way.
 *  @author Afonso Rio
 *  @version 1.0 13/03/2022
 */

public class EncrypterSmith {
 
    /**This method checks if the an input is valid or not only in the method 'Encrypt'
     * @param n number of "rotations" to the left or right
     * @param p number of rotations in the ascii table
     * @throws Exception
     */
    private static void isInputValid(int n, int p) throws Exception{
        if(n < -9 || n > 9) throw new IllegalArgumentException("n must be between -9 and 9");
        if(p < -4 || p > 4) throw new IllegalArgumentException("p must be between -4 and 4");
    }

    /**setString sets a 'character' in the espesific 'index' of a 'string'
     * @param string string that will be modified
     * @param index index of the character we want to modify
     * @param character character we want to put in th 'string'
     * @return returns the string with the new character
     */
    private static String setString(String string, int index, char character){
        String result = "";
        for(int i = 0; i < string.length(); i++){
            if(i == index) result += character;
            else result += string.charAt(i);
        }
        return result;
    }

    /** The method 'Encrypt', encrypts a 'message'. rotating it 'n' to left or right and the 'message' also rotatos 'p'
     * in the ascii table
     * @param message message we want to encrypt
     * @param n number of rotations to the left or right
     * @param p number of ratations in the ascii table
     * @return return the encrypted message
     * @throws Exception
     */
    public static String Encrypt(String message, int n, int p) throws Exception{
        isInputValid(n, p);                 //Checks if n is between -9 and 9, and checks if p is between -4 and 4
        String messageEncrypt = message;                       
        String result = "";
        while(n < 0) n = n + message.length();                  //If n is negetive we need to "invert it" so we can go backwords
        for(int i = 0; i < message.length(); i++) {messageEncrypt = setString(messageEncrypt,((i+n)%message.length()), message.charAt(i));}  
        for(int i = 0; i < message.length(); i++) {result += (char) (messageEncrypt.charAt(i) + p);}
        return result;
    }

    /**Decrypts a 'message', it is only a valid encrypted message in the first 3 characters form the word 'bug'
     * Note: This encryption only pertmits n to be  between -9 and 9, and only permits p to be between -4 and 4,
     * @param message message we want to decrypt
     * @return return the message but it is decrypted
     * @throws Exception
     */
    public static String Dencrypt(String message) throws Exception{
        for(int i = -9; i <= 9; i++){
            for(int j = -4; j <= 4; j++){
                String encryptedMessage = Encrypt(message, i, j);
                if(encryptedMessage.substring(0, 3).toLowerCase().equals("bug")) return encryptedMessage;
            }
        }
        throw new Error("Invalid Message, the first 3 letters of the message must form the word 'bug'");
    }


}
