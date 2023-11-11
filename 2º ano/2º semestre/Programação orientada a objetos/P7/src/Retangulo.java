/** A classe retangulo vai representar um retangulo com 4 pontos
 *  @Author AfonsoRio
 *  @version 2.0 25/03/2023
 *  @inv Verifica se pelo menos 3 lados do retangulo são retos
 */

public class Retangulo extends Poligono{


    /** O construtor vai chamar o poligono para armazenar os vertices do retangulo, ao mesmo tempo verificando se a invariante é valida
     *  @param a Ponto
     *  @param b Ponto
     *  @param c Ponto
     *  @param d Ponto
     */
    public Retangulo(Ponto a, Ponto b, Ponto c, Ponto d){
        super();
        if(!isRightAngle(a, b, c) || !isRightAngle(b, c, d) || !isRightAngle(c, d, a)){
            System.out.println("Retangulo:vi");
            System.exit(0);
        }
        addVertice(a);
        addVertice(b);
        addVertice(c);
        addVertice(d);
    }


    /**Construtor vai receber as coordenadas de todos os pontos do retangulo e vai chamar o construtor acima construindo os pontos com as coordenadas
     * @param x1 String
     * @param y1 String
     * @param x2 String
     * @param y2 String
     * @param x3 String
     * @param y3 String
     * @param x4 String
     * @param y4 String
     */
    public Retangulo(String x1, String y1, String x2, String y2, String x3, String y3, String x4, String y4){
        this(new Ponto(Integer.parseInt(x1), Integer.parseInt(y1)), new Ponto(Integer.parseInt(x2), Integer.parseInt(y2)), 
             new Ponto(Integer.parseInt(x3), Integer.parseInt(y3)), new Ponto(Integer.parseInt(x4), Integer.parseInt(y4)));
    } 

    /**Construtor que vai receber uma string que representam os pontos do retangulo e chama o construtor acima
     * @param string
     */
    public Retangulo(String string){
        this(string.split(" ")[0], string.split(" ")[1], string.split(" ")[2], string.split(" ")[3],
             string.split(" ")[4], string.split(" ")[5], string.split(" ")[6], string.split(" ")[7]);
    }

    /** O metodo isRightAngle vai verificar se tres pontos do retangulo formam um angulo reto
     *  @param a um Ponto que representa uma aresta do retangulo
     *  @param b um Ponto que representa uma aresta do retangulo
     *  @param c um Ponto que representa uma aresta do retangulo
     */
    public boolean isRightAngle(Ponto a, Ponto b, Ponto c){
        boolean result = true;
        double hipotenusa = a.dist(c);
        double cateto1 = a.dist(b);
        double cateto2 = b.dist(c);
        float hipo =  (float) (hipotenusa*hipotenusa);
        float catetoSum = (float) ((cateto1*cateto1) + (cateto2*cateto2));
        if(hipo != catetoSum || hipo == 0 || catetoSum == 0){
            result = false;
        }
        return result;
    }
}
  