package com.angel.cm.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    private Field field;


    @BeforeEach
    void initField () {
        field = new Field(3, 3);
    }


    @Test
    public void testNeighbourDistanceOne () {
        Field neighbour = new Field(3, 4);
        boolean result = field.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    public void testNeighbourDistanceTwo () {
        Field neighbour = new Field(2, 2);
        boolean result = field.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    public void testNotNeighbour () {
        Field neighbour = new Field(1, 1);
        boolean result = field.addNeighbour(neighbour);
        assertFalse(result);
    }

    @Test
    public void testMarkedDefault () {
        assertFalse(field.isMarked());
    }

    @Test
    public void testMarkedToggle () {
        field.toggleMarked();
        assertTrue(field.isMarked());
    }

    @Test
    public void testMarkedToggleFalse () {
        field.toggleMarked();
        field.toggleMarked();
        assertFalse(field.isMarked());
    }

    @Test
    public void fieldNotMinedOrMarked () {
        boolean result = field.setOpenn();
        assertTrue(result);
    }

    @Test
    public void openNotMinedButMarked () {
        field.toggleMarked();
        assertFalse(field.setOpenn());
    }

    @Test
    public void openMinedAndMarked () {
        field.toggleMarked();
        field.undermine();
        assertFalse(field.setOpenn());
    }

/*
    @Test
    public void openMinedAndNotMarked () {
        field.undermine();
        assertThrows(ExplosionException.class, () -> {
            field.setOpenn();
        });
    }
*/

    @Test
    public void openNeighbours () {

        Field field11 = new Field(1, 1);
        Field field22 = new Field(2, 2);
        field22.addNeighbour(field11);
        field.addNeighbour(field22);
        field.setOpenn();

        assertTrue(field11.isOpen() && field22.isOpen());
    }

    @Test
    public void openNeighboursMined () {

        Field field11 = new Field(1, 1);
        Field field12 = new Field(1, 1);
        field12.undermine();

        Field field22 = new Field(2, 2);
        field22.addNeighbour(field11);
        field22.addNeighbour(field12);

        field.addNeighbour(field22);
        field.setOpenn();

        assertTrue(field22.isOpen() && field11.isClose());
    }



}
