package com.angel.cm.view;

import com.angel.cm.model.Field;
import com.angel.cm.model.FieldEvent;
import com.angel.cm.model.FieldObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonField extends JButton implements FieldObserver, MouseListener {


    private final Color BG_DEFAULT = new Color(184, 184, 184);
    private final Color BG_MARKED = new Color(8, 179, 247);
    private final Color BG_EXPLODE = new Color(189, 66, 68);
    private final Color GREEN_TEXT = new Color(0, 100, 0);

    private Field field;

    public ButtonField (Field field) {
        this.field = field;

        setBackground(BG_DEFAULT);
        setBorder(BorderFactory.createBevelBorder(0));

        addMouseListener(this);
        field.registerObserver(this);
    }

    @Override
    public void eventOccurred (Field field, FieldEvent event) {
        switch (event) {
            case OPEN:
                applyStyleOpen();
                break;

            case MARK:
                applyStyleMark();
                break;

            case EXPLODE:
                applyStyleExplode();
                break;
            default:
                applyStyleDefault();
        }

    }


    private void applyStyleDefault () {
        setBackground(BG_DEFAULT);
        setBorder(BorderFactory.createBevelBorder(0));
        setText("");
    }

    private void applyStyleExplode () {
        setBackground(BG_EXPLODE);
        setText("X");
    }


    private void applyStyleMark () {
        setBackground(BG_EXPLODE);
        setText("O");

    }

    private void applyStyleOpen () {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        if (field.isMined()) {
            setBackground(BG_EXPLODE);
            return;
        }

        setBackground(BG_DEFAULT);

        switch (field.countMinedNeighbours()) {
            case 1:
                setForeground(GREEN_TEXT);
                break;
            case 2:
                setForeground(Color.YELLOW);
                break;
            case 3:
                setForeground(Color.BLUE);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                setForeground(Color.red);
                break;
            default:
                setForeground(Color.PINK);
        }

        String value = !field.safeNeighbourhood() ? field.countMinedNeighbours() + "" : "";
        setText(value);
    }

    @Override
    public void mouseClicked (MouseEvent e) {
        if (e.getButton() == 1) {
            field.setOpenn();
        } else {
            field.toggleMarked();
        }
    }

    public void mousePressed (MouseEvent e) {
    }

    public void mouseReleased (MouseEvent e) {
    }

    public void mouseEntered (MouseEvent e) {
    }

    public void mouseExited (MouseEvent e) {
    }
}
