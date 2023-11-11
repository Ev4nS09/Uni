import java.util.ArrayList;
import java.util.Random;

/** A classe PathPopulation como indica é uma população de trajetorias ou seja armazena-as e tem os seus devidos metodos
    @Author AfonsoRio
    @version 1.0 19/03/2023
    @inv
 */
public class PathPopulation {
    private ArrayList<Path> trajectoryList = new ArrayList<>();

    /**Contrutor que armazena as trajetorias na lista "trajectoryList"
     * @param trajectoryList lista de trajetorias
     */
    public PathPopulation(ArrayList<Path> trajectoryList){
        this.trajectoryList = trajectoryList;
    }
    
    /**Contrutor vazio
     */
    public PathPopulation(){}

    public PathPopulation(Random generator, int bound, int pathPopulationSize, int pathSize){
        for (int i = 0; i < pathPopulationSize; i++) {
            Path path = new Path();
            for(int j = 0; j < pathSize; j++)
                path.add(new Ponto(generator.nextInt(bound), generator.nextInt(bound)));
            add(path);
        }
    }

    // public PathPopulation getTournamentResults(Random generator, ArrayList<FiguraGeometrica> figurasGeometricas){
    //     ArrayList<Path> result = new ArrayList<>();
    //     int n = trajectoryList.size();
    //     for(int i = 0; i < n; i++){
    //         Path a = trajectoryList.get(generator.nextInt(n-1));
    //         Path b = trajectoryList.get(generator.nextInt(n-1));
    //         if(a.getEvaluation(figurasGeometricas) >= b.getEvaluation(figurasGeometricas))
    //             result.add(a);
    //         else
    //             result.add(b);
    //     }
    //     return new PathPopulation(result);
    // }


    /**Adiciona uma trajetoria á população
     * @param a
     */
    public void add(Path a){
        trajectoryList.add(a);
    }

    /**Imprime as trajetorias armazenadas na população
     */
    public void printPaths(){
        for(int i = 0; i < trajectoryList.size(); i++)
            System.out.println(trajectoryList.get(i));
    }
}
