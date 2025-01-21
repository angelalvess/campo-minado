package com.angel.cm.view;

import com.angel.cm.model.Board;

public class Main {

    public static void main (String[] args) {

        Board board = new Board(3,3,9);

        board.registerObserver(e -> {
            if (e) {
                System.out.println("You win");
            } else {
                System.out.println("you loose");
            }
        } );

        board.openBoard(1,1);
    }
}
