package ru.alxstn.menu.states;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FindStateTest {

    @ParameterizedTest
    @ValueSource(strings = { "abc", "abc abc", "123", "123 123"})
    void validateCorrectInput(String input) {
        assertTrue(FindState.validateInputString(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "",  " ", "   ", "\t", "\n"})
    void validateIncorrectInput(String input) {
        assertFalse(FindState.validateInputString(input));
    }

}