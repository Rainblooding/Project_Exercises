package io.github.rainblooding.cscript.exception;

import io.github.rainblooding.cscript.base.Token;

public class RuntimeError extends RuntimeException {
    public final Token token;

    public RuntimeError(Token token, String message) {
        super(message);
        this.token = token;
    }
}
