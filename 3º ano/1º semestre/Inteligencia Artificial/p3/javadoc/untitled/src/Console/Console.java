package Console;

import Game.*;
import Game.Ilayout.ID;
import AI.*;

import java.util.*;


/**
 * For playing in the console.
 */
public class Console {
    private Board board;
    private int rows;
    private int columns;
    private int k;
    private int depth;

    private Scanner sc = new Scanner(System.in);

    private Console(){

        System.out.print("Rows: ");
        this.rows = Integer.parseInt(sc.next());

        System.out.print("Columns: ");
        this.columns = Integer.parseInt(sc.next());

        System.out.print("Winner's length: ");
        this.k = Integer.parseInt(sc.next());

        board = new Board(rows, columns, k);
    }

    private Console(int rows, int columns, int k, int depth){
        this.rows = rows;
        this.columns = columns;
        this.k = k;
        this.depth = depth;

        board = new Board(rows, columns, k);
    }

    /**
     * Game on
     */
    private void play () {
        while (true) {
            playMove();
            System.out.println(this.board + "\n");
            // System.out.println("++++++++++++++++++++++++");
            if (board.isGameOver()) {
                printWinner();
                break;
            }
        }
    }

    
    /**
     * Handle the move to be played,(
     */
   
    private void playMove () {
    	int position;
    	
        if (board.getTurn() == ID.X) {
        	// position=getHumanMove();
        	position = MinMaxAgent.play(board, ID.X, this.depth);
        	board.move(position);
 
        } else {
            // position=getHumanMove();
        	position = MinMaxAgent.play(board, ID.O, this.depth);
        	board.move(position);
        }
    }
 
    private void printGameStatus () {
        System.out.println("\n" + board + "\n");
        System.out.println(board.getTurn().name() + "'s turn.");
    }

    /**
     * For reading in and interpreting the move that the user types into the console.
     */
    private int getHumanMove() {
        printGameStatus ();
        System.out.print("Index of move: ");

        int move = sc.nextInt();

        if (move < 0 || move >= rows * columns) {
            System.out.println("\nInvalid move.");
            System.out.println("\nThe index of the move must be between 0 and "
                    + (rows * columns - 1) + ", inclusive.");
        } else if (!board.isBlank(move)) {
            System.out.println("\nInvalid move.");
            System.out.println("\nThe selected index must be blank.");
        }
        return move;
    }

    
    private void printWinner () {
        Ilayout.ID winner = board.getWinner();

        System.out.println("\n" + board + "\n");

        if (winner == Ilayout.ID.Blank) {
            System.out.println("It's a draw.");
        } else {
            System.out.println("Player " + winner.toString() + " wins!");
        }
    }

    

    public static void main(String[] args){
        // ID[][] array = {
        //     {ID.X, ID.Blank, ID.Blank},
        //     {ID.Blank, ID.Blank, ID.Blank},
        //     {ID.Blank, ID.Blank, ID.Blank}
        // };
        // new Board(3, 3, 3).getAllBoards(array);
        // System.exit(0);

    	final int repetitions=1;
        int x = 4;
    	long times = 0;
    	for(int i=0; i<repetitions; i++) {
            Console game = new Console(x, x, x, 16);
            long startTime = System.currentTimeMillis();
            game.play();   
            long totalTime = System.currentTimeMillis() - startTime;
            times += totalTime;
        }
        System.out.println("Av Time: " + times*1.0f/repetitions+ " milisecs");    
        // Board a = new Board(4, 4, 0);
        // System.out.println(a.children());
    }
}
