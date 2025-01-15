package com.angel.cm;

import com.angel.cm.model.Board;
import com.angel.cm.view.ConsoleBoard;

public class App {

    public static void main (String[] args) {

        Board board = new Board(6,6,6);

        new ConsoleBoard(board);

    }
}
