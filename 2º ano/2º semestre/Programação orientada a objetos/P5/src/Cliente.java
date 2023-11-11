import java.lang.reflect.*;
import java.util.*;

/** A classe cliente é a classe main onde sao realizados os testes pelo cliente
    @Author AfonsoRio
    @version 1.0 21/02/2023
    @inv 
 */
 public class Cliente {

    public static final Random generator = new Random(0);

    public static String capital(String s) {
        String res = s.toLowerCase();
        Character initial = Character.toUpperCase(res.charAt(0));
        StringBuilder sb = new StringBuilder(res);
        sb.setCharAt(0, initial);
        final String answer = sb.toString();
        return answer;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner (System.in);

        //I
        // String Pontos[] = sc.nextLine().split(" ");
        // Path path = new Path();
        // for(int i = 0; i < Pontos.length - 1; i+=2)
        //     path.add(new Ponto(Integer.parseInt(Pontos[i]), Integer.parseInt(Pontos[i+1])));
        // Constructor<?> constructor;
        // Class<?> cs;
        // FiguraGeometrica f;
        // String input;
        // String[] inputArray;
        // ArrayList<FiguraGeometrica> figuraGeometricas = new ArrayList<>();
        // while(sc.hasNextLine()){
        //     try{
        //         input = sc.nextLine();
        //         inputArray = input.split(" ", 2);
        //         cs = Class.forName(inputArray[0]);
        //         constructor = cs.getConstructor(String.class);
        //         f = (FiguraGeometrica) constructor.newInstance(inputArray[1]);
        //         figuraGeometricas.add(f);
        //     }
        //     catch (Exception e) {
        //         System.out.println("Tipo de obstaculo desconhecido");
        //         System.exit(0);
        //     }
        // }
        // double output = path.getEvaluation(figuraGeometricas);
        // System.out.printf("%.2f\n", output);
        // sc.close();

        //J
        Ponto inicialPoint = new Ponto(sc.nextDouble(), sc.nextDouble());
        Ponto finalPoint = new Ponto(sc.nextDouble(), sc.nextDouble());
        PathPopulation population = new PathPopulation();
        int populationSize = sc.nextInt();
        for(int i = 0; i < populationSize; i++){
            int numberOfPoints = sc.nextInt();
            Path path = new Path();
            path.add(inicialPoint);
            path.addRandomPoints(generator, 100, numberOfPoints);
            path.add(finalPoint);
            population.add(path);
        }
        Constructor<?> constructor;
        Class<?> cl;
        FiguraGeometrica f;
        String s;
        String [] aos;
        ArrayList<FiguraGeometrica> figurasGeometricas = new ArrayList<>();
        sc.nextLine();
        while (sc.hasNextLine()) {
            s = sc.nextLine();
            aos = s.split(" ", 2);
            try {
                cl = Class.forName(capital(aos[0]));
                constructor = cl.getConstructor(String.class);
                f = (FiguraGeometrica) constructor.newInstance(aos[1]);
                figurasGeometricas.add(f);
            }
            catch (Exception e) {
                System.out.println("Tipo de obstaculo desconhecido");
                System.exit(0);
            }
        }
        System.out.println(population.getTournamentResults(generator, figurasGeometricas).toString());
        sc.close();
    }
}
