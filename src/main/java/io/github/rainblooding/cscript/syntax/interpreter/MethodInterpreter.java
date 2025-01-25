package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.base.ClosureFunction;
import io.github.rainblooding.cscript.syntax.base.MethodClass;

import java.util.HashMap;
import java.util.Map;

public class MethodInterpreter extends SetInterpreter {


    @Override
    public Void visitClassStmt(Stmt.Class stmt) {
        environment.define(stmt.name.lexeme, null);

        Map<String, ClosureFunction> methods = new HashMap<>();
        for (Stmt.Function method : stmt.methods) {
            ClosureFunction function = new ClosureFunction(method, environment);
            methods.put(method.name.lexeme, function);
        }

        MethodClass klass = new MethodClass(stmt.name.lexeme, methods);
        environment.assign(stmt.name, klass);
        return null;
    }

}
