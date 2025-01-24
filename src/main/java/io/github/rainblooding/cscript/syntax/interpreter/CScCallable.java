package io.github.rainblooding.cscript.syntax.interpreter;

import java.util.List;

public interface CScCallable {

    int arity();
    Object call(AbsInterpreter interpreter, List<Object> arguments);
}
