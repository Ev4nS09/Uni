package AI;

import Game.*;
import Game.Ilayout.ID;

public class MinMaxAgente {

    // private static int calculateMove(Ilayout board, Ilayout bestPlay){
    //     for(int i = 0; i < board.getBoardSize(); i++)
    //         if(board.getAvailableMoves().contains(i) && !bestPlay.getAvailableMoves().contains(i))
    //             return i;
    //     return -1;
    // }

	public static int play(Ilayout board, ID turn) {
        return new MinMax().getBestPlay(board, turn);
    }
}
