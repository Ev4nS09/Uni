package Tests;

import Game.*;


import org.junit.jupiter.api.Test;

import java.util.ArrayList.*;
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
            {Ilayout.ID.O,          Ilayout.ID.Blank,       Ilayout.ID.Blank,     Ilayout.ID.Blank},
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
    
}