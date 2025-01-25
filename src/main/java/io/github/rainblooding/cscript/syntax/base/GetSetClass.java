package io.github.rainblooding.cscript.syntax.base;

import io.github.rainblooding.cscript.syntax.interpreter.CallInterpreter;

import java.util.List;

public class GetSetClass extends CSClass {

    public GetSetClass(String name) {
        super(name);
    }


    @Override
    public Object call(CallInterpreter interpreter, List<Object> arguments) {
        GetSetInstance instance = new GetSetInstance(this);
        return instance;
    }
}
