/**A classe Triangulo representa um triangulo
 * @author AfonsoRio
 * @version 1.1 25/03/2023
 * @inv Os vertices do triangulo não podem ser colineares
 */
public class Triangulo extends Poligono{

    /** O construtor vai chamar o poligono para armazenar os vertices do triangulo, ao mesmo tempo verificando se a invariante é valida
     * @param a
     * @param b
     * @param c
     */
    public Triangulo(Ponto a, Ponto b, Ponto c){
        super();
        if(!isTriangle(a, b, c)){
            System.out.println("Triangulo:vi");
            System.exit(0);
        } 
        addVertice(a);
        addVertice(b);
        addVertice(c);
    }

    /**Construtor vai receber as coordenadas de todos os pontos do triangulo e vai chamar o construtor acima construindo os pontos com as coordenadas
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     */
    public Triangulo(String x1, String y1, String x2, String y2, String x3, String y3){
        this(new Ponto(Integer.parseInt(x1), Integer.parseInt(y1)), new Ponto(Integer.parseInt(x2), Integer.parseInt(y2)), 
             new Ponto(Integer.parseInt(x3), Integer.parseInt(y3)));
    }

    /**Construtor que vai receber uma string que representam os pontos do triangulo e chama o construtor acima
     * @param string
     */
    public Triangulo(String string){
        this(string.split(" ")[0], string.split(" ")[1], string.split(" ")[2], 
             string.split(" ")[3], string.split(" ")[4], string.split(" ")[5]);
    }   

    /**Verifica se três pontos são colineares
     * @param a Ponto
     * @param b Ponto
     * @param c Ponto
     * @return um boolean se "true" os pontos são colineares, se "false" os pontos não sao colineares
     */
    public boolean isTriangle(Ponto a, Ponto b, Ponto c){
        return !new Segmento(a, b).pointBelongSegment(c) && !new Segmento(a, c).pointBelongSegment(b);
    }
}

