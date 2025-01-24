package io.github.rainblooding.cscript.syntax.base;

import io.github.rainblooding.cscript.context.Environment;
import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.interpreter.CallInterpreter;

import java.util.List;

public class CSFunction implements CSCallable {

    private final Stmt.Function declaration;

    public CSFunction(Stmt.Function declaration) {
        this.declaration = declaration;
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public Object call(CallInterpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(interpreter.globals);
        for (int i = 0; i < declaration.params.size(); i++) {
            environment.define(declaration.params.get(i).lexeme,
                    arguments.get(i));
        }

        interpreter.executeBlock(declaration.body, environment);
        return null;
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }
}
