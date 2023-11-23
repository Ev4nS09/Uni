package Console;

import Game.*;
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

    private Console(int rows, int columns, int k){
        this.rows = rows;
        this.columns = columns;
        this.k = k;

        board = new Board(rows, columns, k);
    }

    /**
     * Game on
     */
    private void play () {
        while (true) {
            playMove();
            // System.out.println(this.board);
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
    	
        if (board.getTurn() == Ilayout.ID.X) {
        	// position=getHumanMove();
        	position = MinMaxAgente.play(board);
        	board.move(position);
 
        } else {
            // position=getHumanMove();
        	position = MinMaxAgente.play(board);
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

    	final int repetitions=1;
    	long times = 0;
    	for(int i=0; i<repetitions; i++) {
            Console game = new Console(4, 4, 4);
            long startTime = System.currentTimeMillis();
            game.play();   
            long totalTime = System.currentTimeMillis() - startTime;
            times += totalTime;
        }
        System.out.println("Av Time: " + times*1.0f/repetitions+ " milisecs");    
    }
}
