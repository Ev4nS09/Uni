public class Race{

    public String circuit;
    public String racer;
    public int year;
    public int position;
    public boolean finished;

    public Race(String circuit, String racer, int year, int position, boolean finished) {
        this.circuit = circuit;
        this.racer = racer;
        this.year = year;
        this.position = position;
        this.finished = finished;
    }

    public String getCircuit() {
        return this.circuit;
    }

    public String getRacer() {
        return this.racer;
    }

    public int getYear() {
        return this.year;
    }

    public boolean getFinished() {
        return this.finished;
    }

    public int getPosition() {
        if (this.finished == false)
            return 0;
        else
            return this.position;
    }

    public String toString() {
        if (getPosition() != 0)
            return this.circuit + "-" + this.year + "-" + this.racer + " " + this.position;
        else
            return this.circuit + "-" + this.year + "-" + this.racer + " ";
    }
}
