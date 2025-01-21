package com.angel.cm.view;

import com.angel.cm.model.Field;
import com.angel.cm.model.FieldEvent;
import com.angel.cm.model.FieldObserver;

import javax.swing.*;
import java.awt.*;

public class ButtonField extends JButton implements FieldObserver {


    private final Color BG_DEFAULT = new Color(184, 184, 184);
    private final Color BG_MARKED = new Color(8, 179, 247);
    private final Color BG_EXPLODE = new Color(189, 66, 68);
    private final Color GREEN_TEXT = new Color(0, 100, 0);

    private Field field;

    public ButtonField (Field field) {
        this.field = field;

        setBackground(BG_DEFAULT);
        setBorder(BorderFactory.createBevelBorder(0));

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

            case UNCHECK:
                applyStyleUncheck();
                break;

            case EXPLODE:
                applyStyleExplode();
                break;
            default:
                applyStyleDefault();
        }

    }


    private void applyStyleDefault () {
    }

    private void applyStyleExplode () {
    }

    private void applyStyleUncheck () {
    }

    private void applyStyleMark () {
    }

    private void applyStyleOpen () {
    }
}
