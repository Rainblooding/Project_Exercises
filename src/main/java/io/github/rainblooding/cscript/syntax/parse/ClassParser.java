package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.syntax.Stmt;

import java.util.ArrayList;
import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.*;


/**
 * declaration    → classDecl
 *                | funDecl
 *                | varDecl
 *                | statement ;
 *
 * classDecl      → "class" IDENTIFIER "{" function* "}" ;
 * function       → IDENTIFIER "(" parameters? ")" block ;
 * parameters     → IDENTIFIER ( "," IDENTIFIER )* ;
 */
public class ClassParser extends ReturnParser {

    protected ClassParser(List<Token> tokens) {
        super(tokens);
    }


    /**
     *
     * declaration    → classDecl
     *                | funDecl
     *                | varDecl
     *                | statement ;
     *
     * @return
     */
    @Override
    protected Stmt declaration() {
        try {
            if (match(CLASS)) return classDeclaration();
            if (match(FUN)) return function("function");
            if (match(VAR)) return varDeclaration();

            return statement();
        } catch (ParseError error) {
            synchronize();
            return null;
        }
    }

    /**
     *
     * classDecl      → "class" IDENTIFIER "{" function* "}" ;
     * function       → IDENTIFIER "(" parameters? ")" block ;
     * parameters     → IDENTIFIER ( "," IDENTIFIER )* ;
     *
     * @return
     */
    protected Stmt classDeclaration() {
        Token name = consume(IDENTIFIER, "Expect class name.");
        consume(LEFT_BRACE, "Expect '{' before class body.");

        List<Stmt.Function> methods = new ArrayList<>();
        while (!check(RIGHT_BRACE) && !isAtEnd()) {
            methods.add(function("method"));
        }

        consume(RIGHT_BRACE, "Expect '}' after class body.");

        return new Stmt.Class(name, methods);
    }
}
