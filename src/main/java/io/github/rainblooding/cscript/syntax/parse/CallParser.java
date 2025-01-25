package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.base.TokenType;
import io.github.rainblooding.cscript.syntax.Expr;

import java.util.ArrayList;
import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.*;

/**
 * unary          → ( "!" | "-" ) unary | call ;
 * call           → primary ( "(" arguments? ")" )* ;
 */
public class CallParser extends ForParser {

    protected CallParser(List<Token> tokens) {
        super(tokens);
    }

    /**
     * unary          → ( "!" | "-" ) unary | call ;
     * @return
     */
    protected Expr unary() {
        if (match(TokenType.BANG, TokenType.MINUS)) {
            Token operator = previous();
            Expr right = unary();
            return new Expr.Unary(operator, right);
        }

        return call();
    }


    /**
         * call           → primary ( "(" arguments? ")" )* ;
     */
    protected Expr call() {
        Expr expr = primary();

        while (true) {
            if (match(LEFT_PAREN)) {
                expr = finishCall(expr);
            } else {
                break;
            }
        }

        return expr;
    }

    protected Expr finishCall(Expr callee) {
        List<Expr> arguments = new ArrayList<>();
        if (!check(RIGHT_PAREN)) {
            do {
                if (arguments.size() >= 255) {
                    error(peek(), "Can't have more than 255 arguments.");
                }
                arguments.add(expression());
            } while (match(COMMA));
        }

        Token paren = consume(RIGHT_PAREN,
                "Expect ')' after arguments.");

        return new Expr.Call(callee, paren, arguments);
    }
}
