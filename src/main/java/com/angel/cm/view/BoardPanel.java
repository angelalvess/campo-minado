package com.angel.cm.view;

import com.angel.cm.model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    public BoardPanel (Board board) {

    setLayout(new GridLayout(board.getRows(), board.getColumns()));


        board.forEachField(field -> add(new ButtonField(field)));

            board.registerObserver(e -> {
                SwingUtilities.invokeLater(() -> {
                if (e) {
                    JOptionPane.showMessageDialog(this,"Win");
                } else {
                    JOptionPane.showMessageDialog(this,"Loose");
                }

                board.rebootGameBoard();
            });
        });

    }
}
