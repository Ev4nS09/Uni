/** Tem as propriedades de uma Circunferencia, tendo um Ponto(a) e um raio (r)
    @Author AfonsoRio
    @version 1.0 19/03/2023
    @inv O raio tem que ser maior que 0
 */

public class Circunferencia{

    private Ponto a;
    private int r;


    /**Verifica se o raio é maior que zero, se for, armazena o centro da circunferencia e o seu raio, se não, imprime uma mensagem de erro e sai do programa
     * @param a Centro da circunferencia
     * @param r Raio da circunferencia
     */
    public Circunferencia(Ponto a, int r){
        if(r <= 0){
            System.out.println("Circunferencia:vi");
            System.exit(0);
        }
        this.a = a;
        this.r = r;
    }

    /** Retorna o centro da circunferencia
     * @return retorna o centro da circunferencia
     */
    public Ponto getCenter(){return a;}

    /** Retorna o raio da circunferencia
     * @return retorna o raio da circunferencia
     */
    public int getRadius(){return r;}


    /** O metodo segmentIntersects vai verificar se a circunferencia recetor interseta com o segmento recebido pelo argumento (segmento).
     * @param segmento um Segmento de reta
     * @return  Retorna um boolean, se "true" existe interseção, se "false" não existe interseção
     */
    public boolean segmentIntersects(Segmento segmento){
        boolean result = true;
        if(segmento.getStart().getX() == segmento.getEnd().getX() && segmento.pointBelongSegment(new Ponto(segmento.getStart().getX(), this.a.getY())))
            return result;
        else if(segmento.getStart().getY() == segmento.getEnd().getY() && segmento.pointBelongSegment(new Ponto(this.a.getX(), segmento.getStart().getY())))
            return result;
        double a = 1 + Math.pow(segmento.getSlope(), 2);
        double b = (2 * -this.a.getX()) + (2 * (segmento.getSegmentEquationConstant() -this.a.getY()) * segmento.getSlope());
        double c = Math.pow(this.a.getX(), 2) + Math.pow(segmento.getSegmentEquationConstant() - this.a.getY(), 2) - Math.pow(this.r, 2);
        double bac = Math.pow(b, 2) - (4 * a * c);
        if(bac < 0) return false;
        double positiveX = (-b + Math.sqrt(bac)) / (2*a);
        double negativeX = (-b - Math.sqrt(bac)) / (2*a);
        double positiveY = (segmento.getSlope() * positiveX) + segmento.getSegmentEquationConstant();
        double negativeY = (segmento.getSlope() * negativeX) + segmento.getSegmentEquationConstant();
        if((positiveX < 0 || positiveY < 0 || !segmento.pointBelongSegment(new Ponto(positiveX, positiveY)))
        && (negativeX < 0 || negativeY < 0 || !segmento.pointBelongSegment(new Ponto(negativeX, negativeY)))) 
            result = false;
        return result;
    }
}