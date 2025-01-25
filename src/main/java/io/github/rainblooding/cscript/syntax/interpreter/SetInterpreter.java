package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.exception.RuntimeError;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.base.GetSetInstance;

public class SetInterpreter extends GetInterpreter {

    @Override
    public Object visitSetExpr(Expr.Set expr) {
        Object object = evaluate(expr.object);

        if (!(object instanceof GetSetInstance)) {
            throw new RuntimeError(expr.name,
                    "Only instances have fields.");
        }

        Object value = evaluate(expr.value);
        ((GetSetInstance)object).set(expr.name, value);
        return value;
    }
}
