package AI;

import Game.*;
import Game.Ilayout.ID;

/** MinMaxAgent is the class that represents the agent that uses the algorithm minmax to get the best move of an {@link Ilayout}
 * @author Afonso Rio, Daniel Andrade 
 * @version 1.0 04/12/2023
 * @see MinMax
 */
public class MinMaxAgent {

    /** Returns the best play.
     * @param board Ilayout
     * @param turn ID
     * @param depth int
     * @return the best play.
     */
	public static int play(Ilayout board, ID turn, int depth) {
        return new MinMax().getBestPlay(board, turn, depth);
    }
    
}
