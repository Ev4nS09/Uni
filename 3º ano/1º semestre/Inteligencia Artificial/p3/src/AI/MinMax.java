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
     * @param double alpha
     * @param double beta
     * @param boolean maximizingPlayer
     * @return the best move.
     */
    private double minmax(State current, int depth, double alpha, double beta, boolean maximizingPlayer){

        if(depth == 0 || current.isGameOver()){
            double evaluation = 0;
            if(cache.containsKey(current.getLayout()))
                evaluation = cache.get(current.getLayout());
            else{
                evaluation = current.getEvaluation(turn);
                cache.put(current.getLayout(), evaluation);
            }
            return maximizingPlayer ? evaluation + depth : evaluation - depth;
        }
        
        if(maximizingPlayer){
            double maxEvaluation = Double.NEGATIVE_INFINITY;
            List<Ilayout> children = current.getLayout().children();
            for (Ilayout child : children){
                double evaluation = minmax(new State(child, current), depth - 1, alpha, beta, false);
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
            List<Ilayout> children = current.getLayout().children();
            for(Ilayout child : children){
                double evaluation = minmax(new State(child, current), depth - 1, alpha, beta, true);
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