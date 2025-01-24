package io.github.rainblooding.cscript.syntax;
import java.util.List;
import io.github.rainblooding.cscript.base.Token;

public abstract class Expr {

    public abstract <R> R accept(Visitor<R> visitor);


    public interface Visitor<R> {

        R visitBinaryExpr(Expr.Binary expr);
        R visitGroupingExpr(Expr.Grouping expr);
        R visitLiteralExpr(Expr.Literal expr);
        R visitUnaryExpr(Expr.Unary expr);
        R visitVariableExpr(Expr.Variable expr);
    }

    public static class Binary extends Expr {
        public final Expr left;
        public final Token operator;
        public final Expr right;

        public Binary( Expr left,  Token operator,  Expr right ) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitBinaryExpr(this);
        }


    }

    public static class Grouping extends Expr {
        public final Expr expression;

        public Grouping( Expr expression ) {
            this.expression = expression;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitGroupingExpr(this);
        }


    }

    public static class Literal extends Expr {
        public final Object value;

        public Literal( Object value ) {
            this.value = value;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitLiteralExpr(this);
        }


    }

    public static class Unary extends Expr {
        public final Token operator;
        public final Expr right;

        public Unary( Token operator,  Expr right ) {
            this.operator = operator;
            this.right = right;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitUnaryExpr(this);
        }


    }

    public static class Variable extends Expr {
        public final Token name;

        public Variable( Token name ) {
            this.name = name;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitVariableExpr(this);
        }


    }

}