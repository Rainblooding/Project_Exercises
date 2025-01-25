package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.syntax.Native.Native;

public class NativeClassInterpreter extends MethodInterpreter {

    public NativeClassInterpreter() {
        Native.init(globals);
    }
}
