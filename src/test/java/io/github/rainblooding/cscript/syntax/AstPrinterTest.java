package io.github.rainblooding.cscript.syntax;


import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.base.TokenType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AstPrinterTest {

    private AstPrinter printer;

    @BeforeEach
    public void setUp() {
        printer = new AstPrinter();
    }

    @Test
    public void print_BinaryExpression_ReturnsCorrectString() {
        Expr expression = new Expr.Binary(
                new Expr.Literal(1),
                new Token(TokenType.PLUS, "+", null, 1),
                new Expr.Literal(2)
        );
        String result = printer.print(expression);
        assertEquals("(+ 1 2)", result);
    }

    @Test
    public void print_GroupingExpression_ReturnsCorrectString() {
        Expr expression = new Expr.Grouping(
                new Expr.Literal(3)
        );
        String result = printer.print(expression);
        assertEquals("(group 3)", result);
    }

    @Test
    public void print_LiteralExpression_ReturnsCorrectString() {
        Expr expression = new Expr.Literal(4);
        String result = printer.print(expression);
        assertEquals("4", result);
    }

    @Test
    public void print_UnaryExpression_ReturnsCorrectString() {
        Expr expression = new Expr.Unary(
                new Token(TokenType.MINUS, "-", null, 1),
                new Expr.Literal(5)
        );
        String result = printer.print(expression);
        assertEquals("(- 5)", result);
    }
}
