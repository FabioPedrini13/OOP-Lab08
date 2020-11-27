package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private String nextString;
    private final List<String> history;

    public ControllerImpl() {
        this.history = new ArrayList<>();
    }

    @Override
    public void setNext(final String s) {
        if (s != null) {
            this.nextString = s;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getNext() {
        return this.nextString;
    }

    @Override
    public List<String> getHistory() {
        return this.history;
    }

    @Override
    public void printString() {
        if (this.nextString != null) {
            System.out.println(this.nextString);
            this.history.add(this.nextString);
        } else {
            throw new IllegalStateException();
        }
    }

}
