import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class P5Tests {
    
    @Test
    void testI(){
        Path path = new Path();
        path.add(new Ponto(1, 2));
        path.add(new Ponto(4, 3));
        path.add(new Ponto(5, 5));
        path.add(new Ponto(6, 10));
        path.add(new Ponto(10, 6));
        ArrayList<FiguraGeometrica> figuraGeometricas = new ArrayList<>();
        Circunferencia c = new Circunferencia(new Ponto(10, 10), 3);
        Triangulo t = new Triangulo(new Ponto(0, 0),new Ponto(2, 0), new Ponto(3, 3));
        Retangulo r = new Retangulo(new Ponto(4, 4), new Ponto(6, 4), new Ponto(6, 6), new Ponto(4, 6));
        figuraGeometricas.add(c);
        figuraGeometricas.add(t);
        figuraGeometricas.add(r);
        NumberFormat formatter = new DecimalFormat("#0.00");
        assertEquals("0,03", String.valueOf(formatter.format(path.getEvaluation(figuraGeometricas))));

    }

    @Test
    void testJ(){
        int[] a = {4, 7, 5};
        PathPopulation pathPopulation = new PathPopulation();
        for(int i = 0; i < 3; i++){
            Path path = new Path();
            path.add(new Ponto(2, 4));
            path.addRandomPoints(Cliente.generator, 100, a[i]);
            path.add(new Ponto(34, 18));
            pathPopulation.add(path);
        }
        ArrayList<FiguraGeometrica> figurasGeometricas = new ArrayList<>();
        figurasGeometricas.add(new Circunferencia("10 10 3"));
        figurasGeometricas.add(new Triangulo("0 0 2 0 3 3"));
        figurasGeometricas.add(new Retangulo("4 4 6 4 6 6 4 6"));
        assertEquals(pathPopulation.getTournamentResults(Cliente.generator, figurasGeometricas).toString(), 
        "[(2;4) (24;47) (52;60) (3;82) (92;23) (45;45) (34;18)]\n[(2;4) (24;47) (52;60) (3;82) (92;23) (45;45) (34;18)]\n[(2;4) (60;48) (29;47) (15;53) (91;61) (34;18)]");
    }
}
