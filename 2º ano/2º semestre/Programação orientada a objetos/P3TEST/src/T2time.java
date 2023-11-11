/**T2time saves the time given by a user. You can see the time in 2 formats, in secs and in "00:00:00"
 * @author Afonso Rio
 * @version 1.0 13/03/2023
 * @inv Time bus be between 00:00:00 and 13:27:15
 */
public class T2time {

    static final int T2DAYSECONDS = 13*3600+27*60+16; //48436 secs/day
    private int seconds = 0; //valid range [0, T2DAYSECONDS-1]

    /**Constructor saves the time in seconds
     * @param secs time given in secs
     */
    public T2time(int secs) {
        if (secs < 0 || secs >= T2DAYSECONDS) throw new IllegalArgumentException("0 <= secs < T2DAYSECONDS");
        this.seconds = secs;
    }

    /** Constructor saves the time in hours, minutes and seconds
    * @param h  hours
    * @param m minutes
    * @param s  seconds
    */
    public T2time(int h, int m, int s) {
        this(h*3600 + m*60 + s);
    }


    /**Constructor saves the time in the format "00:00:00"
     * @param time String with the time in the format "00:00:00"
     */
    public T2time(String time) {
        this(Integer.parseInt(time.substring(0, 2)),
        Integer.parseInt(time.substring(3, 5)), 
        Integer.parseInt(time.substring(6, 8)));
    }

    /**Returns the time in seconds
     * @return Returns the time in seconds
     */
    public int asSeconds() { return seconds; } 
 

    /**Returns the time in the format "00:00:00"
     * @return return the time in the format "00:00:00"
     */
    public String toString() {
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        int s = (seconds % 3600) % 60;
        return String.format("%02d:%02d:%02d",h,m,s);
    }

    /** Sums the two 'T2time' times
    * @pre arg != null
    * @return new T2time with the sum of this and arg mod T2DAYSECONDS
    * @post this == old this
    */
    public T2time add(T2time arg) {
        int sum = (this.seconds + arg.asSeconds()) % T2DAYSECONDS;
        return new T2time(sum);
    }

    /**The method isTime returns a boolean that if true the given String 'time' is in the format "00:00:00"
     * @param time time that will be checked if its valid
     * @return returns a boolean that if true the given String 'time' is in the format "00:00:00"
     */
    public static boolean isTime(String time){
        boolean result = true;
        if(time.length() == 8 && time.charAt(2) == ':' && time.charAt(5) == ':'){
            for(int i = 0; i < time.length(); i+=3){
                if((time.charAt(i) <= 47 || time.charAt(i) >= 58) || (time.charAt(i+1) <= 47 || time.charAt(i+1) >= 58))
                    result = false;
            }
        }
        else result = false;
  
        return result;
    }

 }
