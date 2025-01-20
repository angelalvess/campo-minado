package com.angel.cm.model;

@FunctionalInterface
public interface FieldObserver {

    public void eventOccurred(Fields field, FieldEvent event);

}
