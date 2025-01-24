package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;

import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.*;

/**
 *
 * expression     → assignment ;
 * assignment     → IDENTIFIER "=" assignment
 *                | logic_or ;
 * logic_or       → logic_and ( "or" logic_and )* ;
 * logic_and      → equality ( "and" equality )* ;
 *
 */
public class LogicalParser extends BlockParser {

    protected LogicalParser(List<Token> tokens) {
        super(tokens);
    }

    /**
     *
     * assignment     → IDENTIFIER "=" assignment
     *                | logic_or ;
     *
     * @return
     */
    protected Expr assignment() {
        Expr expr = or();

        if (match(EQUAL)) {
            Token equals = previous();
            Expr value = assignment();

            if (expr instanceof Expr.Variable) {
                Token name = ((Expr.Variable)expr).name;
                return new Expr.Assign(name, value);
            }

            error(equals, "Invalid assignment target.");
        }

        return expr;
    }

    /**
     *
     * logic_or       → logic_and ( "or" logic_and )* ;
     *
     * @return
     */
    protected Expr or() {
        Expr expr = and();

        while (match(OR)) {
            Token operator = previous();
            Expr right = and();
            expr = new Expr.Logical(expr, operator, right);
        }

        return expr;
    }

    /**
     *
     * logic_and      → equality ( "and" equality )* ;
     *
     * @return
     */
    protected Expr and() {
        Expr expr = equality();

        while (match(AND)) {
            Token operator = previous();
            Expr right = equality();
            expr = new Expr.Logical(expr, operator, right);
        }

        return expr;
    }
}
