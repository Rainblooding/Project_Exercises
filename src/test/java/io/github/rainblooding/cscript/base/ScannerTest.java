package io.github.rainblooding.cscript.base;


import io.github.rainblooding.cscript.CScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class ScannerTest {

    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        // 在每个测试之前重置错误标志
        CScript.hadError = false;
    }

    @Test
    public void scanTokens_SimpleExpression_CorrectTokens() {
        String source = "var a = 10 + 5;";
        scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        assertEquals(8, tokens.size());
        assertEquals(TokenType.VAR, tokens.get(0).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(1).type);
        assertEquals(TokenType.EQUAL, tokens.get(2).type);
        assertEquals(TokenType.NUMBER, tokens.get(3).type);
        assertEquals(TokenType.PLUS, tokens.get(4).type);
        assertEquals(TokenType.NUMBER, tokens.get(5).type);
        assertEquals(TokenType.SEMICOLON, tokens.get(6).type);
        assertEquals(TokenType.EOF, tokens.get(7).type);
    }

    @Test
    public void scanTokens_StringLiteral_CorrectToken() {
        String source = "\"Hello, World!\"";
        scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        assertEquals(2, tokens.size());
        assertEquals(TokenType.STRING, tokens.get(0).type);
        assertEquals("Hello, World!", tokens.get(0).literal);
        assertEquals(TokenType.EOF, tokens.get(1).type);
    }

    @Test
    public void scanTokens_UnterminatedString_ErrorLogged() {
        String source = "\"This is a test";
        scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        assertTrue(CScript.hadError);
        assertEquals(1, tokens.size());
        assertEquals(TokenType.EOF, tokens.get(0).type);
    }

    @Test
    public void scanTokens_UnexpectedCharacter_ErrorLogged() {
        String source = "var a = @;";
        scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        assertTrue(CScript.hadError);
        assertEquals(5, tokens.size());
        assertEquals(TokenType.EOF, tokens.get(4).type);
    }

    @Test
    public void scanTokens_EmptySource_OnlyEOFToken() {
        String source = "";
        scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        assertEquals(1, tokens.size());
        assertEquals(TokenType.EOF, tokens.get(0).type);
    }

    @Test
    public void scanTokens_ComplexExpression_CorrectTokens() {
        String source = "if (a > 10) { print(\"Success\"); }";
        scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        assertEquals(14, tokens.size());
        assertEquals(TokenType.IF, tokens.get(0).type);
        assertEquals(TokenType.LEFT_PAREN, tokens.get(1).type);
        assertEquals(TokenType.IDENTIFIER, tokens.get(2).type);
        assertEquals(TokenType.GREATER, tokens.get(3).type);
        assertEquals(TokenType.NUMBER, tokens.get(4).type);
        assertEquals(TokenType.RIGHT_PAREN, tokens.get(5).type);
        assertEquals(TokenType.LEFT_BRACE, tokens.get(6).type);
        assertEquals(TokenType.PRINT, tokens.get(7).type);
        assertEquals(TokenType.LEFT_PAREN, tokens.get(8).type);
        assertEquals(TokenType.STRING, tokens.get(9).type);
        assertEquals(TokenType.RIGHT_PAREN, tokens.get(10).type);
        assertEquals(TokenType.SEMICOLON, tokens.get(11).type);
        assertEquals(TokenType.RIGHT_BRACE, tokens.get(12).type);
        assertEquals(TokenType.EOF, tokens.get(13).type);
    }

}
