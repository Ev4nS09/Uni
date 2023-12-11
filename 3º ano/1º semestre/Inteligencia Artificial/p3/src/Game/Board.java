package Game;

import java.util.*;

/** Tic-tac-toe Board that implements Ilayout.
 * @author Afonso Rio, Daniel Andrade 
 * @version 1.0 04/12/2023
 * @see MinMax
 */
public class Board implements Ilayout,Cloneable{

    private int rows;
    private int columns;
    private int rc;
    private int winningCondition;

    
    private ID[][] board;
    private ID playersTurn;
    private ID winner;
    private HashSet<Integer> movesAvailable;
    private List<Integer> movesNotAvaible;

    private int moveCount;
    private int lastMove;
    private int hashIndex;
    private boolean gameOver;

    
    /** Constructor that generates a new board with m {@code rows}, n {@code columns} a k {@code winningCondition}.
     * @param rows int
     * @param columns int
     * @param k int
     */
    public Board(int rows, int columns, int winningCondition) {

        if(rows != columns)
            throw new InputMismatchException("The number of rows must be equal to the number of columns.");
        if(rows < winningCondition)
            throw new InputMismatchException("K must be less or equal than the rows of the board.");
        
        this.rows = rows;
        this.columns = columns;
        this.rc = rows * columns;
        this.winningCondition = winningCondition;

        board = new ID[this.rows][this.columns];
        movesAvailable = new HashSet<>();
        movesNotAvaible = new LinkedList<>();

        reset();
    }

