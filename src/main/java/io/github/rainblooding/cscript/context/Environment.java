package io.github.rainblooding.cscript.context;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.exception.RuntimeError;

import java.util.HashMap;
import java.util.Map;

public class Environment {

    private final Map<String, Object> values = new HashMap<>();

    public Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }

        throw new RuntimeError(name,
                "Undefined variable '" + name.lexeme + "'.");
    }

    public void define(String name, Object value) {
        values.put(name, value);
    }
}
