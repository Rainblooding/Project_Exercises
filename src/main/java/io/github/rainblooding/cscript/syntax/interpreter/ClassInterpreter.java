package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.base.CSClass;

public abstract class ClassInterpreter extends ResolverInterpreter {

    @Override
    public Void visitClassStmt(Stmt.Class stmt) {
        environment.define(stmt.name.lexeme, null);
        CSClass klass = new CSClass(stmt.name.lexeme);
        environment.assign(stmt.name, klass);
        return null;
    }
}