    /** Cunstructor generates a board of an ongoing game.
     * @param board ID[][]
     * @param k int
     */
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
        setAvailableAndNotAvailableMoves();
    }


    /** Updates the current move count.
     */
    private void setCurrentMoveCount() {
        int result = 0;
        for(int i = 0; i < rc; i++)
            if(board[i/rows][i%rows] != ID.Blank)
                result++;
        this.moveCount = result;
    }

    /** Calculates the last turn.
     * @return the last turn.
     */
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

    /** Updates the current available and not available moves
     */
    private void setAvailableAndNotAvailableMoves(){
        for(int i = 0; i < this.rc; i++){
            if(this.board[i/rows][i%columns] == ID.Blank)
                this.movesAvailable.add(i);
            else
                this.movesNotAvaible.add(i);
        }
            
    }
    /** Gets the oposite player.
     * @param turn ID
     * @return the oposite player.
     */
    private ID getOpositePlayer(ID turn){
        return (turn == ID.X) ? ID.O : ID.X;
    }

    /** Gets the oposite player of the current turn.
     * @return the oposite player of the current turn.
     */
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
        this.hashIndex = 0;
        initialize();
    }

    /** Gets the size of the board.
     * @return the size of the board.
     */
    public int size(){
        return this.rc;
    }

    /** Verifies if a winner exists in the board.
     * @return a boolean that if true a winner is in the board, else the game has no winner.
     */
    private boolean checkWinner(int i){
            if(getPotentialWin(i/this.rows,i%this.columns, this.playersTurn, 0, 1) == this.winningCondition) // Cheks if there is a horizontal line of k IDs
                return true;

            if(getPotentialWin(i/this.rows,i%this.columns, this.playersTurn, 1, 0) == this.winningCondition) // Cheks if there is a vertical line of k IDs
                return true;

            if(getPotentialWin(i/this.rows,i%this.columns, this.playersTurn, 1, 1) == this.winningCondition) // Cheks if there is a right diagonal line of k IDs
                return true;  

            if(getPotentialWin(i/this.rows,i%this.columns, this.playersTurn, 1, -1) == this.winningCondition) // Cheks if there is a left diagonal line of k IDs
                return true; 
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
        movesNotAvaible.add(lastMove);

        // The game is a draw.
        if (moveCount == rc) {
            winner = ID.Blank;
            gameOver = true;
        }

        if(checkWinner(lastMove)){
            winner = getTurn();
            gameOver = true;
        }

        hashIndex += this.playersTurn.ordinal() * Math.pow(3, lastMove);
        playersTurn = (playersTurn == ID.X) ? ID.O : ID.X;
        return true;
    }

    /** Gets the last move of the board.
     * @return the last move of the board.
     */
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

    public List<Integer> getNotAvailableMoves () {
        return movesNotAvaible;
    }

    

    /**
     * @return  an deep copy of the board
     */
    public Object clone () {
    	try {
	        Board b = (Board) super.clone();
	        b.board = new ID[rows][columns];
            
	        for (int i = 0; i < this.rows; i++)
	        	System.arraycopy(this.board[i], 0, b.board[i], 0, columns);

            b.rows = this.rows;
            b.columns = this.columns;
            b.winningCondition = this.winningCondition;    
            b.rc = this.rc;
	        b.playersTurn = this.playersTurn;
	        b.winner = this.winner;
	        b.movesAvailable = new HashSet<Integer>();
            b.movesNotAvaible = new ArrayList<Integer>();
	        b.movesAvailable.addAll(this.movesAvailable);
            b.movesNotAvaible.addAll(this.movesNotAvaible);
            b.lastMove = this.lastMove;
	        b.moveCount = this.moveCount;
	        b.gameOver = this.gameOver;
            b.hashIndex = this.hashIndex;

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

    private boolean contains(List<Ilayout> list, Board layout){
        for(Ilayout currentLayout : list)
            if(checkAllPossibleSolutions(((Board) currentLayout).board, layout.board))
                return true;
        return false;
    }
    
     /**
         * @param cutSymetric boolean
         * @return the children of the receiver.
     */   
     public List<Ilayout> children(boolean cutSymetric) {
 		List<Ilayout> result = new LinkedList<>();
        for(Integer index : movesAvailable){
            Board clone = (Board) this.clone();
            if(clone.move(index) && (!cutSymetric || !contains(result, clone)))
                result.add(clone);
        }

        return result;
     }

    /**
    * 
    * @return the children of the receiver.
     */  
     public List<Ilayout> children() {
        return children(false);
     }

     /** Verifies if two board are strictly equal.
      * @param board ID[][]
      * @param that ID[][]
      * @return a boolean if true the two boards are strictly equal, else they are not strictly equal.
      */
    public boolean isEqual(ID[][] board, ID[][] that){
        for (int y = 0; y < rows; y++) 
            for (int x = 0; x < columns; x++) 
            	if (board[x][y] != that[x][y]) 
                    return false;
        return true;
    }

    /** Verifies if a {@code board} is equal to another {@code board} (Two boards can be equal if you rotate them symetricly or not 4 times).
     * @param board
     * @param that
      * @return a boolean if true the two boards are equal, else they are not equal.
     */
    public boolean checkAllPossibleSolutions(ID[][] board, ID[][] that){
        boolean rotation0 = true;
        boolean rotation1 = true;
        boolean rotation2 = true;
        boolean rotation3 = true;

        boolean symetric0 = true;
        boolean symetric1 = true;
        boolean symetric2 = true;
        boolean symetric3 = true;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                
                if(that[i][j] != board[i][j])
                    rotation0 = false;
                if(that[j][Math.abs(i - (columns-1))] != board[i][j])
                    rotation1 = false;
                if(that[Math.abs(i - (columns-1))][Math.abs(j - (columns-1))] != board[i][j])
                    rotation2 = false;
                if(that[Math.abs(j - (columns-1))][Math.abs(Math.abs(i - (columns-1)) - (columns-1))] != board[i][j])
                    rotation3 = false;

                if(that[i][Math.abs(columns-1 - j)] != board[i][j])
                    symetric0 = false;
                if(that[Math.abs(columns-1 - j)][Math.abs(i - (columns-1))] != board[i][j])
                    symetric1 = false;
                if(that[Math.abs(i - (columns-1))][Math.abs(Math.abs(columns-1 - j) - (columns-1))] != board[i][j])
                    symetric2 = false;
                if(that[Math.abs(Math.abs(columns-1 - j) - (columns-1))][Math.abs(Math.abs(i - (columns-1)) - (columns-1))] != board[i][j])
                    symetric3 = false;
            
                if(!rotation0 && !rotation1 && !rotation2 && !rotation3 && !symetric0 && !symetric1 && !symetric2 && !symetric3)
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
        if(isEqual(this.board, that.board)) return true;
        
        return false;
	}
	
	@Override
	public int hashCode() { 
        return hashIndex;
	}

    /** Verifies if a position in the board is blank
     * @param index
     * @return true if the position is blank else returns false
     */
	public boolean isBlank (int index) {
		int x=index/rows;
		int y=index%rows;
        return (board[x][y] == ID.Blank);
	}

    /** Verifies if the given turn is the current turn
     * @param turn ID
     * @return true if the current turn equals the given turn, else returns false
     */
    public boolean isCurrentTurn(ID turn){
        return turn == getTurn();
    }


    /** Calculates the value of a current postion with a given jump.
     * @param turn ID
     * @param index int
     * @param jump int
     * @return the winning potential value.
     */
    private int getPotentialWin(int row, int column, ID turn, int jumpRow, int jumpColumn){
        int result = 0;
        int count = 0;

        int i = row;
        int j = column;

        int minRow = Math.min(this.rows - 1, row + ((this.winningCondition - 1) * Math.abs(jumpRow)));
        int minColumn = Math.min(this.columns - 1, column + ((this.winningCondition - 1) * Math.abs(jumpColumn)));
        
        int maxRow = Math.max(0, row - ((this.winningCondition - 1) * Math.abs(jumpRow)));
        int maxColumn = Math.max(0, column - ((this.winningCondition - 1) * Math.abs(jumpColumn)));

        while(i >= 0 && i <= minRow &&
              j >= 0 && j <= minColumn){
            if(getOpositePlayer(turn) == board[i][j])
                break;
            else if(turn == board[i][j])
                result++;
            count++;

            i += jumpRow;
            j += jumpColumn;
        }

        i = row - jumpRow;
        j = column - jumpColumn;
        while(i >= maxRow && i < this.rows && 
              j >= maxColumn && j < this.columns){
            if(getOpositePlayer(turn) == board[i][j])
                break;
            else if(turn == board[i][j])
                result++;
            count++;

            i -= jumpRow;
            j -= jumpColumn;
        }
        return count >= this.winningCondition ? result : 0;
    }

    /**Calculates the potentional win value of all the moves that have been made.
     * @param turn ID
     * @return the potentional win value of all the moves that have been made.
     */
    public double getHeuristic(ID turn){
        int result = 0;
        for(Integer index : movesNotAvaible){
            if(board[index/rows][index%columns] == turn){
                result += getPotentialWin(index/this.rows, index%this.columns, turn, 0, 1);
                result += getPotentialWin(index/this.rows, index%this.columns, turn, 1, 0);
                result += getPotentialWin(index/this.rows, index%this.columns, turn, 1, 1);
                result += getPotentialWin(index/this.rows, index%this.columns, turn, 1, -1);
            }
        }
        return result;
    }

    /** Returns the {@code Heuristic} of the given turn minus the {@code Heuristic} of the oposite player.
     * @param turn ID
     * @return the {@code Heuristic} of the given turn minus the {@code Heuristic} of the oposite player.
     */
    public double getEvaluation(ID turn){
        if (this.winner == turn)
            return 999999;
        else if(this.winner == getOpositePlayer(turn))
            return -999999; 

        return getHeuristic(turn) - getHeuristic(getOpositePlayer(turn));
    }
}

//     public void getAllBoards(ID[][] b){
//         ID[][] currentBoard = b;
//         for(int i = 0; i < 8; i++){
//             currentBoard = rotateboard(currentBoard);
//             if(i == 3) currentBoard = getHorizontalSymetric(currentBoard);
//             System.out.println(new Board(currentBoard, b.length));
//             System.out.println();
//         }	
//     }
// }


    // private int getBinaryBoardNumber(ID[][] board){
    //     String resultX = "";
    //     String resultO = "";
    //     for(int i = 0; i < rows; i++){
    //         for(int j = 0; j < columns; j++){
    //             if(board[i][j] == ID.X){
    //                 resultX = "1" + resultX;
    //                 resultO = "0" + resultO;          
    //             }
    //             else if(board[i][j] == ID.O){                    
    //                 resultX = "0" + resultX;
    //                 resultO = "1" + resultO;   
    //             }
    //             else{
    //                 resultX = "0" + resultX;
    //                 resultO = "0" + resultO;           
    //             }
    //         }
    //     }
    //     return binaryToDecimal(resultX) + binaryToDecimal(resultO);
    // }

    //     public static int binaryToDecimal(String binary){
    //     int result = 0;
    //     for(int i = 0; i < binary.length(); i++)
    //         result += (binary.charAt(i) / 49) * Math.pow(2, (binary.length() - 1) - i);
    //     return result;
    // }

    //     private int getValue(ID id, int i){
    //     int result = 0;

    //     if(id == ID.X)
    //         result = (int) Math.pow(2, (rc - 1) - i);

    //     else if(id == ID.O)
    //         result = (int) Math.pow(3, (rc - 1) - i);

    //     return result;
    // }

    // private int[] getHashArray(){
    //     int result[] = new int[8];
    //     for(int i = 0; i < rows; i++){
    //         for(int j = 0; j < columns; j++){
    //             result[0] += getValue(this.board[i][j], i);
    //             result[1] += getValue(this.board[j][Math.abs(i - (columns-1))], i);
    //             result[2] += getValue(this.board[Math.abs(i - (columns-1))][Math.abs(j - (columns-1))], i);
    //             result[3] += getValue(this.board[Math.abs(j - (columns-1))][Math.abs(Math.abs(i - (columns-1)) - (columns-1))], i);

    //             result[4] += getValue(this.board[i][Math.abs(columns-1 - j)], i);
    //             result[5] += getValue(this.board[Math.abs(columns-1 - j)][Math.abs(i - (columns-1))], i);
    //             result[6] += getValue(this.board[Math.abs(i - (columns-1))][Math.abs(Math.abs(columns-1 - j) - (columns-1))], i);
    //             result[7] += getValue(this.board[Math.abs(Math.abs(columns-1 - j) - (columns-1))][Math.abs(Math.abs(i - (columns-1)) - (columns-1))], i);
    //         }
    //     }
    //     return result;
    // }

        // public ID[][] rotateboard(ID[][] board){
    //     ID[][] result = new ID[rows][columns];
    //     for(int i = 0; i < rows; i++)
    //         for(int j = 0; j < columns; j++)
    //             result[j][Math.abs(i - (columns-1))] = board[i][j];
    //     return result;
    // }
    
    // public ID[][] rotateboard(){
    //     return rotateboard(this.board);
    // }

    //     public int getMinFromArray(int[] array){
    //     int result = Integer.MAX_VALUE;
    //     for(int i = 0; i < array.length; i++)
    //         result = Math.min(result, array[i]);
    //     return result;
    // }

    //     public ID[][] getHorizontalSymetric(ID[][] board){
    //     ID[][] result = new ID[rows][columns];
    //     for(int i = 0; i < rows; i++)
    //         for(int j = 0; j < columns; j++)
    //             result[i][Math.abs(columns-1 - j)] = board[i][j];
    //     return result;
    // }

    //     private int getValue(ID id, int i){
    //     int result = 0;

    //     if(id == ID.X)
    //         result = (int) Math.pow(2, (rc - 1) - i);

    //     else if(id == ID.O)
    //         result = (int) Math.pow(3, (rc - 1) - i);

    //     return result;
    // }

    // private int[] getHashArray(){
    //     int result[] = new int[8];
    //     for(int i = 0; i < rows; i++){
    //         for(int j = 0; j < columns; j++){
    //             result[0] += getValue(this.board[i][j], i);
    //             result[1] += getValue(this.board[j][Math.abs(i - (columns-1))], i);
    //             result[2] += getValue(this.board[Math.abs(i - (columns-1))][Math.abs(j - (columns-1))], i);
    //             result[3] += getValue(this.board[Math.abs(j - (columns-1))][Math.abs(Math.abs(i - (columns-1)) - (columns-1))], i);

    //             result[4] += getValue(this.board[i][Math.abs(columns-1 - j)], i);
    //             result[5] += getValue(this.board[Math.abs(columns-1 - j)][Math.abs(i - (columns-1))], i);
    //             result[6] += getValue(this.board[Math.abs(i - (columns-1))][Math.abs(Math.abs(columns-1 - j) - (columns-1))], i);
    //             result[7] += getValue(this.board[Math.abs(Math.abs(columns-1 - j) - (columns-1))][Math.abs(Math.abs(i - (columns-1)) - (columns-1))], i);
    //         }
    //     }
    //     return result;
    // }

    // public int getMinFromArray(int[] array){
    //     int result = Integer.MAX_VALUE;
    //     for(int i = 0; i < array.length; i++)
    //         result = Math.min(result, array[i]);
    //     return result;
    // }


    //     private int getHashIndex(int lastMove){
    //     int result = (int) Math.pow(this.playersTurn.ordinal() + 1, (rc - 1) - lastMove);
    //     int currentIndex = lastMove;

    //     currentIndex = rotateIndex(currentIndex);
    //     result = Math.min(result, (int) Math.pow(this.playersTurn.ordinal() + 1, (rc - 1) - currentIndex));

    //     currentIndex = rotateIndex(currentIndex);
    //     result = Math.min(result, (int) Math.pow(this.playersTurn.ordinal() + 1, (rc - 1) - currentIndex));

    //     currentIndex = rotateIndex(currentIndex);
    //     result = Math.min(result, (int) Math.pow(this.playersTurn.ordinal() + 1, (rc - 1) - currentIndex));

        
    //     currentIndex = symetricIndex(lastMove);
    //     result = Math.min(result, (int) Math.pow(this.playersTurn.ordinal() + 1, (rc - 1) - currentIndex));

    //     currentIndex = rotateIndex(currentIndex);
    //     result = Math.min(result, (int) Math.pow(this.playersTurn.ordinal() + 1, (rc - 1) - currentIndex));

    //     currentIndex = rotateIndex(currentIndex);
    //     result = Math.min(result, (int) Math.pow(this.playersTurn.ordinal() + 1, (rc - 1) - currentIndex));

    //     currentIndex = rotateIndex(currentIndex);
    //     result = Math.min(result, (int) Math.pow(this.playersTurn.ordinal() + 1, (rc - 1) - currentIndex));

    //     return result;
    // }

    //     private int rotateIndex(int index){
    //     int r = index / rows;
    //     int c = index % columns;
    //     return (rows * c) + Math.abs(r - (columns-1));
    // }

    // private int symetricIndex(int index){
    //     int r = index / rows;
    //     int c = index % columns;
    //     return (rows * r) +  Math.abs(columns-1 - c);
    // }


    //     /** Returns a boolean that if true, a winner is in the given index. 
    //  * @param index int
    //  * @param range int
    //  * @param jump int
    //  * @return a boolean that if true, there is a winner in the index else there ins't a winner in the given index.
    //  */
    // private boolean analyseWinner(int index, int range, int jump){
    //     for(int j = index; range >= 0; j += jump){
    //         if(!isCurrentTurn(board[j/rows][j%columns]))
    //             break;
    //         if(--range == 0)
    //             return true;
    //     }
    //     return false;
    // }


