package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.exception.RuntimeError;
import io.github.rainblooding.cscript.syntax.Expr;

import java.util.ArrayList;
import java.util.List;

public class CallInterpreter extends WhileInterpreter {

    @Override
    public Object visitCallExpr(Expr.Call expr) {
        Object callee = evaluate(expr.callee);

        List<Object> arguments = new ArrayList<>();
        for (Expr argument : expr.arguments) {
            arguments.add(evaluate(argument));
        }

        if (!(callee instanceof CScCallable)) {
            throw new RuntimeError(expr.paren,
                    "Can only call functions and classes.");
        }

        CScCallable function = (CScCallable) callee;
        if (arguments.size() != function.arity()) {
            throw new RuntimeError(expr.paren, "Expected " +
                    function.arity() + " arguments but got " +
                    arguments.size() + ".");
        }
        return function.call(this, arguments);
    }
}
