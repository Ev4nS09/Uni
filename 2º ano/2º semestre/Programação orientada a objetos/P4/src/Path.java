import java.util.ArrayList;

/** Tem as propriedades de uma trajetoria criada por pontos
    @Author AfonsoRio
    @version 2.0 21/02/2023
    @inv Não pode haver pontos iguais na trajetoria
 */


public class Path {

    private ArrayList<Ponto> pointList = new ArrayList<>();
    private ArrayList<Segmento> segmentoList = new ArrayList<>();

    /** O contrutor vai armazenar uma ArrayList de Pontos verificando se é valida e ao mesmo tempo atualizando a trajetoria na forma de segmentos
        @param pointList ArrayList<Ponto>
    */
    public Path(ArrayList<Ponto> pointList){
        this.pointList = pointList;
        updateSegmentPath();
        isPathValid();
    }

    /** O contrutor vazio
    */
    public Path(){};

    /** Adiciona um ponto á trajetoria verificando se é valida e ao mesmo tempo atualizando a trajetoria na forma de segmentos
     * @param a
     */
    public void add(Ponto a){
        pointList.add(a);
        isPathValid();
        updateSegmentPath();
    }


    /** O metodo updateSegmentPath atualiza a lista de segmentos
    */
    public void updateSegmentPath(){ 
        for(int i = segmentoList.size(); i < this.pointList.size()-1; i++)
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
                if(retangulo.segmentIntersects(segmento)){  
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
