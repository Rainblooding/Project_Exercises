package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;

import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.*;

public abstract class AssignParser extends VarParser {

    protected AssignParser(List<Token> tokens) {
        super(tokens);
    }

    protected Expr expression() {
        return assignment();
    }

    protected Expr assignment() {
        Expr expr = equality();

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
}
