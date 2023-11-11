import java.util.ArrayList;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

import facility.Robot;
import geometry.Path;
import geometry.Point;

public class Cliente {

    static double getBatteryLevel(ArrayList<Robot> robots, Path path){
        double batteryLevel = -1;
        for(int i = 0; i < robots.size(); i++){
            double thisBattery = (double) robots.get(i).getcurrentBatteryLevel();
            double finalBat = thisBattery - (path.length() + (robots.get(i).getCurrentPosition().distanceTo(path.getStart()))) * 10;
            if(finalBat > batteryLevel)
                batteryLevel = finalBat;
        }
        if(batteryLevel < 0){
            System.out.println("imp");
            System.exit(0);
        }
        return batteryLevel;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Robot> robots =  new ArrayList<>();
        Path path = new Path();
        int robotsSize = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < robotsSize; i++){
            String robotString = sc.nextLine();
            robots.add(new Robot(new Point(Double.parseDouble(robotString.split(" ")[1]), Double.parseDouble(robotString.split(" ")[2])), null, null, Integer.parseInt(robotString.split(" ")[0])));
        }
        int pathSize = sc.nextInt();
        sc.nextLine();
        String[] pathString = sc.nextLine().split(" ");
        for(int i = 0; i < pathString.length; i+=2){
            path.addPoint(new Point(Double.parseDouble(pathString[i]), Double.parseDouble(pathString[i+1])));
        }
        System.out.println(getBatteryLevel(robots, path));
    }
}
