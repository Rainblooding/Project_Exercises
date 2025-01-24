package io.github.rainblooding.cscript.base;

import io.github.rainblooding.cscript.CScript;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.rainblooding.cscript.base.TokenType.*;

public class Scanner {

    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;
    private int line = 1;

    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("and",    AND);
        keywords.put("class",  CLASS);
        keywords.put("else",   ELSE);
        keywords.put("false",  FALSE);
        keywords.put("for",    FOR);
        keywords.put("fun",    FUN);
        keywords.put("if",     IF);
        keywords.put("nil",    NIL);
        keywords.put("or",     OR);
        keywords.put("print",  PRINT);
        keywords.put("return", RETURN);
        keywords.put("super",  SUPER);
        keywords.put("this",   THIS);
        keywords.put("true",   TRUE);
        keywords.put("var",    VAR);
        keywords.put("while",  WHILE);
    }


    public Scanner(String source) {
        this.source = source;
    }

    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            //我们在下一个词素的开头。
            start = current;
            scanToken();
        }
        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(': addToken(LEFT_PAREN); break;
            case ')': addToken(RIGHT_PAREN); break;
            case '{': addToken(LEFT_BRACE); break;
            case '}': addToken(RIGHT_BRACE); break;
            case ',': addToken(COMMA); break;
            case '.': addToken(DOT); break;
            case '-': addToken(MINUS); break;
            case '+': addToken(PLUS); break;
            case ';': addToken(SEMICOLON); break;
            case '*': addToken(STAR); break;

            case '!':
                addToken(match('=') ? BANG_EQUAL : BANG);
                break;
            case '=':
                addToken(match('=') ? EQUAL_EQUAL : EQUAL);
                break;
            case '<':
                addToken(match('=') ? LESS_EQUAL : LESS);
                break;
            case '>':
                addToken(match('=') ? GREATER_EQUAL : GREATER);
                break;
            case '/':
                if (match('/')) {
                    // A comment goes until the end of the line.
                    while (peek() != '\n' && !isAtEnd()) advance();
                } else {
                    addToken(SLASH);
                }
                break;

            case ' ':
            case '\r':
            case '\t':
                // Ignore whitespace.
                break;

            case '\n':
                line++;
                break;
            case '"': string(); break;
            case 'o':
                if (match('r')) {
                    addToken(OR);
                }
                break;
            default:
                if (isDigit(c)) {
                    number();
                } else if (isAlpha(c)) {
                    identifier();
                }  else {
                    CScript.error(line, "Unexpected character.");
                }
                break;
        }
    }

    /**
     * 该方法用于获取源字符串中当前索引位置的字符，并将当前索引值递增
     * 它在解析或处理字符串时非常有用，允许以顺序方式逐个字符地读取源字符串
     *
     * @return 当前索引位置的字符
     * @throws IndexOutOfBoundsException 如果当前索引超出源字符串的范围，则抛出此异常
     */
    private char advance() {
        return source.charAt(current++);
    }

    /**
     * 返回当前字符，但不移动指针
     * 如果已经到达源字符串的末尾，则返回空字符
     * 此方法用于在不改变当前指针位置的情况下，预览下一个字符
     *
     * @return 当前指针位置的字符，如果到达末尾则返回 '\0'
     */
    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }


    /**
     * 尝试匹配当前字符与预期字符是否相同
     * 如果当前字符与预期字符匹配成功，则当前索引向前移动；否则，保持当前索引不变
     *
     * @param expected 预期匹配的字符
     * @return 如果当前字符与预期字符匹配成功，则返回true；否则返回false
     */
    private boolean match(char expected) {
        // 检查是否到达源代码的末尾，如果到达末尾，则不执行匹配逻辑，直接返回false
        if (isAtEnd()) return false;

        // 检查当前字符是否与预期字符相匹配，如果不匹配，则直接返回false
        if (source.charAt(current) != expected) return false;

        // 当前字符与预期字符匹配成功后，将当前索引向前移动，以便进行下一次字符的匹配
        current++;
        // 匹配成功，返回true
        return true;
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }
        if (isAtEnd()) {
            CScript.error(line, "Unterminated string.");
            return;
        }

        advance();
        String value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    public boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void number() {
        while(isDigit(peek())) {
            advance();
        }

        if (peek() == '.' && isDigit(peekNext())) {
            advance();
            while (isDigit(peek())) advance();
        }

        addToken(NUMBER, Double.parseDouble(source.substring(start, current)));
    }


    private void identifier() {
        while (isAlphaNumeric(peek())) advance();
        String text = source.substring(start, current);
        TokenType type = keywords.get(text);
        if (type == null) type = IDENTIFIER;
        addToken(type);
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }


    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }


}
