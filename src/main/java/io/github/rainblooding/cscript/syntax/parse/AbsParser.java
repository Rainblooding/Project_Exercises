package io.github.rainblooding.cscript.syntax.parse;

import io.github.rainblooding.cscript.CScript;
import io.github.rainblooding.cscript.base.Token;
import io.github.rainblooding.cscript.base.TokenType;

import java.util.List;

import static io.github.rainblooding.cscript.base.TokenType.SEMICOLON;

/**
 * 抽象语法树解析器
 * 此类用于解析抽象语法树（Abstract Syntax Tree，AST），
 * 并根据解析结果生成相应的抽象语法树节点。
 *
 * @author rainblooding
 * @version 1.0
 */
public abstract class AbsParser {

    protected List<Token> tokens;
    protected int current = 0;


    private static class ParseError extends RuntimeException {}

    protected AbsParser(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * 消耗当前词法分析器位置的令牌
     * 如果当前令牌的类型与预期的类型匹配，则移动到下一个令牌
     * 否则，抛出一个错误，指示期望的令牌类型不匹配
     *
     * @param type 期望的令牌类型
     * @param message 错误信息，当当前令牌类型不匹配时抛出
     * @return 返回当前（如果类型匹配）或下一个令牌对象
     */
    protected Token consume(TokenType type, String message) {
        // 检查当前令牌是否与预期类型匹配
        if (check(type)) return advance();

        // 如果类型不匹配，抛出错误
        throw error(peek(), message);
    }

    /**
     * 生成一个解析错误对象
     * 此方法在遇到无法解析的语法时被调用，它会记录一个错误信息，并返回一个ParseError对象
     * 表示发生了解析错误
     *
     * @param token    发生错误的令牌，用于定位错误位置
     * @param message  错误信息，描述发生了什么错误
     * @return         返回一个ParseError对象，表示解析过程中遇到了错误
     */
    protected ParseError error(Token token, String message) {
        // 记录错误信息
        CScript.error(token, message);
        // 返回一个新的ParseError对象
        return new ParseError();
    }


    protected void synchronize() {
        advance();

        while (!isAtEnd()) {
            if (previous().type == SEMICOLON) return;

            switch (peek().type) {
                case CLASS:
                case FUN:
                case VAR:
                case FOR:
                case IF:
                case WHILE:
                case PRINT:
                case RETURN:
                    return;
            }

            advance();
        }
    }

    /**
     * 检查当前词法符号是否与给定的TokenType之一匹配
     * 如果匹配，则移动到下一个词法符号，并返回true
     * 如果不匹配，则不改变当前词法符号的位置，并返回false
     *
     * @param types 可变参数，表示要匹配的TokenType数组
     * @return 如果当前词法符号与给定的TokenType之一匹配，则返回true，否则返回false
     */
    protected boolean match(TokenType... types) {
        // 遍历给定的TokenType数组
        for (TokenType type : types) {
            // 检查当前词法符号是否与给定的TokenType匹配
            if (check(type)) {
                // 如果匹配，则移动到下一个词法符号，并返回true
                advance();
                return true;
            }
        }

        // 如果没有找到匹配的TokenType，则返回false
        return false;
    }

    /**
     * 检查当前词元的类型是否与给定的TokenType类型匹配
     * 此方法用于在不移动词元指针的情况下，检查当前词元的类型
     *
     * @param type 要检查的TokenType类型
     * @return 如果当前词元类型与给定类型匹配，则返回true；否则返回false
     */
    protected boolean check(TokenType type) {
        // 如果已经到达词元序列的末尾，则返回false
        if (isAtEnd()) return false;
        // 检查当前词元的类型是否与给定的TokenType类型匹配
        return peek().type == type;
    }

    /**
     * 将当前的词法分析器位置向前移动到下一个词元
     * 如果当前不是词法分析的末尾，则将当前位置递增
     * 此方法用于在词法分析过程中前进到下一个字符或词元
     *
     * @return 移动到下一个词元位置之前的词元
     */
    protected Token advance() {
        // 检查是否已经到达词法分析的末尾，如果没有，则当前位置递增
        if (!isAtEnd()) current++;
        // 返回移动位置之前当前词元
        return previous();
    }

    protected boolean isAtEnd() {
        return peek().type == TokenType.EOF;
    }

    protected Token peek() {
        return tokens.get(current);
    }

    protected Token previous() {
        return tokens.get(current - 1);
    }

}
