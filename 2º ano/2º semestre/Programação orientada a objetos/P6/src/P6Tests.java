import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class P6Tests {
    
    @Test   
    void testK(){
        PathPopulation test = new PathPopulation();
        Random generator = new Random(0);
        Path pathA = new Path("1 2 2 4 8 5 3 5"); Path pathB = new Path("2 3 3 4 3 6 3 7 2 8");
        assertEquals("[(1;2) (2;8)]\n[(2;3) (3;4) (3;6) (3;7) (2;4) (8;5) (3;5)]", test.createRecombinationPopulation(generator, pathA, pathB).toString());
    }

    @Test
    void testL(){
        PathPopulation pathPopulation = new PathPopulation();
        int[] a = {3, 5, 6};
        Random generator = new Random(0);
        for(int i = 0; i < 3; i++){
            Path path = new Path();
            path.addRandomPoints(generator, 100, a[i]);
            pathPopulation.add(path);
        }
        double probability = 0.25;
        pathPopulation.mutePopulation(generator, 100, probability);
        assertEquals("[(60;48) (29;47) (15;53)]\n[(91;61) (19;54) (77;77) (73;62) (95;44)]\n[(84;75) (41;20) (43;88) (62;25) (52;60) (3;82)]", pathPopulation.toString());
    }

    @Test
    void testM(){
        PathPopulation pathPopulation = new PathPopulation();
        int[] a = {2, 3};
        Random generator = new Random(0);
        for(int i = 0; i < 2; i++){
            Path path = new Path();
            path.addRandomPoints(generator, 100, a[i]);
            pathPopulation.add(path);
        }
        double probability = 0.5;
        pathPopulation.addRandomPositionPopulation(generator, 100, probability);
        assertEquals("[(60;48) (73;62) (29;47)]\n[(15;53) (91;61) (19;54)]", pathPopulation.toString());
    }

    @Test
    void testN(){
        PathPopulation pathPopulation = new PathPopulation();
        int[] a = {2, 3};
        Random generator = new Random(0);
        for(int i = 0; i < 2; i++){
            Path path = new Path();
            path.addRandomPoints(generator, 100, a[i]);
            pathPopulation.add(path);
        }
        double probability = 0.75;
        pathPopulation.removeRandomPointsFromPopulation(generator, 100, probability);
        assertEquals("[(60;48) (29;47)]\n[(15;53) (19;54)]", pathPopulation.toString());
    }
    
}
