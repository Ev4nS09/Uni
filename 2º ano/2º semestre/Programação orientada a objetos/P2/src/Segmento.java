
/** A classe segmento vai contruir um segmento a partir de dois pontos dados.
    @Author AfonsoRio
    @version 1.0 21/02/2023
    @inv Dois pontos não podem ser iguais
 */

class Segmento{
    private Ponto a, b;

    /** O construtor vai armazenar dois pontos diferentes que forma um segmento
        @param a Ponto diferente de b
        @param b Ponto diferente de a
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
        @return retorna o Ponto (a) do segmento
    */

    public Ponto getStart(){return a;}

    /** O metodo getEnd vai retornar o ponto final do segmento
        @return retorna o Ponto (b) do segmento
    */

    public Ponto getEnd(){return b;}

    /*Formula usada no metodo segmentIntersects
    * Por Exemplo temos um segmento s1 e s2 que queremos saber se intersetao
    * P1 = s1.getA()
    * P2 = s1.getB()
    * P3 = s2.getA()
    * P4 = s2.getB()
    *  (P4.getX() - P3.getX())*(P1.getY() - P3.getY()) - (P4.getY() - P3.getY())*(P1.getX() - P3.getX())
    * --------------------------------------------------------------------------------------------------- = U
    * ((P4.getY() - P3.getY())*(P2.getX() - P1.getX()) - (P4.getX() - P3.getX())*(P2.getY() - P1.getY()))
    * 
    * (P2.getX() - P1.getX())*(P1.getY() - P3.getY()) - (P2.getY() - P1.getY())*(P1.getX() - P3.getX())
    * ---------------------------------------------------------------------------------------------------- = V
    * ((P4.getY() - P3.getY())*(P2.getX() - P1.getX()) - (P4.getX() - P3.getX())*(P2.getY() - P1.getY()))
    * 
    * Para ocorrer uma interseçao entre dois segmentos o valor U e V têm q pertencer ao intervalo [0, 1].
    * Mas se as linhas forem coicidentes entao u = 0 v = 0 e denominador = 0 vai existir interseção
    */

    /** O metodo segmentIntersects vai verificar se existe interseçao entre um segmento recetor this e o seu argumento segmentoDeReta
        @param segmentoDeReta um segmento
        @return retorna um boolean
        @see http://www.dpi.inpe.br/gilberto/livro/bdados/cap2.pdf (Pagina 8 e Pagina 9)
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
        if(denominador == 0 && numeradorU == 0 && numeradorV == 0){   //Os segmentos sao coincidentes
            if(pointBelongSegment(P1, P2, P3) || pointBelongSegment(P1, P2, P4)) result = true;
        }
        else if(denominador == 0){
            result = false;
        }
        else if((numeradorU/denominador >= 0 && numeradorU/denominador <= 1) && (numeradorV/denominador >= 0 && numeradorV/denominador <= 1)){
            result = true;
        }
        return result;
    }

    /** O metodo segmentIntersects vai verificar se existe interseçao entre um segmento recetor e o seu argumento segmentoDeReta, e podemos escolher mostrar o erro da funçao com
     * um boolean
        @param segmentoDeReta um segmento
        @param displayError um boolean
        @return retorna um boolean
        @see http://www.dpi.inpe.br/gilberto/livro/bdados/cap2.pdf (Pagina 8 e Pagina 9)
    */


    public boolean segmentIntersects(Segmento segmentoDeReta, boolean displayError){
        if(!displayError) return segmentIntersects(segmentoDeReta);

        if(segmentIntersects(segmentoDeReta)){
            System.out.println("Fail");
            System.exit(0);
        }
        return false;
    }

    /** O metodo pointBelongSegment vai verificar se um ponto pertence a um segmento(P1 e P2 formam esse segmento), este metodo é apenas para ser usado no metodo segmentIntersects
        @param P1 Ponto inicial do segmento
        @param P2 Ponto final do segmento
        @param Intersecao Possivel ponto que interseta o segmento (P1 - P2)
        @return um boolean que se "true" o ponto Intersecao pertence ao segmento (P1 - P2)
    */

    public boolean pointBelongSegment(Ponto P1, Ponto P2, Ponto Intersecao){
        boolean result = false;
        double minimoX = Math.min(P2.getX(), P1.getX());
        double minimoY = Math.min(P2.getY(), P1.getY());
        double maximoX = Math.max(P2.getX(), P1.getX());
        double maximoY = Math.max(P2.getY(), P1.getY());
        if((Intersecao.getX() >= minimoX && Intersecao.getX() <= maximoX) && (Intersecao.getY() >= minimoY && Intersecao.getY() <= maximoY)){
            result = true;
        }
        return result;
    }

}