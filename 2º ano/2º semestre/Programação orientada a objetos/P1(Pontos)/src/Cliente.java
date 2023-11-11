import java.util.Scanner;

public class Cliente {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //A
        Ponto a = new Ponto(scanner.nextDouble(), scanner.nextDouble());
        Ponto b = new Ponto(scanner.nextDouble(), scanner.nextDouble());
        System.out.println((int) a.dist(b));

        //B
        Ponto c = new Ponto(scanner.nextDouble(), scanner.nextDouble(), true);
        Ponto d = new Ponto(scanner.nextDouble(), scanner.nextDouble(), true);
        System.out.println((int) c.dist(d));

        //C
        Path path = new Path();
        System.out.println(path.returnPath());

        
    }
}
