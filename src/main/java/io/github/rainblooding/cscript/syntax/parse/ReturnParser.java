package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.Stmt;

import java.util.List;

/**
 * statement      → exprStmt
 *                | forStmt
 *                | ifStmt
 *                | printStmt
 *                | returnStmt
 *                | whileStmt
 *                | block ;
 *
 * returnStmt     → "return" expression? ";" ;
 */
import static io.github.rainblooding.cscript.base.TokenType.*;

public class ReturnParser extends FunctionParser {

    protected ReturnParser(List<Token> tokens) {
        super(tokens);
    }


    /**
     * statement      → exprStmt
     *                | forStmt
     *                | ifStmt
     *                | printStmt
     *                | returnStmt
     *                | whileStmt
     *                | block ;
     */
    @Override
    protected Stmt statement() {
        if (match(FOR)) return forStatement();
        if (match(IF)) return ifStatement();
        if (match(PRINT)) return printStatement();
        if (match(RETURN)) return returnStatement();
        if (match(WHILE)) return whileStatement();
        if (match(LEFT_BRACE)) return new Stmt.Block(block());
        return expressionStatement();
    }

    /**
     *
     * returnStmt     → "return" expression? ";" ;
     */
    protected Stmt returnStatement() {
        Token keyword = previous();
        Expr value = null;
        if (!check(SEMICOLON)) {
            value = expression();
        }

        consume(SEMICOLON, "Expect ';' after return value.");
        return new Stmt.Return(keyword, value);
    }
}
