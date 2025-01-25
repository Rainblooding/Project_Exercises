package io.github.rainblooding.cscript.syntax.base;

import io.github.rainblooding.cscript.syntax.interpreter.CallInterpreter;

import java.util.List;

public class GetClass extends CSClass {

    public GetClass(String name) {
        super(name);
    }


    @Override
    public Object call(CallInterpreter interpreter, List<Object> arguments) {
        CSInstance instance = new CSInstance(this);
        return instance;
    }
}
