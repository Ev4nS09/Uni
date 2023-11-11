import java.util.ArrayList;
import java.util.Random;

/** A classe path vai conter uma lista de Pontos que é armazenada pelo construtor
    @Author AfonsoRio
    @version 2.0 25/03/2023
    @inv O path não pode ter ponto iguais
 */

public class Path {

    private ArrayList<Ponto> pointList = new ArrayList<>();

    /**Construtor que recebe uma string e transforma num caminho de Pontos
     * @param path String
     */
    public Path(String path){
        String[] pathArray = path.split(" ");
        for(int i = 0; i < pathArray.length - 1; i+=2)
            this.pointList.add(new Ponto(Integer.parseInt(pathArray[i]), Integer.parseInt(pathArray[i+1])));
        isPathValid();
    }

    /** O contrutor vai armazenar uma ArrayList de Pontos
        @param pointList ArrayList<Ponto>
    */
    public Path(ArrayList<Ponto> pointList){
        this.pointList = pointList;
        isPathValid();
    }

    /** Construtor vazio
    */
    public Path(){};

    /**Construtor que gera um caminho aleatório 
     * @param generator Random number generator
     * @param bound bound of the generator
     * @param pathSize Inteiro
     */
    public Path(Random generator, int bound, int pathSize){
        addRandomPoints(generator, 100, pathSize);
    }

    /**Construtor que gera um caminho aleatório  com ponto inicial e final
     * @param generator Random number generator
     * @param bound bound of the generator
     * @param pathSize Inteiro
     */
    public Path(Random generator, int bound, Ponto inicialPoint, Ponto finalPoint, int pathSize){
        this.pointList.add(inicialPoint);
        addRandomPoints(generator, 100, pathSize);
        this.pointList.add(finalPoint);
    }


    /**Adiciona um ponto ao path e ao mesmo tempo atualizando a lista de segmentos e por fim, verifica se o ponto introduzido é valido
     * @param a Ponto
     */
    public void add(Ponto a){
        pointList.add(a);
        isPathValid();
    }

    /**Retorna o tamanho do caminho
     * @return o tamanho do caminho
     */
    public int getSize(){return this.pointList.size();}

    /** Retorna um Ponto do da lista de caminho na posição do "index" dado
     * @param index Inteiro 
     * @return um Ponto do da lista de caminho na posição do "index" dado
     */
    public Ponto getPoint(int index){return this.pointList.get(index);}

    /**Adiciona pontos random ao path
     * @param generator Random number generator
     * @param bound bound of the generator
     * @param numberOfPoints Numero inteiro
     */
    public void addRandomPoints(Random generator, int bound, int numberOfPoints){
        for(int i = 0; i < numberOfPoints; i++)
            add(new Ponto(generator.nextInt(bound), generator.nextInt(bound)));
    }

    /** Cria uma lista de segmentos que representam o caminho
     * @return uma lista de segmentos que representam o caminho
     */
    public ArrayList<Segmento> getSegmentolistFromPath(){
        ArrayList<Segmento> result = new ArrayList<>();
        for(int i = 0; i < this.pointList.size()-1; i++)
            result.add(new Segmento(this.pointList.get(i), this.pointList.get(i+1)));
        return result;
    }

    /** O metodo isPathValid vai verificar se a trajetoria é valida
    */
    private void isPathValid(){
        if(hasReapeatedPoint()){
            System.out.println("Trajetoria:vi");
            System.exit(0);
        }
    }

    /**Verifica se o caminho tem pontos repetidos
     * @return um boolean que se "true" o caminho tem pontos repetidos, se "false" o caminho não tem pontos repetidos
     */
    public boolean hasReapeatedPoint(){
        boolean result = false;
        for(int i = 0; i < this.pointList.size(); i++)
            for(int j = i+1; j < this.pointList.size(); j++)
                if(this.pointList.get(i).equals(this.pointList.get(j)))
                    result = true;
        return result;
    }

    /**Verifica se um Ponto "a" existe no caminho
     * @param a Ponto 
     * @return um boolean se "true" o Ponto "a" existe no caminho, se "false" este não existe no caminho
     */
    public boolean hasPoint(Ponto a){
        boolean result = false;
        for(int i = 0; i < this.pointList.size(); i++)
                if(this.pointList.get(i).equals(a))
                    result = true;
        return result; 
    }
    

