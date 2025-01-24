package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.Stmt;

import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.*;

/**
 * statement      → exprStmt
 *                | ifStmt
 *                | printStmt
 *                | whileStmt
 *                | block ;
 *
 * whileStmt      → "while" "(" expression ")" statement ;
 *
 */
public class WhileParser extends IfParser {

    protected WhileParser(List<Token> tokens) {
        super(tokens);
    }

    /**
     * statement      → exprStmt
     *                | ifStmt
     *                | printStmt
     *                | whileStmt
     *                | block ;
     *
     * @return
     */
    @Override
    protected Stmt statement() {
        if (match(IF)) return ifStatement();
        if (match(PRINT)) return printStatement();
        if (match(WHILE)) return whileStatement();
        if (match(LEFT_BRACE)) return new Stmt.Block(block());
        return expressionStatement();
    }

    /**
     *
     * whileStmt      → "while" "(" expression ")" statement ;
     *
     * @return
     */
    protected Stmt whileStatement() {
        consume(LEFT_PAREN, "Expect '(' after 'while'.");
        Expr condition = expression();
        consume(RIGHT_PAREN, "Expect ')' after condition.");
        Stmt body = statement();

        return new Stmt.While(condition, body);
    }
}
