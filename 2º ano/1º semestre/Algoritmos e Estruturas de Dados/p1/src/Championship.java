
public class Championship {
    public int year;
    public String racer;
    public String category;
    public String bike;
    public int position;
    public int points;
    Race a[] = new Race[21];

    Championship(int year, String racer, String category, String bike, int position, int points) {
        this.year = year;
        this.racer = racer;
        this.category = category;
        this.bike = bike;
        this.position = position;
        this.points = points;
    }

    public String getCategory() {
        return this.category;
    }

    public String getRacer() {
        return this.racer;
    }

    public int getYear() {
        return this.year;
    }

    public String getBike() {
        return this.bike;
    }

    public int getPoints() {
        return this.points;
    }

    public int getPosition() {
        return this.position;
    }

    public void addRace(int raceNumber, Race r) {
        if ((raceNumber >= 1 && raceNumber <= 21)
                && ((getYear() == r.getYear() && getRacer().equals(r.getRacer()) == true)))
            a[raceNumber - 1] = r;
        else if (raceNumber < 1 || raceNumber > 21)
            throw new IndexOutOfBoundsException("Number out of range.");
        else if (getYear() != r.getYear() || getRacer().equals(r.getRacer()) == false)
            throw new IllegalArgumentException("Data not valid.");
    }

    public Race getRace(int raceNumber) {
        if (raceNumber < 1 || raceNumber > 21)
            throw new IndexOutOfBoundsException("Number out of range.");
        else
            return a[raceNumber - 1];
    }

    public String toString() {
        String r = "";
        for (int i = 0; i < 21; i++) {
            if (a[i] == null)
                r += " " + "/";
            else if (a[i].getFinished() == false)
                r += a[i].getCircuit() + " " + "/";
            else
                r += a[i].getCircuit() + " " + a[i].getPosition() + "/";
        }
        return racer + "/" + year + "/" + category + "/" + bike + '\n' + r;
    }

    public static int stringCompare(String str1, String str2) {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);
        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int) str1.charAt(i);
            int str2_ch = (int) str2.charAt(i);
            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        if (l1 != l2) {
            return l1 - l2;
        }

        else {
            return 0;
        }
    }

    public int compareTo(Championship c2) {
        int result = stringCompare(getRacer(), c2.getRacer());
        if (result == 0)
            result = c2.getYear() - getYear();
        return result;
    }

}
