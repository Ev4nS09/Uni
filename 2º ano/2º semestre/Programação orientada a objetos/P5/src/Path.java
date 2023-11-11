import java.util.ArrayList;
import java.util.Random;

/** A classe path vai conter uma lista de Pontos que é armazenada pelo construtor
    @Author AfonsoRio
    @version 2.0 25/03/2023
    @inv O path não pode ter ponto iguais
 */

public class Path {

    private ArrayList<Ponto> pointList = new ArrayList<>();
    private ArrayList<Segmento> segmentoList = new ArrayList<>();

    /** O contrutor vai armazenar uma ArrayList de Pontos
        @param pointList ArrayList<Ponto>
    */

    public Path(ArrayList<Ponto> pointList){
        this.pointList = pointList;
        addSegmentPath();
        isPathValid();
    }

    /** Construtor vazio
    */
    public Path(){};


    /**Adiciona um ponto ao path e ao mesmo tempo atualizando a lista de segmentos e por fim, verifica se o ponto introduzido é valido
     * @param a Ponto
     */
    public void add(Ponto a){
        pointList.add(a);
        addSegmentPath();
        isPathValid();
    }

    /**Adiciona pontos random ao path
     * @param generator Random number generator
     * @param bound bound of the generator
     * @param numberOfPoints Numero inteiro
     */
    public void addRandomPoints(Random generator, int bound, int numberOfPoints){
        for(int i = 0; i < numberOfPoints; i++)
            add(new Ponto(generator.nextInt(bound), generator.nextInt(bound)));
    }

    /** O metodo transforma um path de pontos num path de segmentos
    */
    public void addSegmentPath(){ 
        for(int i = segmentoList.size(); i < this.pointList.size()-1; i++)
            this.segmentoList.add(new Segmento(this.pointList.get(i), this.pointList.get(i+1)));
    }

    /** O metodo isPathValid vai verificar se a trajetoria é valida
    */
    private void isPathValid(){
        for(int i = 0; i < this.pointList.size(); i++){
            for(int j = i+1; j < this.pointList.size(); j++){
                if(this.pointList.get(i).equals(this.pointList.get(j))){
                    System.out.println("T+rajetoria:vi");
                    System.exit(0);
                }
            }
        }
    }

    /** o metodo obstacleIntersections calcula o numero de obstaculos(FigurasGeometricas) que são intersetados por pelo menos um segmento de um path criado
        @param FiguraGeometricaList
        @return retorna a quantidade de FigurasGeometricas intersetadas por pelo menos um segmento
     */
    public int obstacleIntersections(ArrayList<FiguraGeometrica> FiguraGeometricaList){
        int result = 0;
        for(int i = 0; i < FiguraGeometricaList.size(); i++){
            FiguraGeometrica figuraGeometrica = FiguraGeometricaList.get(i);
            for(int j = 0; j < segmentoList.size(); j++){
                Segmento segmento = segmentoList.get(j);
                if(figuraGeometrica.segmentIntersects(segmento)){  
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    /** Calcula a distancia da Trajetoria de ponto em ponto
        @return retorna a distancia de um path de pontos
    */
    public double pathDist() { 
        double result = 0;
        for (int i = 0; i < pointList.size() - 1; i++) { 
            result += pointList.get(i).dist(pointList.get(i + 1));
        }
        return result;
    }

    /**Retorna um numero que representa o quão "boa" um path é, ou seja, quanto maior melhor pois é mais curto e intersecta menos obstaculos
     * @param FiguraGeometricas
     * @return Retorna um numero que representa o quão "boa" um path é
     */
    public double getEvaluation(ArrayList<FiguraGeometrica> FiguraGeometricas){
        return 1/(pathDist() + Math.exp(obstacleIntersections(FiguraGeometricas)));
    }

    @Override
    public String toString(){
        String result = "";
        result += "[";
        for(int i = 0; i < pointList.size(); i++){
            result += pointList.get(i).toString();
            if(i != pointList.size()-1) result += " ";
        }
        result += "]";
        return result;
    }
}
