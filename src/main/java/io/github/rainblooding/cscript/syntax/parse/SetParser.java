package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;

import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.EQUAL;

/**
 * assignment     → ( call "." )? IDENTIFIER "=" assignment
 *                | logic_or ;
 */
public class SetParser extends GetParser {

    protected SetParser(List<Token> tokens) {
        super(tokens);
    }


    /**
     *
     * assignment     → ( call "." )? IDENTIFIER "=" assignment
     *                | logic_or ;
     *
     * @return
     */
    protected Expr assignment() {
        Expr expr = equality();

        if (match(EQUAL)) {
            Token equals = previous();
            Expr value = assignment();

            if (expr instanceof Expr.Variable) {
                Token name = ((Expr.Variable)expr).name;
                return new Expr.Assign(name, value);
            } else if (expr instanceof Expr.Get) {
                Expr.Get get = (Expr.Get)expr;
                return new Expr.Set(get.object, get.name, value);
            }

            error(equals, "Invalid assignment target.");
        }

        return expr;
    }
}
