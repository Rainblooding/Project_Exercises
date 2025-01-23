package io.github.rainblooding.lex;

public class JavaToken {

    private String content;

    private TokenType tokenType;


    public JavaToken(String content, TokenType tokenType) {
        this.content = content;
        this.tokenType = tokenType;
    }

    public String getContent() {
        return content;
    }

    public TokenType getTokenType() {
        return tokenType;
    }
}
