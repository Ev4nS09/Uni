package AI;

import java.util.*;
import Game.*;
import Game.Ilayout.ID;

public class MinMax {

    private final int DEPTH = 6;
    
    private HashMap<Ilayout, Double> closed = new HashMap<>();
    private State current;
    private int goal;
    private ID turn;


    private double minmax(State current, int depth, double alpha, double beta, boolean maximizingPlayer){

        if(depth == 0 || current.isGameOver()){
            Object evaluation = closed.get(current.getLayout());
            if(evaluation == null){
                evaluation = current.getEvaluation(turn);
                closed.put(current.getLayout(), (double) evaluation);
            }
            return maximizingPlayer ? ((double)evaluation) + depth : ((double)evaluation) - depth;
        }
        
        if(maximizingPlayer){
            double maxEvaluation = Double.NEGATIVE_INFINITY;
            List<Ilayout> children = current.getLayout().children();
            for (Ilayout child : children){
                double evaluation = minmax(new State(child, current), depth - 1, alpha, beta, false);
                if(depth == DEPTH && maxEvaluation < evaluation){
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

    public int getBestPlay(Ilayout layout, ID turn){
        this.closed = new HashMap<>();
        this.current = new State(layout, null);
        this.goal = -1;
        this.turn = turn;

        minmax(this.current, DEPTH, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true);
    
        return this.goal;
   }
}