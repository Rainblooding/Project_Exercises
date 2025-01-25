package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;

import java.util.HashMap;
import java.util.Map;

public abstract class ResolverInterpreter extends ClosureInterpreter {

    protected final Map<Expr, Integer> locals = new HashMap<>();



    @Override
    public Object visitVariableExpr(Expr.Variable expr) {
        return lookUpVariable(expr.name, expr);
    }


    @Override
    public Object visitAssignExpr(Expr.Assign expr) {
        Object value = evaluate(expr.value);
        environment.assign(expr.name, value);

        Integer distance = locals.get(expr);
        if (distance != null) {
            environment.assignAt(distance, expr.name, value);
        } else {
            globals.assign(expr.name, value);
        }

        return value;
    }

    public void resolve(Expr expr, int depth) {
        locals.put(expr, depth);
    }


    private Object lookUpVariable(Token name, Expr expr) {
        Integer distance = locals.get(expr);
        if (distance != null) {
            return environment.getAt(distance, name.lexeme);
        } else {
            return globals.get(name);
        }
    }
}
