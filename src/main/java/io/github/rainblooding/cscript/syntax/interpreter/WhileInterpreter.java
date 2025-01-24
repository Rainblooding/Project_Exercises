package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.syntax.Stmt;

public abstract class WhileInterpreter extends IfInterpreter{

    @Override
    public Void visitWhileStmt(Stmt.While stmt) {
        while (isTruthy(evaluate(stmt.condition))) {
            execute(stmt.body);
        }
        return null;
    }
}
