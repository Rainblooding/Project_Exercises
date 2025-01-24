package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.exception.RuntimeError;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.base.CSCallable;

import java.util.ArrayList;
import java.util.List;

public abstract class CallInterpreter extends WhileInterpreter {

    @Override
    public Object visitCallExpr(Expr.Call expr) {
        Object callee = evaluate(expr.callee);

        List<Object> arguments = new ArrayList<>();
        for (Expr argument : expr.arguments) {
            arguments.add(evaluate(argument));
        }

        if (!(callee instanceof CSCallable)) {
            throw new RuntimeError(expr.paren,
                    "Can only call functions and classes.");
        }

        CSCallable function = (CSCallable) callee;
        if (arguments.size() != function.arity()) {
            throw new RuntimeError(expr.paren, "Expected " +
                    function.arity() + " arguments but got " +
                    arguments.size() + ".");
        }
        return function.call(this, arguments);
    }
}
