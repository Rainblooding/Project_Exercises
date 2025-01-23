package io.github.rainblooding.regex;

public class Parser {
    static Lexer lexer;

    static State parse(String pattern) {
        lexer = new Lexer(pattern);
        lexer.advance();
        StateTransition trans = new StateTransition();
        regexToState(trans);
        return trans.startNode;
    }

    static boolean regexToState(StateTransition trans) {
        return expr(trans);
    }

    static boolean expr(StateTransition trans) {
        conn(trans);
        StateTransition newTrans = new StateTransition();

        while (lexer.match(Token.ALTER)) {
            lexer.advance();
            conn(newTrans);

            State start = new State();
            start.nexts.add(newTrans.startNode);
            start.nexts.add(trans.startNode);

            trans.startNode = start;

            State end = new State();
            newTrans.endNode.nexts.add(end);
            trans.endNode.nexts.add(end);
            trans.endNode = end;
        }
        return true;
    }

    static boolean conn(StateTransition trans) {
        if (isConn(lexer.currentToken)) {
            factor(trans);
        }
        while (isConn(lexer.currentToken)) {
            StateTransition newTrans = new StateTransition();
            factor(newTrans);
            trans.endNode.nexts.add(newTrans.startNode);
            trans.endNode = newTrans.endNode;
        }
        return true;
    }

    static boolean factor(StateTransition trans) {
        term(trans);
        if (lexer.match(Token.ZERO_OR_MORE)) {
            zeroOrMore(trans);
        } else if (lexer.match(Token.ZERO_OR_ONE)) {
            zeroOrOne(trans);
        } else if (lexer.match(Token.ONE_OR_MORE)) {
            oneOrMore(trans);
        }
        return true;
    }

    static boolean term(StateTransition trans) {
        State start = new State();
        trans.startNode = start;

        State end = new State();
        start.nexts.add(end);
        trans.endNode = end;

        start.edge = lexer.lexeme;
        lexer.advance();
        return true;
    }

    static boolean zeroOrMore(StateTransition trans) {
        if (!lexer.match(Token.ZERO_OR_MORE)) return false;

        State start = new State();
        State end = new State();

        start.nexts.add(trans.startNode);
        start.nexts.add(end);

        trans.endNode.nexts.add(trans.startNode);
        trans.endNode.nexts.add(end);

        trans.startNode = start;
        trans.endNode = end;

        lexer.advance();
        return true;
    }

    static boolean zeroOrOne(StateTransition trans) {
        if (!lexer.match(Token.ZERO_OR_ONE)) return false;

        State start = new State();
        State end = new State();

        start.nexts.add(trans.startNode);
        start.nexts.add(end);

        trans.startNode = start;
        trans.endNode = end;

        lexer.advance();
        return true;
    }

    static boolean oneOrMore(StateTransition trans) {
        if (!lexer.match(Token.ONE_OR_MORE)) return false;

        State start = new State();
        State end = new State();

        start.nexts.add(trans.startNode);

        trans.endNode.nexts.add(trans.startNode);
        trans.endNode.nexts.add(end);

        trans.startNode = start;
        trans.endNode = end;

        lexer.advance();
        return true;
    }

    static boolean isConn(Token token) {
        return token != Token.ALTER && token != Token.ZERO_OR_ONE &&
                token != Token.ZERO_OR_MORE && token != Token.ONE_OR_MORE &&
                token != Token.NONE;
    }
}
