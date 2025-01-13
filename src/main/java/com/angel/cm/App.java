package com.angel.cm;

import com.angel.cm.model.Board;

public class App {

    public static void main (String[] args) {
        Board board = new Board(6,6,6);

        board.openBoard(4,3); // abrindo campos
        board.markedBoard(4,4); // marcando campos
        board.markedBoard(4,5); // marcando campos
        System.out.println(board);
    }
}
