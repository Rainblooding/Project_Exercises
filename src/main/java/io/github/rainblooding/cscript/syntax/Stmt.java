package io.github.rainblooding.cscript.syntax;
import java.util.List;
import io.github.rainblooding.cscript.base.Token;

public abstract class Stmt {

    public abstract <R> R accept(Visitor<R> visitor);


    public interface Visitor<R> {

        R visitExpressionStmt(Stmt.Expression stmt);
        R visitPrintStmt(Stmt.Print stmt);
        R visitVarStmt(Stmt.Var stmt);
    }

    public static class Expression extends Stmt {
        public final Expr expression;

        public Expression( Expr expression ) {
            this.expression = expression;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitExpressionStmt(this);
        }


    }

    public static class Print extends Stmt {
        public final Expr expression;

        public Print( Expr expression ) {
            this.expression = expression;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitPrintStmt(this);
        }


    }

    public static class Var extends Stmt {
        final Token name;
        final Expr initializer;

        public Var( Token name,  Expr initializer ) {
            this.name = name;
            this.initializer = initializer;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitVarStmt(this);
        }


    }

}