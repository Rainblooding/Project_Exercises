package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.context.Environment;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.Stmt;

public abstract class VarInterpreter extends StmtInterpreter {

    protected final Environment globals = new Environment();
    protected Environment environment = globals;

    @Override
    public Void visitVarStmt(Stmt.Var stmt) {
        Object value = null;
        if (stmt.initializer != null) {
            value = evaluate(stmt.initializer);
        }

        environment.define(stmt.name.lexeme, value);
        return null;
    }


    @Override
    public Object visitVariableExpr(Expr.Variable expr) {
        return environment.get(expr.name);

    }
}
