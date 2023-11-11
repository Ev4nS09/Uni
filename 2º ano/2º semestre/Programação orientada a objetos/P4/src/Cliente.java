import java.util.Scanner;
import java.util.*;

/** A classe cliente é a classe main onde sao realizados os testes pelo cliente
    @Author AfonsoRio
    @version
    @inv 
 */

public class Cliente {

    private static Scanner sc =  new Scanner(System.in);
    public static void main(String[] args){
        Random generator = new Random(0);
        int pathManagerSize = sc.nextInt();
        PathPopulation pathManager = new PathPopulation(); 
        for (int i = 0; i < pathManagerSize; i++) {
            Path path = new Path();
            int pathSize = sc.nextInt();
            for(int j = 0; j < pathSize; j++)
                path.add(new Ponto(generator.nextInt(100), generator.nextInt(100)));
            pathManager.add(path);
        }
        pathManager.printPaths();
        sc.close();
    }
}

