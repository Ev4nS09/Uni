package AI;

import Game.*;
import Game.Ilayout.ID;

public class MinMaxAgente {

	public static int play(Ilayout board, ID turn) {
        return new MinMax().getBestPlay(board, turn);
    }
    
}
