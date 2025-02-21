package io.github.rainblooding.cscript.syntax;
import java.util.List;
import io.github.rainblooding.cscript.base.Token;

public abstract class Expr {

    public abstract <R> R accept(Visitor<R> visitor);


    public interface Visitor<R> {

        R visitAssignExpr(Expr.Assign expr);
        R visitBinaryExpr(Expr.Binary expr);
        R visitCallExpr(Expr.Call expr);
        R visitGetExpr(Expr.Get expr);
        R visitGroupingExpr(Expr.Grouping expr);
        R visitLiteralExpr(Expr.Literal expr);
        R visitLogicalExpr(Expr.Logical expr);
        R visitSetExpr(Expr.Set expr);
        R visitUnaryExpr(Expr.Unary expr);
        R visitVariableExpr(Expr.Variable expr);
    }

    public static class Assign extends Expr {
        public final Token name;
        public final Expr value;

        public Assign( Token name,  Expr value ) {
            this.name = name;
            this.value = value;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitAssignExpr(this);
        }


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

    public static class Call extends Expr {
        public final Expr callee;
        public final Token paren;
        public final List<Expr> arguments;

        public Call( Expr callee,  Token paren,  List<Expr> arguments ) {
            this.callee = callee;
            this.paren = paren;
            this.arguments = arguments;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitCallExpr(this);
        }


    }

    public static class Get extends Expr {
        public final Expr object;
        public final Token name;

        public Get( Expr object,  Token name ) {
            this.object = object;
            this.name = name;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitGetExpr(this);
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

    public static class Logical extends Expr {
        public final Expr left;
        public final Token operator;
        public final Expr right;

        public Logical( Expr left,  Token operator,  Expr right ) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitLogicalExpr(this);
        }


    }

    public static class Set extends Expr {
        public final Expr object;
        public final Token name;
        public final Expr value;

        public Set( Expr object,  Token name,  Expr value ) {
            this.object = object;
            this.name = name;
            this.value = value;
        }

        @Override
        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitSetExpr(this);
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