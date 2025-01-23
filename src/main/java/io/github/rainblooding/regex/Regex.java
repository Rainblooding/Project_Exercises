package io.github.rainblooding.regex;
import java.util.*;









public class Regex {
    private final State startState;

    Regex(String pattern) {
        this.startState = Parser.parse(pattern);
    }

    boolean isMatch(String input) {
        return match(input, startState);
    }

    private boolean match(String input, State state) {
        List<State> currentStateSet = new ArrayList<>();
        currentStateSet.add(state);

        List<State> nextStateSet = closure(currentStateSet);

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            currentStateSet = move(nextStateSet, c);
            nextStateSet = closure(currentStateSet);

            if (nextStateSet.isEmpty()) return false;

            if (isAcceptable(nextStateSet) && i == input.length() - 1) {
                return true;
            }
        }
        return false;
    }

    private List<State> closure(List<State> set) {
        List<State> res = new ArrayList<>(set);
        Stack<State> stack = new Stack<>();
        stack.addAll(set);

        while (!stack.isEmpty()) {
            State state = stack.pop();
            if (state.edge == 'ε') {
                for (State next : state.nexts) {
                    if (!res.contains(next)) {
                        res.add(next);
                        stack.push(next);
                    }
                }
            }
        }
        return res;
    }

    private List<State> move(List<State> set, char c) {
        List<State> res = new ArrayList<>();
        for (State state : set) {
            if (state.edge == c) {
                res.addAll(state.nexts);
            }
        }
        return res;
    }

    private boolean isAcceptable(List<State> set) {
        for (State state : set) {
            if (state.nexts.isEmpty()) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Regex regex = new Regex("ax*c");
        System.out.println(regex.isMatch("axxxxxc")); // true
    }
}

