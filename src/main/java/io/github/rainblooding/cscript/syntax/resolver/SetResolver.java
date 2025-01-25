package io.github.rainblooding.cscript.syntax.resolver;

import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.interpreter.Interpreter;

public class SetResolver extends GetResolver {

    public SetResolver(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public Void visitSetExpr(Expr.Set expr) {
        resolve(expr.value);
        resolve(expr.object);
        return null;
    }
}
