package com.angel.cm.view;

import com.angel.cm.model.Board;

import javax.swing.*;

public class MainScreen extends JFrame {


    public MainScreen () {
        Board board = new Board(16, 30, 50);
        add(new BoardPanel(board));


        setTitle("campo minado");
        setVisible(true);
        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main (String[] args) {
        new MainScreen();
    }

}
