package io.github.rainblooding.cscript.syntax.base;

import io.github.rainblooding.cscript.context.Environment;
import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.interpreter.CallInterpreter;

import java.util.List;

public class ClosureFunction extends CSFunction {

    private final Environment closure;

    public ClosureFunction(Stmt.Function declaration, Environment closure) {
        super(declaration);
        this.closure = closure;
    }

    @Override
    public Object call(CallInterpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(closure);
        for (int i = 0; i < declaration.params.size(); i++) {
            environment.define(declaration.params.get(i).lexeme,
                    arguments.get(i));
        }
        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue) {
            return returnValue.value;
        }

        return null;
    }
}
