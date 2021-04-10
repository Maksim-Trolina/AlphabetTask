package com.company;

import java.util.Scanner;
import java.util.Stack;



public class Alphabet {

    enum Color {

        WHITE,
        GRAY,
        BLACK
    }

    private static final byte ALPHABET_SIZE = 26;

    private static final boolean[][] GRAPH = new boolean[ALPHABET_SIZE][ALPHABET_SIZE];

    private static final Color[] COLORS = new Color[ALPHABET_SIZE];

    private static final byte FIRST_LETTER_CODE = 97;

    private static final String DEFAULT_RESPONSE = "Impossible";

    /*public Alphabet() {

        COLORS = new Color[ALPHABET_SIZE];

        GRAPH = new boolean[ALPHABET_SIZE][ALPHABET_SIZE];
    }*/

    private static boolean dfs(int vertex) {

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

    private static void dfs(int vertex, Stack<Integer> alphabetOrder) {

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

    private static boolean isCycle() {

        for (var i = 0; i < ALPHABET_SIZE; i++) {

            if (dfs(i)) {

                return true;
            }
        }

        return false;
    }

    private static void resetColor() {

        for (var i = 0; i < ALPHABET_SIZE; i++) {

            COLORS[i] = Color.WHITE;
        }
    }

    private static String getDefaultAlphabet() {

        var result = "";

        for (var i = 0; i < ALPHABET_SIZE; i++) {

            var code = FIRST_LETTER_CODE + i;

            result += (char) code;
        }

        return result;
    }

    private static boolean isEqual(String firstStr, String secondStr) {

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

    private static Stack<Integer> topological_sort() {

        Stack<Integer> alphabetOrder = new Stack<>();

        for (var i = 0; i < ALPHABET_SIZE; i++) {

            if (COLORS[i] == Color.WHITE) {

                dfs(i, alphabetOrder);
            }
        }

        return alphabetOrder;
    }

    private static String createAlphabet(Stack<Integer> alphabetOrder) {

        var alphabet = "";

        while (!alphabetOrder.empty()) {

            var code = FIRST_LETTER_CODE + alphabetOrder.pop();

            alphabet += (char) code;
        }

        return alphabet;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        var wordCount = Integer.parseInt(in.nextLine());

        var lastName = in.nextLine();

        var isImpossible = false;

        String alphabet;

        if (wordCount == 1) {

            alphabet = getDefaultAlphabet();

            System.out.println(alphabet);

            return;
        }

        for (var i = 1; i < wordCount; i++) {

            var name = in.nextLine();

            var equal = isEqual(name, lastName);

            if (equal && name.length() < lastName.length()) {

                isImpossible = true;

                break;
            }

            lastName = name;
        }

        resetColor();

        isImpossible = isImpossible || isCycle();

        if (isImpossible) {

            System.out.println(DEFAULT_RESPONSE);

            return;
        }

        resetColor();

        var alphabetOrder = topological_sort();

        alphabet = createAlphabet(alphabetOrder);

        System.out.println(alphabet);
    }
}
