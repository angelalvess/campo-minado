package com.angel.cm.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FieldTest {

     private Fields field;


    @BeforeEach
    void initField() {
        field = new Fields(3,3);
    }


   @Test
    public void TestNeighbourDistanceOne () {
        Fields neighbour = new Fields(3,4);
        boolean result = field.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    public void TestNeighbourDistanceTwo () {
        Fields neighbour = new Fields(2,2);
        boolean result = field.addNeighbour(neighbour);
        assertTrue(result);
    }
}
