package io.github.rainblooding.cscript.syntax.base;

import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.exception.RuntimeError;

import java.util.HashMap;
import java.util.Map;

public class GetInstance extends CSInstance {

    protected final Map<String, Object> fields = new HashMap<>();

    public GetInstance(CSClass klass) {
        super(klass);
    }

    public Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        throw new RuntimeError(name,
                "Undefined property '" + name.lexeme + "'.");
    }
}
