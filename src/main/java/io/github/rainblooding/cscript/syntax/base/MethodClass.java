package io.github.rainblooding.cscript.syntax.base;

import io.github.rainblooding.cscript.syntax.interpreter.CallInterpreter;
import io.github.rainblooding.cscript.syntax.interpreter.ClosureInterpreter;

import java.util.List;
import java.util.Map;

public class MethodClass extends GetSetClass {

    protected final Map<String, ClosureFunction> methods;

    public MethodClass(String name, Map<String, ClosureFunction> methods) {
        super(name);
        this.methods = methods;
    }


    @Override
    public Object call(CallInterpreter interpreter, List<Object> arguments) {
        MethodInstance instance = new MethodInstance(this);
        return instance;
    }

    public ClosureFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        return null;
    }
}
