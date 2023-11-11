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

        //O
        // Scanner sc = new Scanner (System.in);

        // double probabilityOfMute = sc.nextDouble();
        // double probabilityOfAdd = sc.nextDouble();
        // double probabilityOfRemove = sc.nextDouble();

        // Ponto inicialPoint = new Ponto(sc.nextInt(), sc.nextInt());
        // Ponto finalPoint = new Ponto(sc.nextInt(), sc.nextInt());

        // int populationSize = sc.nextInt();

        // int pathSizes[] = new int[populationSize];
        // for(int i = 0; i < populationSize; i++) pathSizes[i] = sc.nextInt();
        // PathPopulation population = new PathPopulation(generator, 100, inicialPoint, finalPoint, pathSizes);

        // Constructor<?> constructor;
        // Class<?> classe;
        // FiguraGeometrica figuraGeometrica;
        // String input;
        // String[] inputArray;
        // ArrayList<FiguraGeometrica> figurasGeometricas = new ArrayList<>();
        // sc.nextLine();
        // while (sc.hasNextLine()) {
        //     input = sc.nextLine();
        //     inputArray = input.split(" ", 2);
        //     try {
        //         classe = Class.forName(inputArray[0]);
        //         constructor = classe.getConstructor(String.class);
        //         figuraGeometrica = (FiguraGeometrica) constructor.newInstance(inputArray[1]);
        //         figurasGeometricas.add(figuraGeometrica);
        //     }
        //     catch (Exception e) {
        //         System.out.println("Tipo de obstaculo desconhecido");
        //         System.exit(0);
        //     }
        // }
        // Planer planer = new Planer(population, figurasGeometricas);
        // System.out.println(planer.toString(planer.getGeneration(generator, 100, probabilityOfMute, probabilityOfAdd, probabilityOfRemove), 0));
        // sc.close();

        //P
        Scanner sc = new Scanner (System.in);
        int numberOfGenerations = sc.nextInt();
        double probabilityOfMute = sc.nextDouble();
        double probabilityOfAdd = sc.nextDouble();
        double probabilityOfRemove = sc.nextDouble();
        Ponto inicialPoint = new Ponto(sc.nextInt(), sc.nextInt());
        Ponto finalPoint = new Ponto(sc.nextInt(), sc.nextInt());
        int populationSize = sc.nextInt();

        int pathSizes[] = new int[populationSize];
        for(int i = 0; i < populationSize; i++) pathSizes[i] = sc.nextInt();
        PathPopulation population = new PathPopulation(generator, 100, inicialPoint, finalPoint, pathSizes);
        
        Constructor<?> constructor;
        Class<?> classe;
        FiguraGeometrica figuraGeometrica;
        String input;
        String[] inputArray;
        ArrayList<FiguraGeometrica> figurasGeometricas = new ArrayList<>();
        sc.nextLine();
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            inputArray = input.split(" ", 2);
            try {
                classe = Class.forName(inputArray[0]);
                constructor = classe.getConstructor(String.class);
                figuraGeometrica = (FiguraGeometrica) constructor.newInstance(inputArray[1]);
                figurasGeometricas.add(figuraGeometrica);
            }
            catch (Exception e) {
                System.out.println("Tipo de obstaculo desconhecido");
                System.exit(0);
            }
        }
        Planer planer = new Planer(population, figurasGeometricas);
        System.out.println(planer.toString(numberOfGenerations, generator, 100, probabilityOfMute, probabilityOfAdd, probabilityOfRemove));
        sc.close();
    }

}
