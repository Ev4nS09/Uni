import java.lang.reflect.*;
import java.util.*;

/** A classe cliente é a classe main onde sao realizados os testes pelo cliente
    @Author AfonsoRio
    @version 1.0 21/02/2023
    @inv 
 */
 public class Cliente {

    public static final Random generator = new Random(0);

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner (System.in);

        //K
        Path pathA = new Path(sc.nextLine());
        Path pathB = new Path(sc.nextLine());
        PathPopulation pathPopulation = new PathPopulation();
        System.out.println(pathPopulation.createRecombinationPopulation(generator, pathA, pathB));

        //L
        // PathPopulation pathPopulation = new PathPopulation();
        // int populationSize = sc.nextInt();
        // for(int i = 0; i < populationSize; i++){
        //     Path path = new Path();
        //     path.addRandomPoints(generator, 100, sc.nextInt());
        //     pathPopulation.add(path);
        // }
        // double probability = sc.nextDouble();
        // pathPopulation.mutePopulation(generator, 100, probability);
        // System.out.println(pathPopulation);

        //M
        // PathPopulation pathPopulation = new PathPopulation();
        // int populationSize = sc.nextInt();
        // for(int i = 0; i < populationSize; i++){
        //     Path path = new Path();
        //     path.addRandomPoints(generator, 100, sc.nextInt());
        //     pathPopulation.add(path);
        // }
        // double probability = sc.nextDouble();
        // pathPopulation.addRandomPositionPopulation(generator, 100, probability);
        // System.out.println(pathPopulation);

        //N
        // PathPopulation pathPopulation = new PathPopulation();
        // int populationSize = sc.nextInt();
        // for(int i = 0; i < populationSize; i++){
        //     Path path = new Path();
        //     path.addRandomPoints(generator, 100, sc.nextInt());
        //     pathPopulation.add(path);
        // }
        // double probability = sc.nextDouble();
        // pathPopulation.removeRandomPointsFromPopulation(generator, 100, probability);
        // System.out.println(pathPopulation);
        
        sc.close();

    }
}
