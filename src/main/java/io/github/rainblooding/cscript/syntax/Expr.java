package io.github.rainblooding.cscript.syntax;
import io.github.rainblooding.cscript.base.Token;

public abstract class Expr {

    abstract <R> R accept(Visitor<R> visitor);


    public interface Visitor<R> {

        R visitBinaryExpr(Expr.Binary expr);
        R visitGroupingExpr(Expr.Grouping expr);
        R visitLiteralExpr(Expr.Literal expr);
        R visitUnaryExpr(Expr.Unary expr);
    }

    /**
     * Binary 类继承自 Expr，用于表示二元表达式。
     * 该类封装了表达式树中的二元操作或表达式。
     */
    public static class Binary extends Expr {
        final Expr left;
        final Token operator;
        final Expr right;

        public Binary( Expr left,  Token operator,  Expr right ) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBinaryExpr(this);
        }


    }

    /**
     * Grouping 类表示语法中的一个分组表达式，继承自 Expr 类。
     * 分组表达式是一种语法结构，用于将其他表达式组合在一起，
     * 通常会影响表达式的求值优先级或作用域。
     */
    public static class Grouping extends Expr {
        final Expr expression;

        public Grouping( Expr expression ) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitGroupingExpr(this);
        }


    }

    /**
     * Literal 类表示抽象语法树中的字面量表达式。
     * 该类继承自 Expr 类，根据需要实现或重写其属性和方法。
     */
    public static class Literal extends Expr {
        final Object value;

        public Literal( Object value ) {
            this.value = value;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitLiteralExpr(this);
        }


    }

    /**
     * Unary 类表示表达式树中的一元表达式类型。
     * 一元表达式包括诸如取反、自增、自减、逻辑非等操作，这些操作仅涉及一个操作数。
     * 该类继承自 Expr 类，用于定义和处理一元表达式。
     */
    public static class Unary extends Expr {
        final Token operator;
        final Expr right;

        public Unary( Token operator,  Expr right ) {
            this.operator = operator;
            this.right = right;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitUnaryExpr(this);
        }


    }

}


