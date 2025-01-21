package com.angel.cm.view;

import com.angel.cm.model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    public BoardPanel (Board board) {

    setLayout(new GridLayout(board.getRows(), board.getColumns()));

    int total = board.getRows() * board.getColumns();

        board.forEachField(field -> add(new ButtonField(field)));

    }
}
