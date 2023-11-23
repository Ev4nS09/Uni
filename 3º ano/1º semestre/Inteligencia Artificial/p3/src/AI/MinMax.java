package AI;

import java.util.*;
import Game.*;

public class MinMax {

    private final int DEPTH = 8;
    private final int SYMETRICDEPTH = 4;
    
    private HashSet<Ilayout> closed;
    private State current;
    private State goal;

    private double minmax(State current, int depth, double alpha, double beta, boolean maximizingPlayer){
        if(depth == 0 || current.getLayout().isGameOver())
            return current.getEvaluation();
        
        if(maximizingPlayer){
            double maxEvaluation = Double.NEGATIVE_INFINITY;
            List<Ilayout> children = current.getLayout().children();
            for(Ilayout child : children){
                if((depth > SYMETRICDEPTH && !closed.contains(child)) || depth <= SYMETRICDEPTH){
                    if(depth > SYMETRICDEPTH) closed.add(child);
                    double evaluation = minmax(new State(child, null), depth - 1, alpha, beta, false);
                    if(depth == DEPTH && maxEvaluation <= evaluation){
                        this.goal = new State(child, this.current);
                    }
                    maxEvaluation = Math.max(maxEvaluation, evaluation);
                    alpha = Math.max(alpha, evaluation);
                    if(beta <= alpha)
                        break;
                }
            }
            return maxEvaluation;
        }
        else{
            double minEvaluation = Double.POSITIVE_INFINITY;
            List<Ilayout> children = current.getLayout().children();
            for(Ilayout child : children){
                // System.out.println(depth);
                if((depth > SYMETRICDEPTH && !closed.contains(child)) || depth <= SYMETRICDEPTH){
                    if(depth > SYMETRICDEPTH) closed.add(child);
                    double evaluation = minmax(new State(child, null), depth - 1, alpha, beta, true);
                    minEvaluation = Math.min(minEvaluation, evaluation);
                    beta = Math.min(beta, evaluation);
                    if(beta <= alpha)
                        break;
                }
            }
            return minEvaluation;
        }
    }

    public State getBestPlay(Ilayout layout){
        this.closed = new HashSet<>();
        this.current = new State(layout, null);
        this.goal = null;

        minmax(this.current, DEPTH, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true);

        
        return this.goal;
   }
}
