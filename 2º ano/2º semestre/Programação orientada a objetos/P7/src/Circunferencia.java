/** A classe Circunferencua representa uma circunferencia com um ponto "center" e o seu raio "r"
 * @author AfonsoRio
 * @version 1.1 25/03/2023
 * @inv O raio da circunferencia não pode ser menos que zero
 */
public class Circunferencia extends FiguraGeometrica{

    private Ponto center;
    private int r;

    /**Construtor que verifica se a invariante é cumprida e armazena o centro da circunferencia e o seu raio
     * @param center
     * @param r
     */
    public Circunferencia(Ponto center, int r){
        if(r < 0){
            System.out.println("Circunferencia:vi");
            System.exit(0);
        }
        this.center = center;
        this.r = r;
    }

    /**Recebe as coordenadas do centro e o raio e chama o construtor acima criando um ponto com as coordenadas
     * @param x String
     * @param y String
     * @param r String
     */
    public Circunferencia(String x, String y, String r){
        this(new Ponto(Integer.parseInt(x), Integer.parseInt(y)), Integer.parseInt(r));
    }

    /**Recebe uma string que representam os argumentos do construtor maior, este vai chamar o construtor acima dele dividindo a string recebida em 3
     * @param string
     */
    public Circunferencia(String string){
        this(string.split(" ")[0], string.split(" ")[1], string.split(" ")[2]);
    }


    @Override
    public boolean segmentIntersects(Segmento segmento){

        //Puts the line equation into the circunference equation so we cant find the points of intersections
        boolean result = true;
        double a = 1 + Math.pow(segmento.getSlope(), 2);
        double b = (2 * -this.center.getX()) + (2 * (segmento.getSegmentConstant() -this.center.getY()) * segmento.getSlope());
        double c = Math.pow(this.center.getX(), 2) + Math.pow(segmento.getSegmentConstant() - this.center.getY(), 2) - Math.pow(this.r, 2);
        if(segmento.getStart().getX() == segmento.getEnd().getX()){
            b = (2 * (-this.center.getY()));
            c = Math.pow(-this.center.getX() + segmento.getSegmentConstant(), 2) + Math.pow(this.center.getY(), 2) - Math.pow(this.r, 2);
        }

        //Quadratic formula
        double bac = Math.pow(b, 2) - (4 * a * c);
        if(bac < 0) return false;
        double positiveX = (-b + Math.sqrt(bac)) / (2*a); //these two will create a Point "A" with the positive sign in the quadradic formula                           
        double positiveY = (segmento.getSlope() * positiveX) + segmento.getSegmentConstant();

        double negativeX = (-b - Math.sqrt(bac)) / (2*a); //these two will create a Point "B" with the negative sign in the quadradic formula           
        double negativeY = (segmento.getSlope() * negativeX) + segmento.getSegmentConstant();

        //Exchange the variables in the case we want to find the Y of the intersection Point
        if(segmento.getStart().getX() == segmento.getEnd().getX()){
            double auxpositiveX = positiveX; double auxnegativeX = negativeX; //Stores the X's
            positiveX = positiveY; negativeX = negativeY; //X now becomes the Y
            positiveY = auxpositiveX; negativeY = auxnegativeX; //Y now becomes the X
        }

        //We check if any of the x's or y's are negative cause we just want postive number (Point @inv), then we
        //check if the point we got belongs to the segment we receive in the argument 
        if((positiveX < 0 || positiveY < 0 || !segmento.pointBelongSegment(new Ponto(positiveX, positiveY)))
        && (negativeX < 0 || negativeY < 0 || !segmento.pointBelongSegment(new Ponto(negativeX, negativeY)))) 
            result = false;

        return result;
    }
}