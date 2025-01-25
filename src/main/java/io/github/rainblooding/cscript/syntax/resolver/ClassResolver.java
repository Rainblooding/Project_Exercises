package io.github.rainblooding.cscript.syntax.resolver;

import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.interpreter.Interpreter;

public abstract class ClassResolver extends AbsResolver{

    public ClassResolver(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public Void visitClassStmt(Stmt.Class stmt) {
        declare(stmt.name);
        define(stmt.name);
        return null;
    }
}
