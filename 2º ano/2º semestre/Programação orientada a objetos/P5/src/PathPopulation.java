import java.util.ArrayList;
import java.util.Random;

/** A classe PathPopulation como indica é uma população de trajetorias ou seja armazena-as e tem os seus devidos metodos
    @Author AfonsoRio
    @version 2.0 25/03/2023
    @inv
 */
public class PathPopulation {
    private ArrayList<Path> population = new ArrayList<>();

    /**Contrutor que armazena as trajetorias na lista "trajectoryList"
     * @param trajectoryList lista de trajetorias
     */
    public PathPopulation(ArrayList<Path> trajectoryList){
        this.population = trajectoryList;
    }
    
    /**Contrutor vazio
     */
    public PathPopulation(){}

    /**Construtor que gera paths aleatorios e os armazena
     * @param generator Random number generator
     * @param bound bound of the generator
     * @param pathPopulationSize Numero inteiro
     * @param pathSize Numero inteiro
     */
    public PathPopulation(Random generator, int bound, int pathPopulationSize, int pathSize){
        for (int i = 0; i < pathPopulationSize; i++) {
            Path path = new Path();
            path.addRandomPoints(generator, bound, pathSize);
            add(path);
        }
    }


    /**Simula um torneio escolhendo aleatoriamente os paths da população e verificando qual é melhor com o metodo "getEvaluation()"
     * @param generator Random number generator
     * @param figurasGeometricas Obstaculos
     * @return Retorna uma lista com as vencedoras do torneio
     */
    public PathPopulation getTournamentResults(Random generator, ArrayList<FiguraGeometrica> figurasGeometricas){
        PathPopulation pathPopulation = new PathPopulation();
        int n = population.size();
        for(int i = 0; i < n; i++){
            Path a = population.get(generator.nextInt(n));
            Path b = population.get(generator.nextInt(n));
            if(a.getEvaluation(figurasGeometricas) >= b.getEvaluation(figurasGeometricas))
                pathPopulation.add(a);
            else
                pathPopulation.add(b);
        }
        return pathPopulation;
    }


    /**Adiciona uma trajetoria á população
     * @param a
     */
    public void add(Path a){
        population.add(a);
    }

    /**Imprime as trajetorias armazenadas na população
     */
    public void printPaths(){
        for(int i = 0; i < population.size(); i++)
            System.out.println(population.get(i));
    }

    @Override
    public String toString(){
        String result = "";
        for(int i = 0; i < population.size()-1; i++){
            result += population.get(i).toString();
            result += '\n';
        }
        result += population.get(population.size()-1).toString();
        return result;
    }
}
