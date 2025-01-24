package io.github.rainblooding.cscript.syntax.interpreter;

import io.github.rainblooding.cscript.context.Environment;
import io.github.rainblooding.cscript.syntax.Stmt;

import java.util.List;

public class BlockInterpreter extends AssignInterpreter {


    @Override
    public Void visitBlockStmt(Stmt.Block stmt) {
        executeBlock(stmt.statements, new Environment(environment));
        return null;
    }

    private void executeBlock(List<Stmt> statements,
                      Environment environment) {
        Environment previous = this.environment;
        try {
            this.environment = environment;

            for (Stmt statement : statements) {
                execute(statement);
            }
        } finally {
            this.environment = previous;
        }
    }
}
