package io.github.rainblooding.cscript.syntax.resolver;

import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.interpreter.Interpreter;

public class GetResolver extends ClassResolver {

    public GetResolver(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public Void visitGetExpr(Expr.Get expr) {
        resolve(expr.object);
        return null;
    }
}
