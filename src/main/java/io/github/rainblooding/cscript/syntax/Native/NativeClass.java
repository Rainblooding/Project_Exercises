package io.github.rainblooding.cscript.syntax.Native;

import io.github.rainblooding.cscript.CScript;
import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.base.TokenType;
import io.github.rainblooding.cscript.exception.RuntimeError;
import io.github.rainblooding.cscript.syntax.base.ClosureFunction;
import io.github.rainblooding.cscript.syntax.base.MethodClass;
import io.github.rainblooding.cscript.syntax.interpreter.CallInterpreter;

import java.util.List;
import java.util.Map;

public class NativeClass extends MethodClass {

    private Token token;

    private NativeClass(String className, Map<String, ClosureFunction> methods) {
        super(className, methods);
        token = new Token(TokenType.NATIVE, className, null, 0);
    }

    public static NativeClass createClass(String className, Map<String, ClosureFunction> methods) {
        return new NativeClass(className, methods);
    }

    @Override
    public Object call(CallInterpreter interpreter, List<Object> arguments) {
        throw new RuntimeError(token, "本地方法无法实例化");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int arity() {
        return super.arity();
    }
}
