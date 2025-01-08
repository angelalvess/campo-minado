package com.angel.cm.model;

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
        boolean diferentRow = this.row != neighbour.row;
        boolean diferentColum = this.colum != neighbour.colum;
        boolean diagonal = diferentRow && diferentColum;

        int deltaRow = Math.abs(this.row - neighbour.row);
        int deltaColum = Math.abs(this.colum - neighbour.colum);
        int deltaGeneral = deltaColum + deltaRow;

        if (deltaGeneral == 1 && !diagonal){
            neighbours.add(neighbour);
            return true;
        }else if (deltaGeneral == 2 && diagonal){
            neighbours.add(neighbour);
            return true;
        } else {
            return  false;
        }


    }
}
