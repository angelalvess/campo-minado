package com.angel.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Board {

    private int rows;
    private int columns;
    private int mines;

    private final List<Fields> fields = new ArrayList<Fields>();

    public Board (int columns, int rows, int mines) {
        this.columns = columns;
        this.rows = rows;
        this.mines = mines;

        generateFields();
        associateNeighbors();
        drawMines();
    }


    private void generateFields () {
        for (int row = 0; row < rows; row++) {
            for (int colum = 0; colum < columns; colum++) {
                fields.add(new Fields(row, colum));
            }
        }
    }

    private void associateNeighbors () {
        for (Fields c1 : fields) {
            for (Fields c2 : fields) {
                c1.addNeighbour(c2);
            }
        }
    }


    private void drawMines () {
        long armedMines = 0;
        Predicate<Fields> mined = (f) -> f.isMined();

        do {

            armedMines = fields.stream().filter(mined).count();

            int random = (int) (Math.random() * fields.size());
            fields.get(random).undermine();
        } while (armedMines < mines);
    }


    public boolean objectiveAchievedBoard () {
        return fields.stream().allMatch(c -> c.objectiveAchieved());
    }

    public void rebootGameBoard () {
        fields.forEach(c -> c.rebootGame());
        drawMines();
    }
}
