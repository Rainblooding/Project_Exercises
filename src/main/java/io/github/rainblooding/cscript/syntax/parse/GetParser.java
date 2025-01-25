package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;

import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.*;

/**
 * call           → primary ( "(" arguments? ")" | "." IDENTIFIER )* ;
 */
public class GetParser extends ClassParser {

    protected GetParser(List<Token> tokens) {
        super(tokens);
    }


    /**
     * call           → primary ( "(" arguments? ")" )* ;
     */
    protected Expr call() {
        Expr expr = primary();

        while (true) {
            if (match(LEFT_PAREN)) {
                expr = finishCall(expr);
            } else if (match(DOT)) {
                Token name = consume(IDENTIFIER,
                        "Expect property name after '.'.");
                expr = new Expr.Get(expr, name);
            } else {
                break;
            }
        }

        return expr;
    }
}