    /** o metodo obstacleIntersections calcula o numero de obstaculos(FigurasGeometricas) que são intersetados por pelo menos um segmento de um path criado
        @param FiguraGeometricaList
        @return retorna a quantidade de FigurasGeometricas intersetadas por pelo menos um segmento
     */
    public int obstacleIntersections(ArrayList<FiguraGeometrica> FiguraGeometricaList){
        ArrayList<Segmento> segmentoList = this.getSegmentolistFromPath();
        int result = 0;
        for(int i = 0; i < FiguraGeometricaList.size(); i++){
            FiguraGeometrica figuraGeometrica = FiguraGeometricaList.get(i);
            for(int j = 0; j < segmentoList.size(); j++){
                Segmento segmento = segmentoList.get(j);
                if(figuraGeometrica.segmentIntersects(segmento)){  
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    /** Calcula a distancia da Trajetoria de ponto em ponto
        @return retorna a distancia de um path de pontos
    */
    public double pathDist() { 
        double result = 0;
        for (int i = 0; i < pointList.size() - 1; i++) { 
            result += pointList.get(i).dist(pointList.get(i + 1));
        }
        return result;
    }

    /**Retorna um numero que representa o quão "bom" um path é, ou seja, quanto maior melhor pois é mais curto e intersecta menos obstaculos
     * @param FiguraGeometricas
     * @return Retorna um numero que representa o quão "boa" um path é
     */
    public double getEvaluation(ArrayList<FiguraGeometrica> FiguraGeometricas){
        return 1/(pathDist() + Math.exp(obstacleIntersections(FiguraGeometricas)));
    }

    /**"Corta" o path de um index "firstIndex" a outro index "lastIndex"
     * @param firstIndex Numero inteiro
     * @param lastIndex Numero inteiro
     * @return o path cortada de um index ao outro
     */
    public Path getSplitPath(int firstIndex, int lastIndex){
        Path result = new Path();
        for(int i = firstIndex; i <= lastIndex; i++)
            result.add(this.pointList.get(i));
        return result;
    }

    /**"Corta" o path de um index "firstIndex" até ao final da lista
     * @param firstIndex Numero inteiro
     * @return o path cortada de um index ate ao fim da lista
     */
    public Path getSplitPath(int firstIndex){
        return getSplitPath(firstIndex, this.pointList.size()-1);
    }

    /**Conecta a trajetoria this com a trajetoria "path"
     * @param path Path
     * @return uma trajetoria que resulta da conecção entre a trajetoria this e "path"
     */
    public Path conectPaths(Path path){
        Path result = new Path(this.pointList);
        for(int i = 0; i < path.getSize(); i++)
            if(!hasPoint(path.getPoint(i)))
                result.add(path.getPoint(i));
        return result;
    }

    /**Escolhe um ponto aleatório da trajetoria e substitui-o por outro ponto(aleatório) se este não existir na trajetoria
     * O ponto que sofre alteração não pode ser o inicial ou inicial
     * @param generator Random generator
     * @param bound Inteiro que diz o limite do valor do ponto aleatório
     * @param probability Double que é a probabilidade de ocorrer motação
     */
    public void mutePath(Random generator, int bound, double probability){
        if(this.pointList.size() < 3) return;
        double range = generator.nextDouble();
        if(range < probability){
            int index = generator.nextInt(this.pointList.size()-2) + 1;
            Ponto newPoint = new Ponto(generator.nextInt(bound), generator.nextInt(bound));
            if(!hasPoint(newPoint))
                this.pointList.set(index, newPoint);
        }
    }

    /**Escolhe uma posição aleatória da trajetoria e adiciona um ponto(aleatório) se este não existir na trajetoria
     * O ponto não pode ser adicionado no inicio ou fim da lista
     * @param generator Random generator
     * @param bound Inteiro que diz o limite do valor do ponto aleatório
     * @param probability Double que é a probabilidade de ocorrer motação
     */
    public void addRandomPosition(Random generator, int bound, double probability){
        if(this.pointList.size() < 2) return;
        double range = generator.nextDouble();
        int index = 0;
        if(range < probability){
            if(this.pointList.size() == 2) index = 1;
            else index = generator.nextInt(this.pointList.size()-2) + 2;
            Ponto newPoint = new Ponto(generator.nextInt(bound), generator.nextInt(bound));
            if(!hasPoint(newPoint))
                this.pointList.add(index, newPoint);
        }
    }

    /**Escolhe um ponto aleatório da trajetoria e remove-o se este não for o inicial ou final da lista
     * @param generator Random generator
     * @param bound Inteiro que diz o limite do valor do ponto aleatório
     * @param probability Double que é a probabilidade de ocorrer motação
     */
    public void removeRandomPoint(Random generator, int bound, double probability){
        if(this.pointList.size() < 3) return;
        double range = generator.nextDouble();
        if(range < probability){
            int index = generator.nextInt(this.pointList.size()-2) + 1;
            this.pointList.remove(index);
        }
    }

    @Override
    public String toString(){
        String result = "";
        result += "[";
        for(int i = 0; i < pointList.size(); i++){
            result += pointList.get(i).toString();
            if(i != pointList.size()-1) result += " ";
        }
        result += "]";
        return result;
    }
}
