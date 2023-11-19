package Game;

import java.util.*;
// import java.util.ArrayList;

/**
 * Represents a board.
 */
public class Board implements Ilayout,Cloneable{

    private int rows;
    private int columns;
    private int rc;
    private int k;

    
    private ID[][] board;
    private ID playersTurn;
    private ID winner;
    private HashSet<Integer> movesAvailable;

    private int moveCount;
    private boolean gameOver;
    
    public Board(int rows, int columns, int k) {

        if(rows != columns)
            throw new InputMismatchException("The number of rows must be equal to the number of columns.");
        if(rows < k)
            throw new InputMismatchException("K must be less than the rows of the board.");
        
        this.rows = rows;
        this.columns = columns;
        this.rc = rows * columns;
        this.k = k;

        board = new ID[this.rows][this.columns];
        movesAvailable = new HashSet<>();

        reset();
    }

    public Board(ID[][] board, int k){
        this(board.length, board.length > 0 ? board[0].length : 0, k);

        if(board.length <= 0 || board[0].length <= 0)
            throw new InputMismatchException("Board must not be empty.");
            
        this.board = board;

        this.playersTurn = calculateLastTurn();
        if(this.gameOver = checkWinner())
             this.winner = this.playersTurn;

        this.playersTurn = getOpositePlayer();
        setCurrentMoveCount();
    }


    private void setCurrentMoveCount() {
        int result = 0;
        for(int i = 0; i < rc; i++)
            if(board[i/rows][i%rows] != ID.Blank)
                result++;
        this.moveCount = result;
    }

    private ID calculateLastTurn(){
        int X = 0;
        int O = 0;
        for(int i = 0; i < this.rc; i++){
            if(board[i/rows][i%rows] == ID.X)
                X++;
            else if(board[i/rows][i%rows] == ID.O)
                O++;
        }
        return X == O ? ID.O : ID.X;
    } 

    private ID getOpositePlayer(ID turn){
        return (turn == ID.X) ? ID.O : ID.X;
    }

    private ID getOpositePlayer(){
        return getOpositePlayer(this.playersTurn);
    }

    /**
     * Set the cells to be blank and load the available moves (all the moves are
     * available at the start of the game).
     */
    private void initialize () {
        for (int row = 0; row < this.rows; row++)
            for (int col = 0; col < this.columns; col++) {
                board[row][col] = ID.Blank;
            }
        movesAvailable.clear();

        for (int i = 0; i < this.rc; i++) {
            movesAvailable.add(i);
        }
    }

    /**
     * Restart the game with a new blank board.
     */
    public void reset() {
        this.moveCount = 0;
        this.gameOver = false;
        this.playersTurn = ID.X;
        this.winner = ID.Blank;
        initialize();
    }

    public int getBoardSize(){
        return rc;
    }

    private boolean analyseWinner(int index, int range, int jump){
        for(int j = index; range >= 0; j += jump){
            if(!isCurrentTurn(board[j/rows][j%rows]))
                break;
            if(--range == 0)
                return true;
        }
        return false;
    }

    private boolean checkWinner(){
        for(int i = 0; i < rc; i++){
            if(i + k - 1 < columns && analyseWinner(i, k,1)) // Cheks if there is a horizontal line of k IDs
                return true;

            if(i + (k-1)*rows < rc && analyseWinner(i, k, rows)) // Cheks if there is a vertical line of k IDs
                return true;

            if(i + (k-1) < columns && i + (k-1)*rows + (k-1) < rc && analyseWinner(i, k, rows + 1)) // Cheks if there is a right diagonal line of k IDs
                return true;  

            if(i%columns - (k-1) >= 0 && i + (k-1)*rows - (k-1) < rc && analyseWinner(i, k, rows - 1)) // Cheks if there is a left diagonal line of k IDs
                return true; 
        }
        return false;
    }

    /**
     * Places an X or an O on the specified index depending on whose turn it is.
     * @param index     position starts in 0 and increases from left to right and from top to bottom
     * @return          true if the move has not already been played
     */
    public boolean move (int index) {
        return move(index % rows, index/rows);
    }

