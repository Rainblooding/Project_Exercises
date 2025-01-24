package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.Stmt;

import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.*;

/**
 * if
 *
 * statement      → exprStmt
 *                | ifStmt
 *                | printStmt
 *                | block ;
 *
 * ifStmt         → "if" "(" expression ")" statement
 *                ( "else" statement )? ;
 *
 */
public class IfParser extends LogicalParser {

    protected IfParser(List<Token> tokens) {
        super(tokens);
    }

    /**
     *
     * statement      → exprStmt
     *                | ifStmt
     *                | printStmt
     *                | block ;
     *
     * @return
     */
    @Override
    protected Stmt statement() {
        if (match(IF)) return ifStatement();
        if (match(PRINT)) return printStatement();
        if (match(LEFT_BRACE)) return new Stmt.Block(block());
        return expressionStatement();
    }

    /**
     *
     * ifStmt         → "if" "(" expression ")" statement
     *                ( "else" statement )? ;
     *
     * @return
     */
    protected Stmt ifStatement() {
        consume(LEFT_PAREN, "Expect '(' after 'if'.");
        Expr condition = expression();
        consume(RIGHT_PAREN, "Expect ')' after if condition.");

        Stmt thenBranch = statement();
        Stmt elseBranch = null;
        if (match(ELSE)) {
            elseBranch = statement();
        }

        return new Stmt.If(condition, thenBranch, elseBranch);
    }
}
