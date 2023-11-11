import java.util.ArrayList;

/**A classe poligono vai representar um poligono
 *  @Author AfonsoRio
 *  @version 1.0 25/03/2023
 *  @inv 
 */
public class Poligono extends FiguraGeometrica{
    
    protected ArrayList<Ponto> vertices = new ArrayList<>();

    /**Construtor vazio
     */
    public Poligono(){}

    /**Retorna o vertice na posição "index" do arrayList de vertices
     * @param index Numero inteiro
     * @return Retorna o vertice na posição "index" do arrayList de vertices
     */
    public Ponto getVertice(int index){return vertices.get(index);}

    /**Adiciona um vertice ao poligono
     * @param a Ponto
     */
    public void addVertice(Ponto a){vertices.add(a);}

    @Override
    public boolean segmentIntersects(Segmento segmento){
        boolean result = false;
        for(int i = 0; i < this.vertices.size()-1; i++)
            if(segmento.segmentIntersects(new Segmento(this.vertices.get(i), this.vertices.get(i+1)))) 
                result = true;
        return result;
    }

}
