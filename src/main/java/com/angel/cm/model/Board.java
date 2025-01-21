package com.angel.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Board implements FieldObserver {

    private final int rows;
    private final int columns;
    private final int mines;

    private final List<Field> fields = new ArrayList<Field>();
    private final List<Consumer<Boolean>> observers = new ArrayList<>();


    public Board (int columns, int rows, int mines) {
        this.columns = columns;
        this.rows = rows;
        this.mines = mines;

        generateFields();
        associateNeighbors();
        drawMines();
    }


    public void forEachField( Consumer<Field> func) {
        fields.forEach(func);
    }

    public void registerObserver (Consumer<Boolean> observer) {
        observers.add(observer);
    }

    public void notifyObservers (boolean result) {
        observers.stream()
                .forEach(observer -> observer.accept(result));
    }


    private void generateFields () {
        for (int row = 0; row < rows; row++) {
            for (int colum = 0; colum < columns; colum++) {
                Field field = new Field(row, colum);
                field.registerObserver(this);
                fields.add(field);
            }
        }
    }

    private void associateNeighbors () {
        for (Field c1 : fields) {
            for (Field c2 : fields) {
                c1.addNeighbour(c2);
            }
        }
    }

    public int getRows () {
        return rows;
    }

    public int getColumns () {
        return columns;
    }

    private void drawMines () {
        long armedMines = 0;
        Predicate<Field> mined = (f) -> f.isMined();

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

    public void openBoard (int row, int colum) {
        fields.stream().filter(c -> c.getRow() == row && c.getColum() == colum).findFirst().ifPresent(c -> c.setOpenn());
    }


    public void markedBoard (int row, int colum) {
        fields.stream().filter(c -> c.getRow() == row && c.getColum() == colum).findFirst().ifPresent(c -> c.toggleMarked());
    }


    @Override
    public void eventOccurred (Field field, FieldEvent event) {
        if (event == FieldEvent.EXPLODE) {
            showMines();
            notifyObservers(false);
        } else if (objectiveAchievedBoard()) {
            notifyObservers(true);
        }
    }

    public void showMines () {
        fields.stream().filter(f -> f.isMined()).forEach(f -> f.setOpen(true));
    }
}
