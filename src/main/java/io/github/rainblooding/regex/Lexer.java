package io.github.rainblooding.regex;

public class Lexer {
    private final char[] pattern;
    private int pos = 0;
    Token currentToken = Token.NONE;
    char lexeme = 'ε';

    Lexer(String pattern) {
        this.pattern = pattern.toCharArray();
    }



    Token advance() {
        if (pos >= pattern.length) {
            currentToken = Token.NONE;
            return Token.NONE;
        }
        char c = pattern[pos++];
        lexeme = c;
        currentToken = Token.map(c);
        return currentToken;
    }

    boolean match(Token token) {
        return currentToken == token;
    }
}
