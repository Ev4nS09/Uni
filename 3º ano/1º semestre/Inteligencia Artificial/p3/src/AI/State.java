package AI;

import java.util.*;
import Game.*;
import Game.Ilayout.ID;

/** State representes a "node" that instead of going forward it goes back.
 * @author Afonso Rio, Daniel Andrade 
 * @version 2.0 04/12/2023
 * @see Ilayout
 */
class State{
    
    private Ilayout layout;
    private State father;
    private double weight;
    

    /** Constructs a {@code State} given a layout. {@code Father} is set to {@code null}
     * @param layout Ilayout
     */
    public State(Ilayout layout){
        this.layout = layout;
        this.father = null;
        this.weight = 0;
    }
    
    /** Constructs a {@code State} given a layout and the previous State
     * @param layout Ilayout
     * @param father State
     */
    public State(Ilayout layout, State father){
        this.layout = layout;
        this.father = father;
        if(father != null)
            this.weight = father.getWeight() + 1;
        else
            this.weight = 0;
    }
    
    
    /** Returns this.State layout
     * @return this.State layout
     */
    public Ilayout getLayout(){
        return this.layout;
    }
    
    /** Returns this.State father
     * @return this.State father
     */
    public State getFather(){
        return this.father;
    }
    
    /** Returns this.State weight
     * @return this.State weight
     */
    public double getWeight(){
        return this.weight;
    }
    
    /** Returns the sum between this.State weight and it's estimated weight to achive {@code Goal}
     * @param goal Ilayout
     * @return the sum between this.State weight and it's estimated weight to achive {@code Goal}
     */
    public double getEvaluation(ID turn){
        return this.layout.getEvaluation(turn);
    }
    
    /** Verifys if this.State's layout the goal
     * @param goal Ilayout
     * @return Bolean if this.State's layout is the goal
     */
    public boolean isGameOver(){
        return this.layout.isGameOver();
    }

    /** Returns the path from a this.State until it's father is null
     * @return the path from a this.State until it's father is null
     */
    public List<State> getPath(){
        LinkedList<State> result = new LinkedList<>();
        State currentState = this.father;
        result.addFirst(new State(this.layout, this.father));
    
        while(currentState != null){
            result.addFirst(currentState);
            currentState = currentState.getFather();
        }
        return result;
    }

    @Override
    public boolean equals(Object s){
        return this.layout.equals(((State)s).layout);
    }

    @Override
    public int hashCode() {
        return this.layout.hashCode();
    }
            
    @Override
    public String toString(){
        return this.layout.toString();
    }
}
