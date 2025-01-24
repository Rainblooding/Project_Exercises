package io.github.rainblooding.cscript.syntax.interpreter;

import java.util.List;

public class NativeInterpreter extends CallInterpreter {


    public NativeInterpreter() {
        globals.define("clock", new CScCallable() {
            @Override
            public int arity() { return 0; }

            @Override
            public Object call(AbsInterpreter interpreter,
                               List<Object> arguments) {
                return (double)System.currentTimeMillis() / 1000.0;
            }

            @Override
            public String toString() { return "<native fn>"; }
        });
    }

}
