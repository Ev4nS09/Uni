import java.util.Scanner;

/**Craftsman is the class where the user will be doing the tests with the class 'encrypterSmith'
 * @author Afonso Rio
 * @version 1.0 13/03/2023
 */
public class Craftsman  {
    private static Scanner scanner =  new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int n = scanner.nextInt(); int p = scanner.nextInt();
        String message = scanner.next();
        if(n == 0 && p == 0) System.out.println(EncrypterSmith.Dencrypt(message));
        else System.out.println(EncrypterSmith.Encrypt(message, n, p)); 
    }
}
