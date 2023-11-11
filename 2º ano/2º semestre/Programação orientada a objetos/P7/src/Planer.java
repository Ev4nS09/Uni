import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

public class Planer{
    
    private PathPopulation population;
    private ArrayList<FiguraGeometrica> figurasGeometricas;

    /**Construtor que armazena uma população e uma lista de obstaculos
     * @param population População
     * @param figurasGeometricas Lista de obstaculos
     */
    public Planer(PathPopulation population, ArrayList<FiguraGeometrica> figurasGeometricas){
        this.population = population;
        this.figurasGeometricas = figurasGeometricas;
    }

    /**Calcula a proxima geração de uma população e retorna-a
     * @param generator Random number generator
     * @param bound bound of the generator
     * @param probabilityOfMute Probabilidade de ocorrer mutação na populaçao
     * @param probabilityOfAdd Probabilidade de ocorrer uma adição na populaçao
     * @param probabilityOfRemove Probabilidade de ocorrer uma remoção na populaçao
     * @return a proxima geração de uma população
     */
    public PathPopulation getGeneration(Random generator, int bound, double probabilityOfMute, double probabilityOfAdd, double probabilityOfRemove){
        PathPopulation result = new PathPopulation();
        PathPopulation tournamentPopulation = this.population.getTournamentResults(generator, this.figurasGeometricas);
        int tournamentPopulationSize = tournamentPopulation.getSize();
        while(result.getSize() < tournamentPopulationSize){
            Path A = tournamentPopulation.getPath(generator.nextInt(tournamentPopulationSize));
            Path B = tournamentPopulation.getPath(generator.nextInt(tournamentPopulationSize));
            PathPopulation recombination = this.population.createRecombinationPopulation(generator, A, B);
            result.add(recombination.getPath(0));
            if(result.getSize() == tournamentPopulationSize) break;
            result.add(recombination.getPath(1));
        }
        result.mutePopulation(generator, bound, probabilityOfMute);
        result.addRandomPositionPopulation(generator, bound, probabilityOfAdd);
        result.removeRandomPointsFromPopulation(generator, bound, probabilityOfRemove);
        return result;
    }

    /**Cria N gerações da população this
     * @param generationNumber Numero de gerações
     * @param generator Random number generator
     * @param bound bound of the generator
     * @param probabilityOfMute Probabilidade de ocorrer mutação na populaçao
     * @param probabilityOfAdd Probabilidade de ocorrer uma adição na populaçao
     * @param probabilityOfRemove Probabilidade de ocorrer uma remoção na populaçao
     * @return uma lista de populações com N gerações
     */
    public ArrayList<PathPopulation> getAllGeneration(int generationNumber, Random generator, int bound, double probabilityOfMute, double probabilityOfAdd, double probabilityOfRemove){;
        PathPopulation newPopulation = this.population;
        ArrayList<PathPopulation> result = new ArrayList<>();
        for(int i = 0; i < generationNumber; i++){
            newPopulation = new Planer(newPopulation, this.figurasGeometricas).getGeneration(generator, bound, probabilityOfMute, probabilityOfAdd, probabilityOfRemove);
            result.add(newPopulation);
        }
        return result;
    }

    /**Cria um string para representar a geração dada
     * @param generation População dada
     * @param generationNumber Geração da população
     * @return String que representa a geração
     */
    public String toString(PathPopulation generation, int generationNumber){
        NumberFormat formatter = new DecimalFormat("#0.00");
        String gen = String.valueOf(generationNumber);
        String max = formatter.format(generation.getMaxEvaluation(this.figurasGeometricas));
        String ave = formatter.format(generation.getAveEvaluation(this.figurasGeometricas));
        String min = formatter.format(generation.getMinEvaluation(this.figurasGeometricas));
        String dis = formatter.format(generation.getPathWithMinIntersections(this.figurasGeometricas).pathDist());
        String obi = String.valueOf(generation.getPathWithMinIntersections(this.figurasGeometricas).obstacleIntersections(this.figurasGeometricas));
        return gen + ": " + max + " " + ave + " " + min + " " + dis + " " + obi; 
    }

    /**Cria uma String com N gerações da população this
     * @param numberOfGenerations Numero de gerações
     * @param generator Random number generator
     * @param bound bound of the generator
     * @param probabilityOfMute Probabilidade de ocorrer mutação na populaçao
     * @param probabilityOfAdd Probabilidade de ocorrer uma adição na populaçao
     * @param probabilityOfRemove Probabilidade de ocorrer uma remoção na populaçao
     * @return
     */
    public String toString(int numberOfGenerations, Random generator, int bound, double probabilityOfMute, double probabilityOfAdd, double probabilityOfRemove){
        String result = "";
        ArrayList<PathPopulation> generationList = getAllGeneration(numberOfGenerations,  generator,  bound, probabilityOfMute,  probabilityOfAdd,  probabilityOfRemove);
        for(int i = 0; i < numberOfGenerations-1; i++)
            result += toString(generationList.get(i), i) +'\n';
        result += toString(generationList.get(numberOfGenerations-1), numberOfGenerations-1);
        return result;
    }
    
}