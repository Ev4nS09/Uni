import java.util.ArrayList;

/** A classe path vai conter uma lista de Pontos que é armazenada pelo construtor
    @Author AfonsoRio
    @version 1.0 21/02/2023
    @inv A trajetoria tem que ser valida
 */


public class Path {

    private ArrayList<Ponto> pointList = new ArrayList<>();
    private ArrayList<Segmento> segmentoList = new ArrayList<>();

    /** O contrutor vai armazenar uma ArrayList de Pontos
        @param pointList ArrayList<Ponto>
    */

    public Path(ArrayList<Ponto> pointList){
        this.pointList = pointList;
        createSegmentPath();
        isPathValid();
    }


    /** O metodo transforma um path de pontos num path de segmentos
    */

    public void createSegmentPath(){ 
        for(int i = 0; i < this.pointList.size()-1; i++)
            this.segmentoList.add(new Segmento(this.pointList.get(i), this.pointList.get(i+1)));
    }

    /** O metodo isPathValid vai verificar se a trajetoria é valida
    */

    private void isPathValid(){
        for(int i = 0; i < this.pointList.size(); i++){
            for(int j = i+1; j < this.pointList.size(); j++){
                if(this.pointList.get(i).equals(this.pointList.get(j))){
                    System.out.println("Trajetoria:vi");
                    System.exit(0);
                }
            }
        }
    }

    /** o metodo obstacleIntersections calcula o numero de obstaculos(retangulos) que são intersetados por pelo menos um segmento de um path criado
        @param retanguloList
        @return retorna a quantidade de retangulos intersetados por pelo menos um segmento
     */

    public int obstacleIntersections(ArrayList<Retangulo> retanguloList){
        int result = 0;
        for(int i = 0; i < retanguloList.size(); i++){
            Retangulo retangulo = retanguloList.get(i);
            for(int j = 0; j < segmentoList.size(); j++){
                Segmento segmento = segmentoList.get(j);
                if(retangulo.segmentIntersectsRectangle(segmento, false)){  
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    /** Calcula a distancia de um ponto ao outra passando por todos os pontos da lista 
        @return retorna a distancia de um path de pontos
    */

    public double pathDist() { 
        double result = 0;
        for (int i = 0; i < pointList.size() - 1; i++) { 
            result += pointList.get(i).dist(pointList.get(i + 1));
        }
        return result;
    }
}
