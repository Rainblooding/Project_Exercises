package io.github.rainblooding.cscript.syntax.resolver;

import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.interpreter.Interpreter;

public class MethodResolver extends SetResolver {


    public MethodResolver(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public Void visitClassStmt(Stmt.Class stmt) {
        declare(stmt.name);
        define(stmt.name);

        for (Stmt.Function method : stmt.methods) {
            FunctionType declaration = FunctionType.METHOD;
            resolveFunction(method, declaration);
        }
        return null;
    }
}
