import java.util.ArrayList;
import java.util.Scanner;

public class Path {

    private ArrayList<Ponto> pointList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void readPath() {
        int numeroDePontos = scanner.nextInt();
        for (int i = 0; i < numeroDePontos; i++) { // Constroi uma lista de pontos a partir do input do user
            Ponto a = new Ponto(scanner.nextDouble(), scanner.nextDouble());
            pointList.add(a);
        }
    }
    

    public double returnPath() {
        double result = 0;
        for (int i = 0; i < pointList.size() - 1; i++) { // Calcula a distancia de um ponto ao outra passando por todos os pontos da lista
            result += pointList.get(i).dist(pointList.get(i + 1));
        }
        return result;
    }

}
