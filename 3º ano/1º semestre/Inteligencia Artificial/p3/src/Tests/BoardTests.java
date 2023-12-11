package Tests;

import Game.*;


import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

            // {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            // {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            // {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            // {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},

public class BoardTests{

    @Test
    public void DrawTest(){

        Ilayout.ID array[][] = {
            {Ilayout.ID.X,      Ilayout.ID.X,     Ilayout.ID.X,     Ilayout.ID.O},
            {Ilayout.ID.O,      Ilayout.ID.X,     Ilayout.ID.X,     Ilayout.ID.X},
            {Ilayout.ID.O,      Ilayout.ID.O,     Ilayout.ID.X,     Ilayout.ID.O},
            {Ilayout.ID.O,      Ilayout.ID.X,     Ilayout.ID.O,     Ilayout.ID.Blank},
            };

        Board board = new Board(array, 4);
        board.move(15);

        assertTrue(board.isGameOver());
        assertEquals(board.getWinner().name(), Ilayout.ID.Blank.name());
    }


    @Test
    public void WinnerHorinzotalLineTest(){

        Ilayout.ID array[][] = {
            {Ilayout.ID.X,      Ilayout.ID.X,     Ilayout.ID.X,     Ilayout.ID.Blank},
            {Ilayout.ID.Blank,  Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank},
            {Ilayout.ID.O,      Ilayout.ID.O,     Ilayout.ID.Blank, Ilayout.ID.Blank},
            {Ilayout.ID.O,      Ilayout.ID.Blank, Ilayout.ID.Blank, Ilayout.ID.Blank},
            };

        Board board = new Board(array, 4);
        board.move(3);

        assertTrue(board.isGameOver());
        assertEquals(board.getWinner().name(), Ilayout.ID.X.name());
    }

    @Test
    public void WinnerVerticalLineTest(){

        Ilayout.ID array[][] = {
            {Ilayout.ID.X,          Ilayout.ID.O,         Ilayout.ID.Blank,     Ilayout.ID.Blank},
            {Ilayout.ID.X,          Ilayout.ID.O,         Ilayout.ID.O,         Ilayout.ID.Blank},
            {Ilayout.ID.X,          Ilayout.ID.Blank,     Ilayout.ID.Blank,     Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,     Ilayout.ID.Blank,     Ilayout.ID.Blank},
            };

        Board board = new Board(array, 4);
        board.move(12);

        assertTrue(board.isGameOver());
        assertEquals(board.getWinner().name(), Ilayout.ID.X.name());
    }

    @Test
    public void WinnerDiagonalRightlLineTest(){

        Ilayout.ID array[][] = {
            {Ilayout.ID.X,          Ilayout.ID.O,           Ilayout.ID.Blank,       Ilayout.ID.O},
            {Ilayout.ID.Blank,      Ilayout.ID.X,           Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.X,           Ilayout.ID.Blank},
            {Ilayout.ID.O,          Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            };

        Board board = new Board(array, 4);
        board.move(15);

        assertTrue(board.isGameOver());
        assertEquals(board.getWinner().name(), Ilayout.ID.X.name());

    }


    @Test
    public void WinnerDiagonalLeftlLineTest(){

        Ilayout.ID array[][] = {
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.X},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.X,           Ilayout.ID.O},
            {Ilayout.ID.Blank,      Ilayout.ID.X,           Ilayout.ID.O,           Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.O,           Ilayout.ID.Blank,       Ilayout.ID.Blank},
            };

        Board board = new Board(array, 4);
        board.move(12);

