package io.github.rainblooding.cscript.syntax.base;

import io.github.rainblooding.cscript.syntax.interpreter.CallInterpreter;

import java.util.List;

public class CSClass implements CSCallable {

    protected final String name;

    public CSClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int arity() {
        return 0;
    }

    @Override
    public Object call(CallInterpreter interpreter, List<Object> arguments) {
        CSInstance instance = new CSInstance(this);
        return instance;
    }
}
