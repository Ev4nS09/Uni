import java.util.ArrayList;
import java.util.Random;

/** A classe PathPopulation como indica é uma população de trajetorias ou seja armazena-as e tem os seus devidos metodos
    @Author AfonsoRio
    @version 3.0 29/03/2023
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

    /**Adiciona uma trajetoria á população
     * @param a
     */
    public void add(Path a){
        population.add(a);
    }

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

    /**Cria uma população de dois paths. Esses paths sao a recombinação do inicio de um index de path "A" e o final de um path "B",
     * o segundo path é igual ao primeiro mas ao contrario sendo primeiro o path "B" e em seguida o path "A"
     * @param generator Random generator
     * @param A Path
     * @param B Path
     * @return uma população de dois paths com a recombinação de esses dois paths
     */
    public PathPopulation createRecombinationPopulation(Random generator, Path A, Path B){
        PathPopulation result = new PathPopulation();
        int indexCutA = generator.nextInt(A.getSize()-2) + 1;
        int indexCutB = generator.nextInt(B.getSize()-2) + 1;
        result.add(A.getSplitPath(0, indexCutA).conectPaths(B.getSplitPath(indexCutB+1)));
        result.add(B.getSplitPath(0, indexCutB).conectPaths(A.getSplitPath(indexCutA+1)));
        return result;
    }

    /**Muta um ponto aleatório todos os elementos da população
     * @param generator Random generator
     * @param bound Inteiro que diz o limite do valor do ponto aleatório
     * @param probability Double que é a probabilidade de ocorrer motação
     */
    public void mutePopulation(Random generator, int bound, double probability){
        for(int i = 0; i < this.population.size(); i++)
            this.population.get(i).mutePath(generator, bound, probability);
    }

    /**Adiciona um ponto aleatorio a todos os elementos da população
     * @param generator Random generator
     * @param bound Inteiro que diz o limite do valor do ponto aleatório
     * @param probability Double que é a probabilidade de ocorrer motação
     */
    public void addRandomPositionPopulation(Random generator, int bound, double probability){
        for(int i = 0; i < this.population.size(); i++)
            this.population.get(i).addRandomPosition(generator, bound, probability);
    }

    /**Remove um ponto aleatório de todos os elementos da população
     * @param generator Random generator
     * @param bound Inteiro que diz o limite do valor do ponto aleatório
     * @param probability Double que é a probabilidade de ocorrer motação
     */
    public void removeRandomPointsFromPopulation(Random generator, int bound, double probability){
        for(int i = 0; i < this.population.size(); i++)
            this.population.get(i).removeRandomPoint(generator, bound, probability);
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
