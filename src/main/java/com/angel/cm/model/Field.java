package com.angel.cm.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private final int row;
    private final int colum;

    private boolean mined = false;
    private boolean open = false;
    private boolean marked = false;

    final private List<Field> neighours = new ArrayList<Field>();

    Field (int row, int colum) {
        this.row = row;
        this.colum = colum;
    }

    void addNeighour (Field neighour) {
    }
}
