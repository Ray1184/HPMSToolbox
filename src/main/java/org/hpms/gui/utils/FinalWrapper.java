package org.hpms.gui.utils;

public class FinalWrapper <T> {

    private T value;

    public FinalWrapper(T value) {
        this.value = value;
    }

    /**
     * Getter for property 'value'.
     *
     * @return Value for property 'value'.
     */
    public T getValue() {
        return value;
    }

    /**
     * Setter for property 'value'.
     *
     * @param value Value to set for property 'value'.
     */
    public void setValue(T value) {
        this.value = value;
    }
}
