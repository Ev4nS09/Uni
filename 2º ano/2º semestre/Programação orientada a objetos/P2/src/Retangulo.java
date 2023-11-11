/** A classe retangulo vai representar um retangulo com 4 pontos
    @Author AfonsoRio
    @version 1.0 21/02/2023
    @inv Verifica se o retangulo é de facto um retangulo
 */

public class Retangulo {

    private Ponto a, b, c, d;

    /** O construtor vai armazenar as arestas do retangulo
        @param a Ponto
        @param b Ponto
        @param c Ponto
        @param d Ponto
    */

    public Retangulo(Ponto a, Ponto b, Ponto c, Ponto d){
        if(!isRightAngle(a, b, c) || !isRightAngle(b, c, d) || !isRightAngle(c, d, a)){
            System.out.println("Retangulo:vi");
            System.exit(0);
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /** O metodo get vai retornar a aresta (a) do retangulo
        @return a
    */

    public Ponto getA(){return a;}

    /** O metodo get vai retornar a aresta (b) do retangulo
        @return b
    */

    public Ponto getB(){return b;}

    /** O metodo get vai retornar a aresta (c) do retangulo
        @return c
    */

    public Ponto getC(){return c;}

    /** O metodo get vai retornar a aresta (d) do retangulo
        @return d
    */

    public Ponto getD(){return d;}


    /** O metodo isRightAngle vai verificar se tres pontos do retangulo formam um angulo reto
        @param a um Ponto que representa uma aresta do retangulo
        @param b um Ponto que representa uma aresta do retangulo
        @param c um Ponto que representa uma aresta do retangulo
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

    /** O metodo segmentIntersectsRectangle vai verificar se o retangulo recetor interseta com o segmento recebido pelo argumento (segmento). O argumento (displayError) se
     * "true" vai mostrar o erro no terminal se houver intersecao entre o retangulo e o semgneto, e se "false" o metodo retorna um boolean se existe intersecao
        @param displayStatus boolean
        @param segmento Segmento
        @return boolean
    */

    public boolean segmentIntersectsRectangle(Segmento segmento, boolean displayError){
        Segmento AB = new Segmento(this.a, this.b);
        Segmento BC = new Segmento(this.b, this.c);
        Segmento CD = new Segmento(this.c, this.d);
        Segmento DA = new Segmento(this.d, this.a);
        if(segmento.segmentIntersects(AB, displayError)) return true;
        if(segmento.segmentIntersects(BC, displayError)) return true;
        if(segmento.segmentIntersects(CD, displayError)) return true;
        if(segmento.segmentIntersects(DA, displayError)) return true;
        if(displayError) System.out.println("OK");
        return false;
    }

    public void  checkIntersecaoRetangulo(Segmento segmento){
        segmentIntersectsRectangle(segmento, true);
    }

}
  