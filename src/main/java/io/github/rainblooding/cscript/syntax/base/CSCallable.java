package io.github.rainblooding.cscript.syntax.base;

import io.github.rainblooding.cscript.syntax.interpreter.AbsInterpreter;
import io.github.rainblooding.cscript.syntax.interpreter.CallInterpreter;

import java.util.List;

public interface CSCallable {

    int arity();
    Object call(CallInterpreter interpreter, List<Object> arguments);
}
