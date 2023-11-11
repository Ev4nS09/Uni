/** A classe segmento vai contruir um segmento a partir de dois pontos dados.
 *  @Author AfonsoRio
 *  @version 2.0 19/03/2023
 *  @inv Dois pontos não podem ser iguais
 */

class Segmento{
    private Ponto a, b;

    /** O construtor vai armazenar dois pontos diferentes que forma um segmento
     *  @param a Ponto diferente de b
     *  @param b Ponto diferente de a
     */
    public Segmento(Ponto a, Ponto b){
        if(!a.equals(b)){
            this.a = a;
            this.b = b;
        }
        else{
            System.out.println("Segmento:vi");
            System.exit(0);
        }
    }

    /** O metodo getStart vai retornar o ponto de inicio do segmento
     *  @return retorna o Ponto (a) do segmento
     */
    public Ponto getStart(){return a;}

    /** O metodo getEnd vai retornar o ponto final do segmento
     *  @return retorna o Ponto (b) do segmento
     */
    public Ponto getEnd(){return b;}

    /**Calcula e retorna o declive da reta que o segmento faz parte
     * @return o declive da reta que o segmento faz parte
     */
    public double getSlope(){
        double result = 0;
        double y = this.b.getY() - this.a.getY();
        double x = this.b.getX() - this.a.getX();
        if(x != 0) result = y/x;
        return result;
    }


    /** Calcula e retorn a constante da equação da reta que o segmento faz parte "y = getSlope()*x + getSegmentEquationConstant()"
     *  @return a constante da equação da reta que o segmento faz parte
     */
    public double getSegmentConstant(){
        if(this.a.getX() == this.b.getX()) return this.a.getX(); 
        return this.a.getY() - (getSlope() * this.a.getX());
    }

    /** O metodo segmentIntersects vai verificar se existe interseçao entre um segmento recetor this e o seu argumento segmentoDeReta
     *  @param segmentoDeReta um segmento
     *  @return retorna um boolean
     *  @see http://www.dpi.inpe.br/gilberto/livro/bdados/cap2.pdf (Pagina 8 e Pagina 9)
    */

    public boolean segmentIntersects(Segmento segmentoDeReta){
        boolean result = false;
        Ponto P1 = this.a;
        Ponto P2 = this.b;
        Ponto P3 = segmentoDeReta.getStart();
        Ponto P4 = segmentoDeReta.getEnd();
        double denominador = ((P4.getY() - P3.getY())*(P2.getX() - P1.getX()) - (P4.getX() - P3.getX())*(P2.getY() - P1.getY()));
        double numeradorU = (P4.getX() - P3.getX())*(P1.getY() - P3.getY()) - (P4.getY() - P3.getY())*(P1.getX() - P3.getX());
        double numeradorV = (P2.getX() - P1.getX())*(P1.getY() - P3.getY()) - (P2.getY() - P1.getY())*(P1.getX() - P3.getX());

        if(denominador == 0 && numeradorU == 0 && numeradorV == 0 && (pointBelongSemgnetArea(P3) || pointBelongSemgnetArea(P4))) //Os segmentos sao coincidente
            result = true;
        else if(denominador == 0)
            result = false;
        else if((numeradorU/denominador >= 0 && numeradorU/denominador <= 1) && (numeradorV/denominador >= 0 && numeradorV/denominador <= 1))
            result = true;

        return result;
    }

    /** O metodo pointBelongSemgnetArea vai verificar se um ponto pertence á area do segmento
     *  @param Intersecao Possivel ponto que interseta a "area" do segmento
     *  @return um boolean que se "true" o ponto Intersecao pertence á "area" do segmento
    */
    private boolean pointBelongSemgnetArea(Ponto intersecao){
        boolean result = false;
        double minimoX = Math.min(this.b.getX(), this.a.getX());
        double minimoY = Math.min(this.b.getY(), this.a.getY());
        double maximoX = Math.max(this.b.getX(), this.a.getX());
        double maximoY = Math.max(this.b.getY(), this.a.getY());
        if((intersecao.getX() >= minimoX && intersecao.getX() <= maximoX) && (intersecao.getY() >= minimoY && intersecao.getY() <= maximoY)){
            result = true;
        }
        return result;
    }


    /** Verifica se um Ponto pertence ao segmento
     *  @param Intersecao Possivel ponto que interseta o segmento
     *  @return um boolean que se "true" o ponto Intersecao pertence ao segmento
     */
    public boolean pointBelongSegment(Ponto intersecao){
        if(this.a.getX() == this.b.getX() || this.a.getY() == this.b.getY()) return pointBelongSemgnetArea(intersecao);
        else return (intersecao.getY() == ((getSlope() * intersecao.getX()) + getSegmentConstant()) && pointBelongSemgnetArea(intersecao));
    }

    @Override
    public String toString(){
        return "{" + this.a + "<->" +  this.b + "}";
    }

}