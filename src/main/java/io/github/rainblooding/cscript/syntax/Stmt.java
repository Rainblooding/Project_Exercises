package io.github.rainblooding.cscript.syntax;
import java.util.List;
import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;

public abstract class Stmt {

    abstract <R> R accept(Visitor<R> visitor);


    interface Visitor<R> {

        R visitExpressionStmt(Stmt.Expression stmt);
        R visitPrintStmt(Stmt.Print stmt);
    }

    static class Expression extends Stmt {
        final Expr expression;

        public Expression( Expr expression ) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitExpressionStmt(this);
        }


    }

    static class Print extends Stmt {
        final Expr expression;

        public Print( Expr expression ) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPrintStmt(this);
        }


    }

}


