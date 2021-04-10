package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AlphabetTest {

    @Test
    void solve_oneName_defaultAlphabet() throws Exception {

        var names = new String[]{

                "maksim"
        };

        var alphabet = new Alphabet();

        var actual = alphabet.solve(names);

        var expected = "abcdefghijklmnopqrstuvwxyz";

        assertEquals(expected, actual);
    }

    @Test
    void solve_impossibleComposeAlphabet_exception() {

        var names = new String[]{

                "maksim",
                "ivan",
                "ivi",
                "anna"
        };

        var alphabet = new Alphabet();

        var thrown = assertThrows(Exception.class, () -> alphabet.solve(names));

        assertNotNull(thrown.getMessage());
    }

    @Test
    void solve_threeNames_alphabet() throws Exception {

        var names = new String[]{

                "kolya",
                "kolina",
                "you"
        };

        var alphabet = new Alphabet();

        var actual = alphabet.solve(names);

        var expected = "zxwvutsrqponmlkyjihgfedcba";

        assertEquals(expected, actual);
    }

    @Test
    void solve_threeNames_exception() {

        var names = new String[]{

                "maks",
                "petya",
                "pety"
        };

        var alphabet = new Alphabet();

        var thrown = assertThrows(Exception.class, () -> alphabet.solve(names));

        assertNotNull(thrown.getMessage());
    }
}