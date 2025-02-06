package io.github.rainblooding.cscript.syntax;


import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.base.TokenType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExprTest {

    private Expr.Visitor<String> visitor;

    @BeforeEach
    public void setUp() {
        visitor = mock(Expr.Visitor.class);
    }

    @Test
    public void accept_VisitorCalled_CorrectMethodInvoked() {
        // Arrange
        Token operator = new Token(TokenType.MINUS, "-", null, 1);
        Expr right = new Expr.Literal(1);
        Expr.Unary unary = new Expr.Unary(operator, right);

        when(visitor.visitUnaryExpr(unary)).thenReturn("visited");

        // Act
        String result = unary.accept(visitor);

        // Assert
        assertEquals("visited", result);
        Mockito.verify(visitor).visitUnaryExpr(unary);
    }
}
