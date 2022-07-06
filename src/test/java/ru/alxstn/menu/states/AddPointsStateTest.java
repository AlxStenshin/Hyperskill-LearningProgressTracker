package ru.alxstn.menu.states;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AddPointsStateTest {

    @Test
    void validatePointsInputString() {
    }

    @ParameterizedTest
    @ValueSource(strings = { "abc", "abc abc", "123", "123 123"})
    void validateCorrectPointsInputString(String input) {
        Assertions.assertTrue(FindState.validateInputString(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "",  " ", "   ", "\t", "\n"})
    void validateIncorrectPointsInputString(String input) {
        Assertions.assertFalse(FindState.validateInputString(input));
    }

    @Test
    void getPointsFromString() {
    }

    @Test
    void getIdFromString() {
    }

    @Test
    void validatePoints() {
    }
}