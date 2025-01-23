package io.github.rainblooding.regex;

import java.util.ArrayList;
import java.util.List;

public class State {
    static int counter = 0;
    int id;
    List<State> nexts = new ArrayList<>();
    char edge = 'ε';
    boolean visited = false;

    State() {
        id = counter++;
    }

    @Override
    public String toString() {
        return "id: " + id + ", edge: " + edge;
    }
}