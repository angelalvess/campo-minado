package com.angel.cm.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    private Fields field;


    @BeforeEach
    void initField () {
        field = new Fields(3, 3);
    }


    @Test
    public void testNeighbourDistanceOne () {
        Fields neighbour = new Fields(3, 4);
        boolean result = field.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    public void testNeighbourDistanceTwo () {
        Fields neighbour = new Fields(2, 2);
        boolean result = field.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    public void testNotNeighbour () {
        Fields neighbour = new Fields(1, 1);
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
        boolean result = field.setOpen();
        assertTrue(result);
    }

    @Test
    public void openNotMinedButMarked () {
        field.toggleMarked();
        assertFalse(field.setOpen());
    }

    @Test
    public void openMinedAndMarked () {
        field.toggleMarked();
        field.undermine();
        assertFalse(field.setOpen());
    }

/*
    @Test
    public void openMinedAndNotMarked () {
        field.undermine();
        assertThrows(ExplosionException.class, () -> {
            field.setOpen();
        });
    }
*/

    @Test
    public void openNeighbours () {

        Fields field11 = new Fields(1, 1);
        Fields field22 = new Fields(2, 2);
        field22.addNeighbour(field11);
        field.addNeighbour(field22);
        field.setOpen();

        assertTrue(field11.isOpen() && field22.isOpen());
    }

    @Test
    public void openNeighboursMined () {

        Fields field11 = new Fields(1, 1);
        Fields field12 = new Fields(1, 1);
        field12.undermine();

        Fields field22 = new Fields(2, 2);
        field22.addNeighbour(field11);
        field22.addNeighbour(field12);

        field.addNeighbour(field22);
        field.setOpen();

        assertTrue(field22.isOpen() && field11.isClose());
    }



}
