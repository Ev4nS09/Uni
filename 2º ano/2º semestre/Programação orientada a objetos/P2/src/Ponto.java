/** A classe ponto representa um ponto com coordenadas x e y
    @Author AfonsoRio
    @version 1.0 21/02/2023
    @inv Verifica se o ponto pertence ao primeiro quadrante
 */

class Ponto {
    private double x, y;

    /** O contrutor vai armazenar duas variaveis x e y se estas pertencerem ao primeiro quadrante, tambem tendo a possibilidade de escolhermos se queremos verificar se o Ponto
     * pertence ao primeiro quadrante
    @param x double positivo
    @param y double positivo
    @param verificarPrimeiroQuadrante boolean
    */

    public Ponto(double x, double y) {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        } else {
            System.out.println("Ponto:vi");  
            System.exit(0);
        }
    }

    // public Ponto(double x, double y, boolean verificarPrimeiroQuadrante) {
    //     if (!verificarPrimeiroQuadrante || (x >= 0 && y >= 0)) {
    //         this.x = x;
    //         this.y = y;
    //     } else {
    //         System.out.println("Ponto:vi");  
    //         System.exit(0);
    //     }
    // }

    /** O metodo dist vai calcular a distancia entre o Ponto recetor e o argumento p
    @param p Um Ponto
    @return retorna a distancia entre dois pontos
    */

    public double dist(Ponto p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /** O metodo getX retorna x
    @return retorna um x
    */

    public double getX() {
        return x;
    }

    /** O metodo getY retorna y
    @return retorna um y
    */

    public double getY() {
        return y;
    }

    /** O metodo retorna um boolean se o ponto recetor for igual ao argumento p
    @param p Um Ponto
    @return retorna um boolean se o ponto recetor for igual ao argumento p
    */

    public boolean equals(Ponto p){
        if(this.x == p.x && this.y == p.y) return true;
        else return false;
    }

}
