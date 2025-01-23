package com.angel.cm.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private final int row;
    private final int colum;

    private boolean mined = false;
    private boolean open = false;
    private boolean marked = false;

    private final List<Field> neighbours = new ArrayList<Field>();
    private final List<FieldObserver> observers = new ArrayList<>();


    Field (int row, int colum) {
        this.row = row;
        this.colum = colum;
    }


    public void registerObserver (FieldObserver observer) {
        observers.add(observer);
    }

    private void notifyEventForObservers (FieldEvent event) {
        observers.stream().forEach(observer -> observer.eventOccurred(this, event));
    }

    boolean addNeighbour (Field neighbour) {
        boolean differentRow = this.row != neighbour.row;
        boolean differentColum = this.colum != neighbour.colum;
        boolean diagonal = differentRow && differentColum;

        int deltaRow = Math.abs(this.row - neighbour.row);
        int deltaColum = Math.abs(this.colum - neighbour.colum);
        int deltaGeneral = deltaColum + deltaRow;

        if (deltaGeneral == 1 && !diagonal) {
            neighbours.add(neighbour);
            return true;

        } else if (deltaGeneral == 2 && diagonal) {
            neighbours.add(neighbour);
            return true;

        } else {
            return false;
        }

    }

    public void toggleMarked () {
        if (!open) {
            marked = !marked;
        }

        if (marked) {
            notifyEventForObservers(FieldEvent.MARK);
        } else {
            notifyEventForObservers(FieldEvent.UNCHECK);
        }
    }

    public boolean setOpenn () {
        if (!open && !marked) {

            if (mined) {
                notifyEventForObservers(FieldEvent.EXPLODE);
                return true;
            }

            setOpen(true);

            if (safeNeighbourhood()) {
                neighbours.forEach(Field::setOpenn);
            }

            return true;
        }


        return false;
    }

    public boolean safeNeighbourhood () {
        return neighbours.stream().noneMatch(v -> v.mined);
    }

    void undermine () {
        if (!mined) {
            mined = true;
        }
    }

    public boolean isMarked () {
        return marked;
    }

    public boolean isMined () {
        return mined;
    }

    public boolean isOpen () {
        return open;
    }

    public boolean isClose () {
        return !isOpen();
    }

    public boolean objectiveAchieved () {
        boolean unraveledField = !mined && open;
        boolean protectedField = mined && marked;

        return unraveledField || protectedField;
    }

    public int countMinedNeighbours () {
        return (int) neighbours.stream().filter(v -> v.mined).count();
    }

    void rebootGame () {
        open = false;
        mined = false;
        marked = false;
    }

    public int getColum () {
        return colum;
    }

    public int getRow () {
        return row;
    }

    void setOpen (boolean open) {
        this.open = open;

        if (open) {
            notifyEventForObservers(FieldEvent.OPEN);
        }
    }


}
