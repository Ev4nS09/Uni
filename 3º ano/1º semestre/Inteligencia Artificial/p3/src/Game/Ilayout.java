package Game;

import java.util.*;

public interface Ilayout {

    public enum ID{Blank, X, O}



    /**
     * Places an X or an O on the specified index depending on whose turn it is.
     * @param index     the position on the board (example: index BOARD_WIDTH is location (0, 1))
     * @return          true if the move has not already been played
     */
    public boolean move (int index);


    /**
     * @return          true if the game is over; false otherwise
     */
    public boolean isGameOver(); 

    
    /**
     * @return          the ID for the current Turn
     */
    public ID getTurn();

    /**
     * @return          the player who won (or Blank if the game is a draw)
     */
    public ID getWinner();

    /**
     * Get the indexes of all the positions on the board that are empty.
     * @return          the empty cells
     */
    public HashSet<Integer> getAvailableMoves();
        

    /**
     * 
     * @return the children of the receiver.
    */
    public List<Ilayout> children ();

    public double getHeuristic(ID turn);

    public double getEvaluation();

    public int getBoardSize();

    @Override
    public boolean equals(Object layout);



}
