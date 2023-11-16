package AI;

import java.util.*;
import Game.*;

public class MinMax {
    
    private State current;

    private double minmax(State current, int depth, double alpha, double beta, boolean maximizingPlayer){
        if(depth == 0 || current.getLayout().isGameOver())
            return current.getEvaluation();
        
        if(maximizingPlayer){
            double maxEvaluation = Double.NEGATIVE_INFINITY;
            List<Ilayout> children = current.getLayout().children();
            for(Ilayout child : children){
                double evaluation = minmax(new State(child, current), depth - 1, alpha, beta, false);
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

    public State getBestPlay(Ilayout layout){
        this.current = new State(layout, null);

        State result = null;
        double bestPlayValue = minmax(this.current, 8, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true);
        List<Ilayout> children = layout.children();

        for(Ilayout child : children)
            if(child.getEvaluation() == bestPlayValue)
                result = new State(child, this.current);
        
        return result;
   }
}
