import java.util.ArrayList;
import java.util.Scanner;

/** A classe cliente é a classe main onde sao realizados os testes pelo cliente
    @Author AfonsoRio
    @version 1.0 21/02/2023
    @inv 
 */

public class Cliente {

    private static Scanner scanner =  new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        ArrayList<Ponto> pointList = new ArrayList<>();
        ArrayList<Retangulo> retanguloList = new ArrayList<>();

        int numeroDePontos = scanner.nextInt();
        for (int i = 0; i < numeroDePontos; i++) { // Constroi uma lista de pontos a partir do input do user
            Ponto ponto = new Ponto(scanner.nextDouble(), scanner.nextDouble());
            pointList.add(ponto);
        }

        int numeroDeRetangulos = scanner.nextInt();
        for (int i = 0; i < numeroDeRetangulos; i++) { // Constroi uma lista de retangulos a partir do input do user
            Ponto A = new Ponto(scanner.nextDouble(), scanner.nextDouble());
            Ponto B = new Ponto(scanner.nextDouble(), scanner.nextDouble());
            Ponto C = new Ponto(scanner.nextDouble(), scanner.nextDouble());
            Ponto D = new Ponto(scanner.nextDouble(), scanner.nextDouble());
            Retangulo Retangulo = new Retangulo(A, B, C, D);
            retanguloList.add(Retangulo);
        }

        Path path  = new Path(pointList);
        System.out.println(path.obstacleIntersections(retanguloList));
    }
}

