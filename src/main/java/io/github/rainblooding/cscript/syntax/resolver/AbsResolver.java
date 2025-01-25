package io.github.rainblooding.cscript.syntax.resolver;

import io.github.rainblooding.cscript.CScript;
import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.interpreter.Interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class AbsResolver implements Expr.Visitor <Void>, Stmt.Visitor <Void> {


    protected final Interpreter interpreter;
    protected final Stack<Map<String, Boolean>> scopes = new Stack<>();

    protected FunctionType currentFunction = FunctionType.NONE;

    protected enum FunctionType {
        NONE,
        FUNCTION
    }

    public AbsResolver(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public void resolve(List<Stmt> statements) {
        for (Stmt statement : statements) {
            resolve(statement);
        }
    }

    protected void resolve(Stmt stmt) {
        stmt.accept(this);
    }

    protected void resolve(Expr expr) {
        expr.accept(this);
    }

    protected void beginScope() {
        scopes.push(new HashMap<String, Boolean>());
    }

    protected void endScope() {
        scopes.pop();
    }

    protected void declare(Token name) {
        if (scopes.isEmpty()) return;

        Map<String, Boolean> scope = scopes.peek();

        if (scope.containsKey(name.lexeme)) {
            CScript.error(name,
                    "Already a variable with this name in this scope.");
        }

        scope.put(name.lexeme, false);
    }

    protected void define(Token name) {
        if (scopes.isEmpty()) return;
        scopes.peek().put(name.lexeme, true);
    }

    protected void resolveLocal(Expr expr, Token name) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(name.lexeme)) {
                interpreter.resolve(expr, scopes.size() - 1 - i);
                return;
            }
        }
    }

    protected void resolveFunction(Stmt.Function function, FunctionType type) {
        FunctionType enclosingFunction = currentFunction;
        currentFunction = type;

        beginScope();
        for (Token param : function.params) {
            declare(param);
            define(param);
        }
        resolve(function.body);
        endScope();
        currentFunction = enclosingFunction;
    }
}
