package io.github.rainblooding.regex;

public enum Token {
    CHAR, ALTER, ZERO_OR_ONE, ZERO_OR_MORE, ONE_OR_MORE, NONE;

    static Token map(char c) {
        switch (c) {
            case '*': return ZERO_OR_MORE;
            case '?': return ZERO_OR_ONE;
            case '+': return ONE_OR_MORE;
            case '|': return ALTER;
            default: return CHAR;
        }
    }
}
