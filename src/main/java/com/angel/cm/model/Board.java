package com.angel.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Board implements FieldObserver {

    private int rows;
    private int columns;
    private int mines;

    private final List<Fields> fields = new ArrayList<Fields>();
    private final List<Consumer<Boolean>> observers = new ArrayList<>();


    public void registerObserver(Consumer<Boolean> observer) {
        observers.add(observer);
    }

    public void notifyObservers (boolean result) {
        observers.stream()
                .forEach(observer -> observer.accept(result));
    }

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
                Fields field = new Fields(row, colum);
                field.registerObserver(this);
                fields.add(field);
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

            fields.stream().filter(c -> c.getRow() == row && c.getColum() == colum).findFirst().ifPresent(c-> c.setOpen());
    }



    public void markedBoard(int row, int colum) {
        fields.stream().filter(c -> c.getRow() == row && c.getColum() == colum).findFirst().ifPresent(c-> c.toggleMarked());
    }


    @Override
    public void eventOccurred (Fields field, FieldEvent event) {
    if (event == FieldEvent.EXPLODE) {
        showMines();
        notifyObservers(false);
    } else if (objectiveAchievedBoard()) {

        notifyObservers(true);
    }
    }

    public void showMines() {
        fields.stream().filter(f -> f.isMined()).forEach(f-> f.setOpen(true));
    }
}
