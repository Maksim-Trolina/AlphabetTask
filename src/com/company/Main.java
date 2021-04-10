package com.company;

import java.util.Scanner;

public class Main {

    private static String[] read() {

        var in = new Scanner(System.in);

        var wordCount = Integer.parseInt(in.nextLine());

        var names = new String[wordCount];

        for (var i = 0; i < wordCount; i++) {

            names[i] = in.nextLine();
        }

        return names;
    }

    public static void main(String[] args) {

        var names = read();

        var alphabet = new Alphabet();

        try {

            var result = alphabet.solve(names);

            System.out.println(result);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}

