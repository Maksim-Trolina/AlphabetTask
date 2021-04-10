package com.company;

import java.util.ArrayList;

public class Main {

    private static boolean dfs(int v, byte[] color, byte[][] graph) {

        color[v] = 1;

        for (var i = 0; i < graph[v].length; i++) {

            if (graph[v][i] == 1) {

                if (color[i] == 0) {

                    if (dfs(i, color, graph)) {

                        return true;
                    }
                } else if (color[i] == 1) {

                    return true;
                }
            }
        }

        color[v] = 2;

        return false;
    }

    private static void dfs(int v, byte[] used, byte[][] graph, ArrayList<Integer> ans) {

        used[v] = 1;

        for (var i = 0; i < graph[v].length; i++) {

            if (graph[v][i] == 1) {

                if (used[i] == 0) {

                    dfs(i, used, graph, ans);
                }
            }
        }

        ans.add(v);
    }

    public static void main(String[] args) {

        Alphabet alphabet = new Alphabet();

        alphabet.main();

        /*var res = alphabet.solve();

        System.out.println(res);*/
        /*byte[] color = new byte[26];

        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());

        byte[][] graph = new byte[26][26];

        var lastName = in.nextLine();

        var isImpossible = false;

        if (n == 1) {

            for (int i = 0; i < 26; i++) {

                var code = 97 + i;

                System.out.print((char) code);
            }

            return;
        }

        for (var i = 1; i < n; i++) {

            var name = in.nextLine();

            var equal = true;

            for (var j = 0; j < Math.min(name.length(), lastName.length()); j++) {

                if (name.charAt(j) != lastName.charAt(j)) {

                    var index1 = (int) name.charAt(j) - 97;

                    var index2 = (int) lastName.charAt(j) - 97;

                    graph[index1][index2] = 1;

                    equal = false;

                    break;
                }
            }

            if (equal && name.length() < lastName.length()) {

                isImpossible = true;

                break;
            }

            lastName = name;
        }

        for (var i = 0; i < 26; i++) {

            if (dfs(i, color, graph)) {

                isImpossible = true;

                break;
            }
        }

        if (isImpossible) {

            System.out.println("Impossible");

            return;
        }

        for (var i = 0; i < 26; i++) {

            color[i] = 0;
        }

        ArrayList<Integer> ans = new ArrayList<Integer>();

        for (var i = 0; i < 26; i++) {

            if (color[i] == 0) {

                dfs(i, color, graph, ans);
            }
        }


        for (var i = 0; i < ans.size(); i++) {

            var code = 97 + ans.get(i);

            System.out.print((char) code);
        }*/

    }
}
