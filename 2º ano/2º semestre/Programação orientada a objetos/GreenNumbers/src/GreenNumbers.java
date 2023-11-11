import java.math.BigInteger;

public class GreenNumbers {

    // public static int numberSize(int n){
    //     int result = 0;
    //     while(n > 0){ n/=10; result++;}
    //     return result;
    // }

    public static boolean checkIfGreen(BigInteger n){
        String stringFinalNumber = n.pow(2).toString();
        String lastDigits = "";
        for(int i = 0; i < n.toString().length(); i++){
            lastDigits = stringFinalNumber.charAt(stringFinalNumber.length() - i - 1) + lastDigits;
        }
        if((new BigInteger(lastDigits)).equals(n)) return true;
        else return false;
     }
    
    // public static BigInteger get(int n) {
    //     assert(n <= 5000);
    //     BigInteger number = new BigInteger(Integer.toString(n));
    //     BigInteger finalNumber = number.multiply(number);
    //     return getGreenNumber(n, finalNumber);
    // }

    // public static boolean checkIfGreen(BigInteger n) {
    //     BigInteger finalNumber = number.multiply(number);
    //     if(getGreenNumber(n.equals(number)) return true;
    //     else return false;
    // }

    public static int checkThePower(BigInteger n, String number){
        int result = 1;
        BigInteger numberBig = new BigInteger(number);
        while(!n.equals(numberBig.pow(result))){
            if(!n.max(numberBig.pow(result)).equals(n)){
                result = -1;
                break;
            }
            result++;
        }
        return result;
    }

    public static BigInteger returnsNTHvalue(int n){
        BigInteger one = new BigInteger("1");
        BigInteger result = new BigInteger("0");
        while(n > 0){
            result = result.add(one);
            if(checkIfGreen(result)){
                System.out.println("---------------------------------");
                System.out.println(result);
                System.out.println(checkThePower(result, "5"));
                System.out.println(checkThePower(result, "6"));
                n--;
                System.out.println("---------------------------------");
            }
        }
        return result;
    }

    // public static void testprint(int nth){
    //     while(nth > 0){

    //     }
    // }

    public static BigInteger get(int n) {
        assert(n <= 5000);
        BigInteger result;
        if(n == 1) return new BigInteger("1");
        else if(n % 2 == 0) result = new BigInteger("5");
        else result = new BigInteger("6");
        while(n > 3){
            result = result.multiply(result);
            n -= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        // System.out.println(checkThePower(new BigInteger("5"), "5"));
        System.out.println(returnsNTHvalue(10));
    }
}