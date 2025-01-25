package io.github.rainblooding.cscript;

import io.github.rainblooding.cscript.base.Scanner;
import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.base.TokenType;
import io.github.rainblooding.cscript.exception.RuntimeError;
import io.github.rainblooding.cscript.syntax.*;
import io.github.rainblooding.cscript.syntax.interpreter.Interpreter;
import io.github.rainblooding.cscript.syntax.parse.Parser;
import io.github.rainblooding.cscript.syntax.resolver.Resolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CScript {

    public static boolean hadError = false;
    static boolean hadRuntimeError = false;

    private static final Interpreter interpreter = new Interpreter();

    /**
     * 运行代码
     *
     * @param source
     */
    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();
        Parser parser = new Parser(tokens);
        List<Stmt> statements = parser.parse();


        // Stop if there was a syntax error.
        if (hadError) return;

        Resolver resolver = new Resolver(interpreter);
        resolver.resolve(statements);
        if (hadError) return;

        interpreter.interpret(statements);
//        System.out.println(new AstPrinter().print(expression));
    }

    /**
     * 运行指定文件
     *
     * @param path
     * @throws IOException
     */
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));

        run(new String(bytes, Charset.defaultCharset()));
        if (hadError) System.exit(65);
        if (hadRuntimeError) System.exit(70);
    }

    /**
     * 交互式运行
     *
     * @throws IOException
     */
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError = false;
        }
    }


    public static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where,
                               String message) {
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    public static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, " at end", message);
        } else {
            report(token.line, " at '" + token.lexeme + "'", message);
        }
    }

    public static void run(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: csc [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }

    }

    public  static void runtimeError(RuntimeError error) {
        System.err.println(error.getMessage() +
                "\n[line " + error.token.line + "]");
        hadRuntimeError = true;
    }


    public static void main(String[] args) {
        run("class Bagel {}\n" +
                "var bagel = Bagel();\n" +
                "print bagel;");
    }

}
