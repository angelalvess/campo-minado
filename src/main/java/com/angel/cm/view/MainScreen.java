package com.angel.cm.view;

import com.angel.cm.model.Board;

import javax.swing.*;

public class MainScreen extends JFrame {


    public MainScreen () {
        Board board = new Board(30, 16, 6);
        add(new BoardPanel(board));


        setTitle("Minefield");
        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main (String[] args) {
        new MainScreen();
    }

}
