package io.github.rainblooding.cscript.syntax.base;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.exception.RuntimeError;
import io.github.rainblooding.cscript.syntax.interpreter.ClosureInterpreter;

public class MethodInstance extends GetSetInstance {

    public MethodInstance(MethodClass klass) {
        super(klass);
    }

    @Override
    public Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }
        ClosureFunction method = ((MethodClass) klass).findMethod(name.lexeme);
        if (method != null) return method;
        throw new RuntimeError(name,
                "Undefined property '" + name.lexeme + "'.");
    }
}
