package AI;

import java.util.*;
import Game.*;
import Game.Ilayout.ID;

/** MinMax class olds a algorithm that calculates the best path  of a layout, in a game between two players. MinxMax tries to predict the other players move thinking about the value of each
 * play with a heuristic. So the name minxmax, says us all, when we play, we want to maximize and when the other player plays we want to minamize, so when we reach depth 0, we want the play
 * with the heighest value. Each node has a value of MAXIMIZEPLAYERheuristic() - MINAMIZEPLAYERheuristic().
 * @author Afonso Rio, Daniel Andrade 
 * @version 1.0 04/12/2023
 * @see Ilayout
 * @see State
 */

public class MinMax {

    private HashMap<Ilayout, Double> cache = new HashMap<>();
    private State current;
    private int depth;
    private int goal;
    private ID turn;


    /** Claculates the best move of an {@link Ilayout}.
     * @param current State
     * @param depth int
     * @param alpha double
     * @param beta beta
     * @param maximizingPlayer boolean
     * @return the best move.
     */
    private double minmax(State current, int depth, double alpha, double beta, boolean maximizingPlayer){

        boolean cut = depth >= this.depth - Math.sqrt(this.depth) ? true : false;

        if(depth == 0 || current.isGameOver()){
            double evaluation = current.getEvaluation(turn);
            return maximizingPlayer ? evaluation + depth : evaluation - depth;
        }
        
        if(maximizingPlayer){
            double maxEvaluation = Double.NEGATIVE_INFINITY;
            List<Ilayout> children = ((Board) current.getLayout()).children(cut);
            for (Ilayout child : children){
                double evaluation = 0;
                if(!cache.containsKey(child)){
                    evaluation = minmax(new State(child, null), depth - 1, alpha, beta, false);
                    cache.put(child, evaluation);
                }
                else
                    evaluation = cache.get(child);
                if(depth == this.depth && maxEvaluation < evaluation){
                    this.goal = child.getLastMove();
                }
                maxEvaluation = Math.max(maxEvaluation, evaluation);
                alpha = Math.max(alpha, evaluation);
                if(beta <= alpha)
                    break;
            }
            return maxEvaluation;
        }
        else{
            double minEvaluation = Double.POSITIVE_INFINITY;
            List<Ilayout> children = ((Board) current.getLayout()).children(cut);
            for(Ilayout child : children){
                double evaluation = 0;
                if(!cache.containsKey(child)){
                    evaluation = minmax(new State(child, null), depth - 1, alpha, beta, true);
                    cache.put(child, evaluation);
                }
                else
                    evaluation = cache.get(child);
                minEvaluation = Math.min(minEvaluation, evaluation);
                beta = Math.min(beta, evaluation);
                if(beta <= alpha)
                    break;
            }
            return minEvaluation;
        }
    }

    /**Returns the best move of an {@link Ilayout}.
     * @param layout
     * @param turn
     * @param depth
     * @return the best move.
     */
    public int getBestPlay(Ilayout layout, ID turn, int depth){
        this.cache = new HashMap<>();
        this.current = new State(layout, null);
        this.depth = depth;
        this.goal = -1;
        this.turn = turn;
        minmax(this.current, this.depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true);
    
        return this.goal;
   }
}