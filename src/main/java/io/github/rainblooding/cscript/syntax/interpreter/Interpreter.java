package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.CScript;
import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.context.Environment;
import io.github.rainblooding.cscript.exception.RuntimeError;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.Stmt;

import java.util.List;

public class Interpreter extends SetInterpreter {

    public Interpreter() {
        super();
    }

    public void interpret(List<Stmt> statements) {
        try {
            for (Stmt statement : statements) {
                execute(statement);
            }
        } catch (RuntimeError error) {
            CScript.runtimeError(error);
        }
    }
}
