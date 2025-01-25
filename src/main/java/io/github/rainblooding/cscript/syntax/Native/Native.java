package io.github.rainblooding.cscript.syntax.Native;

import io.github.rainblooding.cscript.context.Environment;
import io.github.rainblooding.cscript.syntax.base.ClosureFunction;
import io.github.rainblooding.cscript.syntax.base.MethodInstance;

import java.util.HashMap;
import java.util.Map;

public class Native {

    public static void init(Environment globals) {
        Map<String, ClosureFunction> methods = new HashMap<>();
        methods.put("println", NativeFunction.createFunction(str -> System.out.println(str)));
        NativeClass system = NativeClass.createClass("System", methods);
        globals.define("System", system);
        globals.define("system", new MethodInstance(system));
    }
}
