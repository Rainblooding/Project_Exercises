package io.github.rainblooding.lex;

import io.github.rainblooding.utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaParse {

    private char[] chars;

    private List<JavaToken> tokenList;

    public JavaParse(String str) {
        chars = str.toCharArray();
        tokenList = new ArrayList<>();
    }


    public List<JavaToken> parse() {
        Character ch;
        while ((ch = nextChar()) != null) {
            parseHelper(ch);
        }
        return tokenList;
    }


    public void parseHelper(Character ch) {

        if (ch == '/') {
            Character next = nextChar();
            if (next == '/') {
                String comment = "//" ;
                while ((next = nextChar()) != null) {
                    if (next == null || next == '\n') {
                        if (next == '\n') {
                            prev();
                        }
                        break;
                    }
                    comment += next;
                }
                tokenList.add(new JavaToken(comment, TokenType.COMMENT));
            } else {
                prev();
            }
        } else if (ch == '*' || ch == '/') {
            tokenList.add(new JavaToken(ch.toString(), TokenType.OPERATOR));
        } else if(ch == '>' || ch == '<' || ch == '=' || ch == '!') {
            Character next = nextChar();
            if (next == '=') {
                tokenList.add(new JavaToken(new String(new char[]{ch, next}), TokenType.OPERATOR));
            } else {
                prev();
                tokenList.add(new JavaToken(ch.toString(), TokenType.OPERATOR));
            }
        } else if(ch == '+') {
            Character next = nextChar();
            if (next == '+' || next == '=') {
                tokenList.add(new JavaToken(new String(new char[]{ch, next}), TokenType.OPERATOR));
            } else {
                prev();
                tokenList.add(new JavaToken(ch.toString(), TokenType.OPERATOR));
            }
        } else if(ch == '-') {
            Character next = nextChar();
            if (next == '-' || next == '=') {
                tokenList.add(new JavaToken(new String(new char[]{ch, next}), TokenType.OPERATOR));
            } else {
                prev();
                tokenList.add(new JavaToken(ch.toString(), TokenType.OPERATOR));
            }
        } else if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_') { // 关键字/标识符
            String id = ch.toString();

            while (true) {
                Character next = nextChar();
                if (next == null) {
                    break;
                }
                if ((next >= 'a' && next <= 'z') || (next >= 'A' && next <= 'Z') || next == '_' || (next >= '0' && next <= '9')) {
                    id += next;
                } else {
                    prev();
                    break;
                }
            }
            tokenList.add(new JavaToken(id, TokenType.IDENTIFIER));

        } else if((ch >= '0' && ch <= '9')) { // 数字
            String num = ch.toString();
            while (true) {
                Character next = nextChar();
                if (next == null) {
                    break;
                }
                if (next >= '0' && next <= '9') {
                    num += next;
                }
                prev();
                break;
            }
            tokenList.add(new JavaToken(num, TokenType.NUMBER));
        } else if(ch == '\'') { // 字符
            String str = ch.toString();
            while (true) {
                Character next = nextChar();
                if (next == null) {
                    throw new RuntimeException("解析失败：错误的字符");
                }
                str += next;
                if (next == '\'') {
                    break;
                }
            }
            tokenList.add(new JavaToken(str, TokenType.CHAR));

        } else if(ch == '\"') { // 字符串
            String str = ch.toString();
            while (true) {
                Character next = nextChar();
                if (next == null) {
                    throw new RuntimeException("解析失败：错误的字符串");
                }
                str += next;
                if (next == '\"') {
                    break;
                }
            }
            tokenList.add(new JavaToken(str, TokenType.CHAR));

        } else if(ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') { // 空白符
            String whitespace = ch.toString();
            while (true) {
                Character next = nextChar();
                if (next == null) {
                    break;
                }
                if (next == ' ' || next == '\t' || next == '\n' || next == '\r') {
                    whitespace += next;
                } else {
                    prev();
                    break;
                }
                tokenList.add(new JavaToken(whitespace, TokenType.WHITESPACE));
            }
        } else {
            tokenList.add(new JavaToken(ch.toString(), TokenType.UNKNOWN));
        }
    }

    int current = 0;

    public Character nextChar() {
        if (chars == null || current >= chars.length) {
            return null;
        }
        return chars[current++];
    }

    public void prev() {
        current--;
    }


    public static void main(String[] args) throws Exception {
        JavaParse javaParse = new JavaParse("int x = 2;\nx+=3;x++;++x");
        List<JavaToken> parse = javaParse.parse();
        for (JavaToken token : parse) {
            if (token.getTokenType() != TokenType.WHITESPACE)
            System.out.println(token.getContent() + " " + token.getTokenType());
        }
    }

}
