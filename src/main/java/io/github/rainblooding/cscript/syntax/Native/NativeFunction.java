package io.github.rainblooding.cscript.syntax.Native;

import io.github.rainblooding.cscript.context.Environment;
import io.github.rainblooding.cscript.syntax.Stmt;
import io.github.rainblooding.cscript.syntax.base.ClosureFunction;
import io.github.rainblooding.cscript.syntax.interpreter.CallInterpreter;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class NativeFunction extends ClosureFunction {

    private Function<List<Object>, Object> function;
    private int arity;


    private NativeFunction(Function function, int arity) {
        super(null, null);
        this.function = function;
        this.arity = arity;
    }

    public static NativeFunction createFunction(Function<List<Object>, Object> function, int arity) {
        return new NativeFunction(function, arity);
    }

    public static NativeFunction createFunction(Consumer<String> function) {
        return createFunction((list) -> {
            function.accept(list.get(0).toString());
            return null;
        }, 1);
    }

    @Override
    public Object call(CallInterpreter interpreter, List<Object> arguments) {
        return function.apply(arguments);
    }

    @Override
    public int arity() {
        return arity;
    }

    @Override
    public String toString() {
        return "<native fn>";
    }
}
