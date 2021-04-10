package com.company;

import java.util.Stack;


public class Alphabet {

    enum Color {

        WHITE,
        GRAY,
        BLACK
    }

    private final byte ALPHABET_SIZE = 26;

    private final boolean[][] GRAPH = new boolean[ALPHABET_SIZE][ALPHABET_SIZE];

    private final Color[] COLORS = new Color[ALPHABET_SIZE];

    private final byte FIRST_LETTER_CODE = 97;

    private boolean dfs(int vertex) {

        COLORS[vertex] = Color.GRAY;

        for (var i = 0; i < GRAPH[vertex].length; i++) {

            if (GRAPH[vertex][i]) {

                if (COLORS[i] == Color.WHITE) {

                    if (dfs(i)) {

                        return true;
                    }
                } else if (COLORS[i] == Color.GRAY) {

                    return true;
                }
            }
        }

        COLORS[vertex] = Color.BLACK;

        return false;
    }

    private void dfs(int vertex, Stack<Integer> alphabetOrder) {

        COLORS[vertex] = Color.GRAY;

        for (var i = 0; i < GRAPH[vertex].length; i++) {

            if (GRAPH[vertex][i]) {

                if (COLORS[i] == Color.WHITE) {

                    dfs(i, alphabetOrder);
                }
            }
        }

        alphabetOrder.push(vertex);
    }

    private boolean isCycle() {

        for (var i = 0; i < ALPHABET_SIZE; i++) {

            if (dfs(i)) {

                return true;
            }
        }

        return false;
    }

    private void resetColor() {

        for (var i = 0; i < ALPHABET_SIZE; i++) {

            COLORS[i] = Color.WHITE;
        }
    }

    private String getDefaultAlphabet() {

        var result = new StringBuilder();

        for (var i = 0; i < ALPHABET_SIZE; i++) {

            var code = FIRST_LETTER_CODE + i;

            result.append((char) code);
        }

        return result.toString();
    }

    private boolean isEqual(String firstStr, String secondStr) {

        for (var j = 0; j < Math.min(firstStr.length(), secondStr.length()); j++) {

            if (firstStr.charAt(j) != secondStr.charAt(j)) {

                var indexFirst = (int) firstStr.charAt(j) - FIRST_LETTER_CODE;

                var indexSecond = (int) secondStr.charAt(j) - FIRST_LETTER_CODE;

                GRAPH[indexSecond][indexFirst] = true;

                return false;
            }
        }

        return true;
    }

    private Stack<Integer> topological_sort() {

        Stack<Integer> alphabetOrder = new Stack<>();

        for (var i = 0; i < ALPHABET_SIZE; i++) {

            if (COLORS[i] == Color.WHITE) {

                dfs(i, alphabetOrder);
            }
        }

        return alphabetOrder;
    }

    private String createAlphabet(Stack<Integer> alphabetOrder) {

        var alphabet = new StringBuilder();

        while (!alphabetOrder.empty()) {

            var code = FIRST_LETTER_CODE + alphabetOrder.pop();

            alphabet.append((char) code);
        }

        return alphabet.toString();
    }

    public String solve(String[] names) throws Exception {

        if (names.length == 1) {

            return getDefaultAlphabet();
        }

        var isImpossible = false;

        for (var i = 1; i < names.length; i++) {

            var equal = isEqual(names[i], names[i - 1]);

            if (equal && names[i].length() < names[i - 1].length()) {

                isImpossible = true;

                break;
            }
        }

        resetColor();

        isImpossible = isImpossible || isCycle();

        if (isImpossible) {

            throw new Exception("Impossible");
        }

        resetColor();

        var alphabetOrder = topological_sort();

        return createAlphabet(alphabetOrder);
    }
}
