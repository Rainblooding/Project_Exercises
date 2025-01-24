package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.Stmt;

import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.PRINT;
import static io.github.rainblooding.cscript.base.TokenType.SEMICOLON;

/**
 *
 * program        → statement* EOF ;
 *
 * statement      → exprStmt
 *                | printStmt ;
 *
 */
public abstract class StmtParser extends ExprParser {


    protected StmtParser(List<Token> tokens) {
        super(tokens);
    }

    /**
     *
     * statement      → exprStmt
     *                | printStmt ;
     *
     * @return
     */
    protected Stmt statement() {
        if (match(PRINT)) return printStatement();

        return expressionStatement();
    }


    /**
     *
     * printStmt      → "print" expression ";" ;
     *
     * @return
     */
    protected Stmt printStatement() {
        Expr value = expression();
        consume(SEMICOLON, "Expect ';' after value.");
        return new Stmt.Print(value);
    }

    /**
     *
     * exprStmt       → expression ";" ;
     *
     * @return
     */
    protected Stmt expressionStatement() {
        Expr expr = expression();
        consume(SEMICOLON, "Expect ';' after expression.");
        return new Stmt.Expression(expr);
    }

}