        assertTrue(board.isGameOver());
        assertEquals(board.getWinner().name(), Ilayout.ID.X.name());

    }
    
    @Test
    public void FailDiagonalLeftlLineTest(){

        Ilayout.ID array[][] = {
            {Ilayout.ID.X,          Ilayout.ID.O,           Ilayout.ID.Blank,       Ilayout.ID.O},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.X,           Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.X,           Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.O,           Ilayout.ID.Blank},
            };

        Board board = new Board(array, 4);
        board.move(12);

        assertFalse(board.isGameOver());

    }

    @Test
    public void WinnerHorinzotalLineTestDiffSize(){
        Ilayout.ID array[][] = {
            {Ilayout.ID.X,          Ilayout.ID.X,           Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.O,          Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.O,          Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            };

        Board board = new Board(array, 3);
        board.move(2);

        assertTrue(board.isGameOver());
        assertEquals(board.getWinner().name(), Ilayout.ID.X.name());
    }

    @Test
    public void WinnerVerticalLineTestDiffSize(){
        Ilayout.ID array[][] = {
            {Ilayout.ID.X,          Ilayout.ID.O,           Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.X,          Ilayout.ID.O,           Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            };

        Board board = new Board(array, 3);
        board.move(8);

        assertTrue(board.isGameOver());
        assertEquals(board.getWinner().name(), Ilayout.ID.X.name());
    }

    @Test
    public void WinnerDiagonalRightLineTestDiffSize(){
        Ilayout.ID array[][] = {
            {Ilayout.ID.X,          Ilayout.ID.O,           Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.X,           Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.O,          Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            };

        Board board = new Board(array, 3);
        board.move(10);

        assertTrue(board.isGameOver());
        assertEquals(board.getWinner().name(), Ilayout.ID.X.name());
    }

    @Test
    public void WinnerDiagonalLeftLineTestDiffSize(){
        Ilayout.ID array[][] = {
            {Ilayout.ID.O,          Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.X},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.X,           Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.O},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            };

        Board board = new Board(array, 3);
        board.move(9);

        assertTrue(board.isGameOver());
        assertEquals(board.getWinner().name(), Ilayout.ID.X.name());
    }

    public boolean listEquals(List<Ilayout> children, List<Ilayout> children1){
        if(children.size() != children1.size()) 
            return false;

        for(int i = 0; i < children.size(); i++)
            if(!children.equals(children1))
                return false;

        return true;
    }

    @Test
    public void Children0Test(){

        Ilayout.ID array[][] = {
            {Ilayout.ID.X,      Ilayout.ID.X,     Ilayout.ID.X,     Ilayout.ID.O},
            {Ilayout.ID.O,      Ilayout.ID.X,     Ilayout.ID.X,     Ilayout.ID.X},
            {Ilayout.ID.O,      Ilayout.ID.O,     Ilayout.ID.X,     Ilayout.ID.O},
            {Ilayout.ID.O,      Ilayout.ID.X,     Ilayout.ID.O,     Ilayout.ID.Blank},
            };

        Board board = new Board(array, 4);

        List<Ilayout> children = board.children();

        List<Ilayout> childrenTest = new LinkedList<>();

        Board boardTest = (Board) board.clone();
        boardTest.move(15);

        childrenTest.add(boardTest);

        assertTrue(listEquals(children, childrenTest));
    }

    @Test
    public void Children1Test(){

        Ilayout.ID array[][] = {
            {Ilayout.ID.X,      Ilayout.ID.X,     Ilayout.ID.X,         Ilayout.ID.O},
            {Ilayout.ID.O,      Ilayout.ID.X,     Ilayout.ID.X,         Ilayout.ID.X},
            {Ilayout.ID.O,      Ilayout.ID.O,     Ilayout.ID.Blank,     Ilayout.ID.O},
            {Ilayout.ID.O,      Ilayout.ID.X,     Ilayout.ID.Blank,     Ilayout.ID.Blank},
            };

        Board board = new Board(array, 4);

        List<Ilayout> children = board.children();

        List<Ilayout> childrenTest = new LinkedList<>();

        Board boardTest0 = (Board) board.clone();
        boardTest0.move(10);
        childrenTest.add(boardTest0);

        
        Board boardTest1 = (Board) board.clone();
        boardTest1.move(14);
        childrenTest.add(boardTest1);

        
        Board boardTest2 = (Board) board.clone();
        boardTest2.move(15);
        childrenTest.add(boardTest2);

        assertTrue(listEquals(children, childrenTest));
    }

    @Test
    public void horizontalSymetricTest(){
        Ilayout.ID array[][] = {
            {Ilayout.ID.X,      Ilayout.ID.Blank,     Ilayout.ID.Blank,},
            {Ilayout.ID.O,      Ilayout.ID.X,         Ilayout.ID.Blank,},
            {Ilayout.ID.O,      Ilayout.ID.Blank,     Ilayout.ID.X},
            };

        Ilayout.ID arrayHorizontal[][] = {
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,     Ilayout.ID.X,},
            {Ilayout.ID.Blank,      Ilayout.ID.X,         Ilayout.ID.O,},
            {Ilayout.ID.X,          Ilayout.ID.Blank,     Ilayout.ID.O},
            };

        Board board = new Board(array, 3);

        assertTrue(board.checkAllPossibleSolutions(array, arrayHorizontal));
    }

    @Test
    public void verticalSymetricTest(){
        Ilayout.ID array[][] = {
            {Ilayout.ID.X,      Ilayout.ID.Blank,     Ilayout.ID.Blank,},
            {Ilayout.ID.O,      Ilayout.ID.X,         Ilayout.ID.Blank,},
            {Ilayout.ID.O,      Ilayout.ID.Blank,     Ilayout.ID.X},
            };

        Ilayout.ID arrayVertical[][] = {
            {Ilayout.ID.O,      Ilayout.ID.Blank,     Ilayout.ID.X,},
            {Ilayout.ID.O,      Ilayout.ID.X,         Ilayout.ID.Blank,},
            {Ilayout.ID.X,      Ilayout.ID.Blank,     Ilayout.ID.Blank},
            };

        Board board = new Board(array, 3);

        assertTrue(board.checkAllPossibleSolutions(array, arrayVertical));
    }

    @Test
    public void diagonalRightSymetricTest(){
        Ilayout.ID array[][] = {
            {Ilayout.ID.X,      Ilayout.ID.Blank,     Ilayout.ID.Blank,},
            {Ilayout.ID.O,      Ilayout.ID.X,         Ilayout.ID.Blank,},
            {Ilayout.ID.O,      Ilayout.ID.Blank,     Ilayout.ID.X},
            };

        Ilayout.ID arrayDiagonal[][] = {
            {Ilayout.ID.X,          Ilayout.ID.Blank,     Ilayout.ID.Blank,},
            {Ilayout.ID.Blank,      Ilayout.ID.X,         Ilayout.ID.Blank,},
            {Ilayout.ID.O,          Ilayout.ID.O,         Ilayout.ID.X},
            };

        Board board = new Board(array, 3);

        assertTrue(board.checkAllPossibleSolutions(array, arrayDiagonal));
    }
    
    @Test
    public void diagonalLeftSymetricTest(){
        Ilayout.ID array[][] = {
            {Ilayout.ID.X,      Ilayout.ID.Blank,     Ilayout.ID.Blank,},
            {Ilayout.ID.O,      Ilayout.ID.X,         Ilayout.ID.Blank,},
            {Ilayout.ID.O,      Ilayout.ID.Blank,     Ilayout.ID.X},
            };

        Ilayout.ID arrayDiagonal[][] = {
            {Ilayout.ID.X,      Ilayout.ID.O,     Ilayout.ID.O,},
            {Ilayout.ID.Blank,  Ilayout.ID.X,     Ilayout.ID.Blank,},
            {Ilayout.ID.Blank,  Ilayout.ID.Blank, Ilayout.ID.X},
            };

        Board board = new Board(array, 3);
        
        assertTrue(board.checkAllPossibleSolutions(array, arrayDiagonal));
    }

    @Test
    public void Test(){
        Ilayout.ID array[][] = {
            {Ilayout.ID.X,          Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            };

        Ilayout.ID array2[][] = {
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.X,           Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            {Ilayout.ID.Blank,      Ilayout.ID.Blank,       Ilayout.ID.Blank,       Ilayout.ID.Blank},
            };

        Board board = new Board(array, 4);
        Board board2 = new Board(array2, 4);

        assertEquals(board.getHeuristic(board.getOpositePlayer()), board2.getHeuristic(board.getOpositePlayer()));
    }
}