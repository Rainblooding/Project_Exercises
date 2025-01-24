package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.base.Return;

public class ReturnInterpreter extends FunctionInterpreter {

    @Override
    public Void visitReturnStmt(Stmt.Return stmt) {
        Object value = null;
        if (stmt.value != null) value = evaluate(stmt.value);

        throw new Return(value);
    }
}
