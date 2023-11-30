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
    private int lastMove;
    private boolean gameOver;

    
    public Board(int rows, int columns, int k) {

        if(rows != columns)
            throw new InputMismatchException("The number of rows must be equal to the number of columns.");
        if(rows < k)
            throw new InputMismatchException("K must be less or equal than the rows of the board.");
        
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

    public ID getOpositePlayer(){
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
        this.lastMove = -1;
        initialize();
    }

    public int size(){
        return rc;
    }

    private boolean analyseWinner(int index, int range, int jump){
        for(int j = index; range >= 0; j += jump){
            if(!isCurrentTurn(board[j/rows][j%columns]))
                break;
            if(--range == 0)
                return true;
        }
        return false;
    }

    private boolean checkWinner(){
        for(int i = 0; i < rc; i++){
            if(i % columns + (k-1) < columns && analyseWinner(i, k,1)) // Cheks if there is a horizontal line of k IDs
                return true;

            if(i / columns + (k-1) < rows && analyseWinner(i, k, rows)) // Cheks if there is a vertical line of k IDs
                return true;

            if(i % columns + (k-1) < columns && i / rows + (k-1) < rows && analyseWinner(i, k, rows + 1)) // Cheks if there is a right diagonal line of k IDs
                return true;  

            if(i % columns - (k-1) >= 0 && i / rows + (k-1) < rows && analyseWinner(i, k, rows - 1)) // Cheks if there is a left diagonal line of k IDs
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
        lastMove = rows * y + x;
        movesAvailable.remove(lastMove);

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

    public int getLastMove(){
        return this.lastMove;
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

    public boolean isEqual(ID[][] board, ID[][] that){
        for (int y = 0; y < rows; y++) 
            for (int x = 0; x < columns; x++) 
            	if (board[x][y] != that[x][y]) 
                    return false;
        return true;
    }

    private boolean checkRotation(ID[][] board, ID[][] that){
        boolean x0 = true;
        boolean x1 = true;
        boolean x2 = true;
        boolean x3 = true;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(that[i][j] != board[i][j])
                    x0 = false;
                if(that[j][Math.abs(i - (columns-1))] != board[i][j])
                    x1 = false;
                if(that[Math.abs(i - (columns-1))][Math.abs(j - (columns-1))] != board[i][j])
                    x2 = false;
                if(that[Math.abs(j - (columns-1))][Math.abs(Math.abs(i - (columns-1)) - (columns-1))] != board[i][j])
                    x3 = false;
                if(!x0 && !x1 && !x2 && !x3)
                    return false;
            }
        }

        return true;
    }


	@Override
	public boolean equals(Object other) {     
		if (other == this) return true;
		if (other == null) return false;
		if (getClass() != other.getClass()) return false;

		Board that = (Board) other;
        if(that.rc != this.rc) return false;
        if(checkRotation(this.board, that.board) || checkRotation(getHorizontalSymetric(this.board), that.board)) return true;
        
        return false;
	}

    private int getBinaryBoardNumber(ID[][] board){
        String resultX = "";
        String resultO = "";
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(board[i][j] == ID.X){
                    resultX = "1" + resultX;
                    resultO = "0" + resultO;          
                }
                else if(board[i][j] == ID.O){                    
                    resultX = "0" + resultX;
                    resultO = "1" + resultO;   
                }
                else{
                    resultX = "0" + resultX;
                    resultO = "0" + resultO;           
                }
            }
        }
        return binaryToDecimal(resultX) + binaryToDecimal(resultO);
    }

    public ID[][] getHorizontalSymetric(ID[][] board){
        ID[][] result = new ID[rows][columns];
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++)
                result[i][Math.abs(columns-1 - j)] = board[i][j];
        return result;

    }

    public ID[][] rotateboard(ID[][] board){
        ID[][] result = new ID[rows][columns];
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++)
                result[j][Math.abs(i - (columns-1))] = board[i][j];
        return result;
    }
    
    public ID[][] rotateboard(){
        return rotateboard(this.board);
    }

    public static int binaryToDecimal(String binary){
        int result = 0;
        for(int i = 0; i < binary.length(); i++)
            result += (binary.charAt(i) / 49) * Math.pow(2, (binary.length() - 1) - i);
        return result;
    }

    public int getMinFromArray(int[] array){
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < array.length; i++)
            result = Math.min(result, array[i]);
        return result;
    }
		
	@Override
	public int hashCode() {        
		int[] binaryArray = new int[8];
        ID[][] currentBoard = this.board;
        for(int i = 0; i < 8; i++){
            binaryArray[i] = getBinaryBoardNumber(currentBoard);
            currentBoard = rotateboard(currentBoard);
            if(i == 3) currentBoard = getHorizontalSymetric(currentBoard);
        }	
        return getMinFromArray(binaryArray);
	}
		
	public boolean isBlank (int index) {
		int x=index/rows;
		int y=index%rows;
        return (board[x][y] == ID.Blank);
	}

    public boolean isCurrentTurn(ID turn){
        return turn == getTurn();
    }


    private int getPotentialWin(ID turn, int index, int jump){
        int result = 0;
        int count = 0;
        for(int i = index; i <= Math.min(index + ((this.k-1) * jump), rc-1); i+=jump){
            if(getOpositePlayer(turn) == board[i/rows][i%columns])
                break;
            else if(turn == board[i/rows][i%columns])
                result++;
            count++;
        }
        for(int i = index; i >= Math.max(index - ((this.k-1) * jump), 0); i-=jump){
            if(getOpositePlayer(turn) == board[i/rows][i%columns])
                break;
            else if(turn == board[i/rows][i%columns])
                result++;
            count++;
        }
        return count >= this.k ? result : 0;
    }

    public double getHeuristic(ID turn){
        int result = 0;
        for(int i = 0; i < rc; i++){
            if(board[i/rows][i%columns] == turn){
                result += getPotentialWin(turn, i, 1);
                result += getPotentialWin(turn, i, rows);
                result += getPotentialWin(turn, i, rows + 1);
                result += getPotentialWin(turn, i, rows - 1);
            }
        }
        return result;
    }

    public double getEvaluation(ID turn){
        if (this.winner == turn)
            return 999999;
        else if(this.winner == getOpositePlayer(turn))
            return -999999; 

        return getHeuristic(turn) - getHeuristic(getOpositePlayer(turn));
    }

    public void getAllBoards(ID[][] b){
        ID[][] currentBoard = b;
        for(int i = 0; i < 8; i++){
            currentBoard = rotateboard(currentBoard);
            if(i == 3) currentBoard = getHorizontalSymetric(currentBoard);
            System.out.println(new Board(currentBoard, b.length));
            System.out.println();
        }	
    }
}
