package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.CScript;
import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.base.TokenType;
import io.github.rainblooding.cscript.syntax.Expr;
import io.github.rainblooding.cscript.syntax.Stmt;

import java.util.ArrayList;
import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.*;

public class Parser extends WhileParser {



    public Parser(List<Token> tokens) {
        super(tokens);
    }

    public List<Stmt> parse () {
        List<Stmt> statements = new ArrayList<>();
        while (!isAtEnd()) {
            statements.add(declaration());
        }
        return statements;
    }


}
