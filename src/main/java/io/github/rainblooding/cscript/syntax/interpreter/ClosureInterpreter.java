package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.base.CSFunction;
import io.github.rainblooding.cscript.syntax.base.ClosureFunction;

public class ClosureInterpreter extends ReturnInterpreter {


    @Override
    public Void visitFunctionStmt(Stmt.Function stmt) {
        CSFunction function = new ClosureFunction(stmt, environment);
        environment.define(stmt.name.lexeme, function);
        return null;
    }
}
