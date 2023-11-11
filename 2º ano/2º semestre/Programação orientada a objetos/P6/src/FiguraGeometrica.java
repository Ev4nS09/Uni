/**A classe figuraGeometrica é uma classe abstrata que simplesmento "conecta" outras classes que a estendão com metodos iguais
 * @author AfonsoRio
 * @version 25/03/2023
 * @inv
 */
public abstract class FiguraGeometrica{
    
    /** Verifica se um segmento intersecta uma figuraGeometrica
     * @param a Segmento
     * @return um boolean que se "true" existe interseção se "false" não existe interseção
     */
    public abstract boolean segmentIntersects(Segmento a);
}