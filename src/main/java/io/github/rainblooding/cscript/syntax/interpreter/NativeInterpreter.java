package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.syntax.base.CSCallable;

import java.util.List;

public abstract class NativeInterpreter extends CallInterpreter {


    public NativeInterpreter() {
        globals.define("clock", new CSCallable() {
            @Override
            public int arity() { return 0; }

            @Override
            public Object call(CallInterpreter interpreter,
                               List<Object> arguments) {
                return (double)System.currentTimeMillis() / 1000.0;
            }

            @Override
            public String toString() { return "<native fn>"; }
        });
    }

}
