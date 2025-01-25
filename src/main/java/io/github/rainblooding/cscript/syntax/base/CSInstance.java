package io.github.rainblooding.cscript.syntax.base;

public class CSInstance {

    protected CSClass klass;

    public CSInstance(CSClass klass) {
        this.klass = klass;
    }

    @Override
    public String toString() {
        return klass.name + " instance";
    }
}
