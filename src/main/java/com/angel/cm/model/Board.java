package com.angel.cm.model;

import com.angel.cm.errors.ExplosionException;

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


            int random = (int) (Math.random() * fields.size());
            fields.get(random).undermine();
            armedMines = fields.stream().filter(mined).count();
        } while (armedMines < mines);
    }


    public boolean objectiveAchievedBoard () {
        return fields.stream().allMatch(c -> c.objectiveAchieved());
    }

    public void rebootGameBoard () {
        fields.forEach(c -> c.rebootGame());
        drawMines();
    }

    public void openBoard(int row, int colum) {
        try{
            fields.stream().filter(c -> c.getRow() == row && c.getColum() == colum).findFirst().ifPresent(c-> c.setOpen());
        } catch (ExplosionException e){
            fields.forEach(f -> f.setOpen(true));
            throw e;
        }
    }

    public void markedBoard(int row, int colum) {
        fields.stream().filter(c -> c.getRow() == row && c.getColum() == colum).findFirst().ifPresent(c-> c.toggleMarked());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        for (int r = 0; r < rows; r++){
            for (int c =0; c< columns; c++){
                sb.append(" ");
                sb.append(fields.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
