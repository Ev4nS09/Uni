/** A classe triangulo representa um triangulo, tendo três ponto
 * @author AfonsoRio
 * @version 1.0 19/03/2023
 * @inv Os três pontos não podem ser colineares
 */
public class Triangulo{
    
    private Ponto a, b, c;

    /**Construtor que verifica se os pontos são validos, se sim armazena-os
     * @param a um Ponto
     * @param b um Ponto
     * @param c um Ponto
     */
    public Triangulo(Ponto a, Ponto b, Ponto c){
        if(!isTriangle(a, b, c)){
            System.out.println("Triangulo:vi");
            System.exit(0);
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**Verifica se três pontos são colineares
     * @param a um Ponto
     * @param b um Ponto
     * @param c um Ponto
     * @return Retorna um boolean se "true" os pontos são colineares, se "false" os pontos não sao colineares
     */
    public boolean isTriangle(Ponto a, Ponto b, Ponto c){
        return !new Segmento(a, b).pointBelongSegment(c) && !new Segmento(a, c).pointBelongSegment(b);
    }

    /**O metodo segmentIntersects vai verificar se o retangulo recetor interseta com o segmento recebido pelo argumento (segmento).
     * @param segmento Segmento
     * @return um boolean se se "true" existe interseção, se "false" não existe interseção
     */
    public boolean segmentIntersects(Segmento segmento){
        if(segmento.segmentIntersects(new Segmento(this.a, this.b))) return true;
        if(segmento.segmentIntersects(new Segmento(this.b, this.c))) return true;
        if(segmento.segmentIntersects(new Segmento(this.c, this.a))) return true;
        return false;
    }
}
