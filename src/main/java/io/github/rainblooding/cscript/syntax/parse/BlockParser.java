package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Stmt;

import java.util.ArrayList;
import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.*;

public class BlockParser extends AssignParser {

    protected BlockParser(List<Token> tokens) {
        super(tokens);
    }

    @Override
    protected Stmt statement() {
        if (match(PRINT)) return printStatement();
        if (match(LEFT_BRACE)) return new Stmt.Block(block());
        return expressionStatement();
    }

    protected List<Stmt> block() {
        List<Stmt> statements = new ArrayList<>();

        while (!check(RIGHT_BRACE) && !isAtEnd()) {
            statements.add(declaration());
        }

        consume(RIGHT_BRACE, "Expect '}' after block.");
        return statements;
    }
}
