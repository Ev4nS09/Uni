package AI;

import java.util.*;
import Game.*;
import Game.Ilayout.ID;

public class MinMax {

    private final int DEPTH = 4;
    private final int SYMETRICDEPTH = -1;
    
    private State current;
    private int goal;
    private ID turn;


    private double minmax(State current, int depth, double alpha, double beta, boolean maximizingPlayer){
        HashSet<Ilayout> closed = new HashSet<>();

        if(depth == 0 || current.isGameOver()){
            double evaluation = current.getEvaluation(this.turn);
            return maximizingPlayer ? evaluation + depth : evaluation - depth;
        }
        
        if(maximizingPlayer){
            double maxEvaluation = Double.NEGATIVE_INFINITY;
            List<Ilayout> children = current.getLayout().children();
            for (Ilayout child : children){
                if(depth < SYMETRICDEPTH || !closed.contains(child)){
                    closed.add(child);
                    double evaluation = minmax(new State(child, current), depth - 1, alpha, beta, false);
                    if(depth == DEPTH && maxEvaluation < evaluation){
                        this.goal = child.getLastMove();
                    }
                    maxEvaluation = Math.max(maxEvaluation, evaluation);
                    alpha = Math.max(alpha, evaluation);
                    if(beta <= alpha)
                        return maxEvaluation;
                }
            }
            return maxEvaluation;
        }
        else{
            double minEvaluation = Double.POSITIVE_INFINITY;
            List<Ilayout> children = current.getLayout().children();
            for (Ilayout child : children){
                if(depth < SYMETRICDEPTH || !closed.contains(child)){
                    closed.add(child);
                    double evaluation = minmax(new State(child, current), depth - 1, alpha, beta, true);
                    minEvaluation = Math.min(minEvaluation, evaluation);
                    beta = Math.min(beta, evaluation);
                    if(beta <= alpha)
                        return minEvaluation;
                
                }
            }
            return minEvaluation;
        }
    }

    public int getBestPlay(Ilayout layout, ID turn){
        this.current = new State(layout, null);
        this.goal = -1;
        this.turn = turn;

        minmax(this.current, DEPTH, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true);
    
        return this.goal;
   }
}