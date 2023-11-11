import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class P2Tests {
    @Test
    public void testConstructor0() {
        assertEquals(true, new Ponto(2, 3).equals(new Ponto(2, 3)));
        // assertEquals(48435, new T2time(48435).asSeconds());  
    }

    @Test
    public void testConstructor1() {
        assertEquals(false, new Ponto(2, 3).equals(new Ponto(2, 7)));
        // assertEquals(48435, new T2time(48435).asSeconds());  
        }
}
