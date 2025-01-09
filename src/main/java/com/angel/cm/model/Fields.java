package com.angel.cm.model;

import com.angel.cm.errors.ExplosionException;

import java.util.ArrayList;
import java.util.List;

public class Fields {

    private final int row;
    private final int colum;

    private boolean mined = false;
    private boolean open = false;
    private boolean marked = false;

    final private List<Fields> neighbours = new ArrayList<Fields>();

    Fields (int row, int colum) {
        this.row = row;
        this.colum = colum;
    }

    boolean addNeighbour (Fields neighbour) {
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

    void toggleMarked () {
        if (!open) {
            marked = !marked;
        }
    }

    boolean setOpen () {
        if (!open && !marked) {
            open = true;

            if (mined) {
                throw new ExplosionException();
            }

            if (safeNeighbourhood()) {
                neighbours.forEach(v -> v.setOpen());
            }

            return true;
        }


        return false;
    }

    boolean safeNeighbourhood () {
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

    long countMinedNeighbours() {
        return neighbours.stream().filter(v -> v.mined).count();
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

    @Override
    public String toString () {
        if (marked) {
            return "X";
        } else if (open && mined){
            return "*";
        } else if (open && countMinedNeighbours() > 0){
            return Long.toString(countMinedNeighbours());
        } else if (open) {
            return " ";
        } else {
           return "?";
        }

    }
}
