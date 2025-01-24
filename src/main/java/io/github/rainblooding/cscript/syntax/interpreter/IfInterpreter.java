package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.syntax.Stmt;

public abstract class IfInterpreter extends LogicalInterpreter {

    @Override
    public Void visitIfStmt(Stmt.If stmt) {
        if (isTruthy(evaluate(stmt.condition))) {
            execute(stmt.thenBranch);
        } else if (stmt.elseBranch != null) {
            execute(stmt.elseBranch);
        }
        return null;
    }
}