    /**
     * Places an X or an O on the specified location depending on who turn it is.
     * @param x         the x coordinate of the location
     * @param y         the y coordinate of the location
     * @return          true if the move has not already been played
     */
   public boolean move (int x, int y) {

        if (gameOver) {
            throw new IllegalStateException("Game over. No more moves can be played.");
        }

        if (board[y][x] == ID.Blank) {
            board[y][x] = playersTurn;
        } else {
            return false;
        }

        moveCount++;
        movesAvailable.remove(y * rows + x);

        // The game is a draw.
        if (moveCount == rc) {
            winner = ID.Blank;
            gameOver = true;
        }

        if(checkWinner()){
            winner = getTurn();
            gameOver = true;
        }

        playersTurn = (playersTurn == ID.X) ? ID.O : ID.X;
        return true;
    }

    /**
     * Check to see if the game is over (if there is a winner or a draw).
     * @return          true if the game is over
     */
    public boolean isGameOver () {
        return gameOver;
    }

    

    /**
     * Check to see who's turn it is.
     * @return          the player who's turn it is
     */
    public ID getTurn () {
        return playersTurn;
    }

    /**
     * @return          the player who won (or Blank if the game is a draw)
     */
    public ID getWinner () {
        if (!gameOver) {
            throw new IllegalStateException("Not over yet!");
        }
        return winner;
    }

    /**
     * Get the indexes of all the positions on the board that are empty.
     * @return          the empty cells
     */
    public HashSet<Integer> getAvailableMoves () {
        return movesAvailable;
    }

    

    /**
     * @return  an deep copy of the board
     */
    public Object clone () {
    	try {
	        Board b = (Board) super.clone();
	        b.board = new ID[rows][columns];
            
	        for (int i = 0; i < this.rows; i++)
	        	for (int j = 0; j < this.columns; j++)
	        		b.board[i][j] = this.board[i][j];
                    
	        b.playersTurn       = this.playersTurn;
	        b.winner            = this.winner;
	        b.movesAvailable    = new HashSet<Integer>();
	        b.movesAvailable.addAll(this.movesAvailable);
	        b.moveCount         = this.moveCount;
	        b.gameOver          = this.gameOver;
	        return b;
    	}
    	catch (Exception e) {
    		throw new InternalError();
    	}
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {

                if (board[y][x] == ID.Blank) {
                    sb.append("-");
                } else {
                    sb.append(board[y][x].name());
                }
                sb.append(" ");

            }
            if (y != rows -1) {
                sb.append("\n");
            }
        }
        return new String(sb);
    }  
        
     /**
         * 
         * @return the children of the receiver.
     */   
     public List<Ilayout> children() {
 		List<Ilayout> result = new LinkedList<>();
        for(int i = 0; i < rc; i++){
            Board clone = (Board) this.clone();
            if(clone.move(i))
                result.add(clone);   
        }
        return result;
     }
   

	@Override
	public boolean equals(Object other) {     
		if (other == this) return true;
		if (other == null) return false;
		if (getClass() != other.getClass()) return false;
		Board that = (Board) other;
		
		for (int y = 0; y < rows; y++) 
            for (int x = 0; x < columns; x++) 
            	if (board[x][y] != that.board[x][y]) return false;
        return true;
	}
		
	@Override
	public int hashCode() {
		return board.hashCode();	
	}
		
	public boolean isBlank (int index) {
		int x=index/rows;
		int y=index%rows;
        return (board[x][y] == ID.Blank);
	}

    public boolean isCurrentTurn(ID turn){
        return turn.name().equals(getTurn().name());
    }


    private double checkAllPotentialWins(int index, ID turn){
        int result = 0;
        int row = index / rows;
        int column = index % columns;
        for(int j = Math.max(0, index - k); j < Math.min(rc, index + k); j++){
            for(int p = j; p < p + this.k && p < (row+1) * rows; p++){
                if(board[p/rows][p/columns] != getOpositePlayer(turn)){
                    result++;
                    break;
                }
            }
        }
        for(int j = Math.max(index%columns, index - k); j < Math.min(rc, index + k); j+=rows){
            for(int p = j; p < p + this.k; p++){
                if(board[p/rows][p/columns] != getOpositePlayer(turn)){
                    result++;
                    break;
                }
            }
        }
    }

    private double getHeuristic(ID turn){
        double result = 0;
        for(int i = 0; i < rc; i++)
            if(board[i/rows][i%columns] == turn)
                result += checkAllPotentialWins(i, turn);
            
        return result;

    }

    public double getHeuristic(){
        return getHeuristic(playersTurn);
    }

    public double getEvaluation(){
        return getHeuristic() - getHeuristic(getOpositePlayer());
    }
}
