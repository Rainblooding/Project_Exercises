package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.base.TokenType;
import io.github.rainblooding.cscript.syntax.Expr;

public abstract class LogicalInterpreter extends BlockInterpreter {

    @Override
    public Object visitLogicalExpr(Expr.Logical expr) {
        Object left = evaluate(expr.left);

        if (expr.operator.type == TokenType.OR) {
            if (isTruthy(left)) return left;
        } else {
            if (!isTruthy(left)) return left;
        }

        return evaluate(expr.right);
    }
}
