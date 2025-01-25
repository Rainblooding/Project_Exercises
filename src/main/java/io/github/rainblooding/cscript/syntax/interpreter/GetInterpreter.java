package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.exception.RuntimeError;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.base.GetSetClass;
import io.github.rainblooding.cscript.syntax.base.GetSetInstance;

public abstract class GetInterpreter extends ClassInterpreter {



    @Override
    public Void visitClassStmt(Stmt.Class stmt) {
        environment.define(stmt.name.lexeme, null);
        GetSetClass klass = new GetSetClass(stmt.name.lexeme);
        environment.assign(stmt.name, klass);
        return null;
    }

    @Override
    public Object visitGetExpr(Expr.Get expr) {
        Object object = evaluate(expr.object);
        if (object instanceof GetSetInstance) {
            return ((GetSetInstance) object).get(expr.name);
        }

        throw new RuntimeError(expr.name,
                "Only instances have properties.");
    }
}
