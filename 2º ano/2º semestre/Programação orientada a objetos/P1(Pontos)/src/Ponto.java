class Ponto {
    private double x, y;

    public Ponto(double x, double y) {
            this.x = x;
            this.y = y;
    }

    public Ponto(double x, double y, boolean verificaPrimeiroQuadrante) {
        if (!verificaPrimeiroQuadrante || (x >= 0 && y >= 0)) {
            this.x = x;
            this.y = y;
        } else {
            System.out.println("iv");
            System.exit(0);
        }
    }

    double getX() {return x;}
    double getY() {return y;}

    double dist(Ponto p) {
        double dx = x - p.x;
        double dy = y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

}
