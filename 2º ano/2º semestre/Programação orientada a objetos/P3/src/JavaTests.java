import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JavaTests {
    
    @Test
    public void EncrypterTest() throws Exception{
        assertEquals("t{dwiKpecejgOgoq", EncrypterSmith.Encrypt("bugIncacheMemory", 2, 2));
        assertEquals("cdecvhB", EncrypterSmith.Encrypt("bugAbcd", 3, 1));
        assertEquals("bugIncacheMemory", EncrypterSmith.Dencrypt("t{dwiKpecejgOgoq"));
        assertEquals("bUgATssdController", EncrypterSmith.Dencrypt("Rd>Qppa@lkqoliibo_"));
        assertThrows(IllegalArgumentException.class,	()	->	{
            EncrypterSmith.Encrypt("bugIncacheMemory", -10, 2);
	    });
        assertThrows(Error.class,	()	->	{
            EncrypterSmith.Dencrypt("bgIncacheMemory");
	    });
    }
}
