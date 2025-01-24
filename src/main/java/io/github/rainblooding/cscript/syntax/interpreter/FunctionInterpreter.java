package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.base.CSFunction;

public abstract class FunctionInterpreter extends NativeInterpreter{

    @Override
    public Void visitFunctionStmt(Stmt.Function stmt) {
        CSFunction function = new CSFunction    (stmt);
        environment.define(stmt.name.lexeme, function);
        return null;
    }
}
