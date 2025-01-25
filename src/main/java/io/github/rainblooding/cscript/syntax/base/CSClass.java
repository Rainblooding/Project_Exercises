package io.github.rainblooding.cscript.syntax.base;

public class CSClass {

    protected final String name;

    public CSClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
