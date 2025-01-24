package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.Stmt;

import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.*;

/**
 * 声明变量
 *
 * program        → declaration* EOF ;
 *
 * declaration    → varDecl
 *                | statement ;
 *
 * statement      → exprStmt
 *                | printStmt ;
 *
 * varDecl        → "var" IDENTIFIER ( "=" expression )? ";" ;
 *
 */
public abstract class VarParser extends StmtParser {

    protected VarParser(List<Token> tokens) {
        super(tokens);
    }

    /**
     *
     * declaration    → varDecl
     *                | statement ;
     *
     * @return
     */
    protected Stmt declaration() {
        try {
            if (match(VAR)) return varDeclaration();

            return statement();
        } catch (ParseError error) {
            synchronize();
            return null;
        }
    }

    /**
     *
     * varDecl        → "var" IDENTIFIER ( "=" expression )? ";" ;
     *
     * @return
     */
    protected Stmt varDeclaration() {
        Token name = consume(IDENTIFIER, "Expect variable name.");

        Expr initializer = null;
        if (match(EQUAL)) {
            initializer = expression();
        }

        consume(SEMICOLON, "Expect ';' after variable declaration.");
        return new Stmt.Var(name, initializer);
    }


    /**
     * primary        → "true" | "false" | "nil"
     *                | NUMBER | STRING
     *                | "(" expression ")"
     *                | IDENTIFIER ;
     *
     * @return
     */
    @Override
    protected Expr primary() {
        if (match(FALSE)) return new Expr.Literal(false);
        if (match(TRUE)) return new Expr.Literal(true);
        if (match(NIL)) return new Expr.Literal(null);

        if (match(NUMBER, STRING)) {
            return new Expr.Literal(previous().literal);
        }
        if (match(IDENTIFIER)) {
            return new Expr.Variable(previous());
        }
        if (match(LEFT_PAREN)) {
            Expr expr = expression();
            consume(RIGHT_PAREN, "Expect ')' after expression.");
            return new Expr.Grouping(expr);
        }
        throw error(peek(), "Expect expression.");
    }
}
