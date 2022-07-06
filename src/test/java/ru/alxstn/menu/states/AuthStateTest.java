package ru.alxstn.menu.states;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AuthStateTest {

    @ParameterizedTest
    @ValueSource(strings = { "jdoe@mail.net", "ane.doe@yahoo.com", "name@domain.com", "maryj@google.com",
            "jsmith@hotmail.com", "125367at@zzz90.z9", "1@1.1"})
    void validateCorrectEmail(String input) {
        Assertions.assertTrue(AuthState.validateEmail(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "email", "Hello world", "12345@.ru", "@google.com", "emailemail.xyz",
            "email@emailxyz", "email@e@mail.xyz" })
    void validateIncorrectEmail(String input) {
        Assertions.assertFalse(AuthState.validateEmail(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "John", "Anny", "Jean-Claude", "Mary", "Al", "Robert", "Ed", "na'me", "n'a", "nA" })
    void validateCorrectFirsName(String input) {
        Assertions.assertTrue(AuthState.validateName(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "n", "'name",  "-name", "name-", "name'", "nam-'e", "na'-me", "na--me", "na''me", "námé" })
    void validateIncorrectFirsName(String input) {
        Assertions.assertFalse(AuthState.validateName(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Smith", "Doolittle", "O'Connor", "Emelianenko", "Owen", "Jemison Van de Graaff", "Eden",
            "s-u", "me su aa-b'b", "me" })
    void validateCorrectLastName(String input) {
        Assertions.assertTrue(AuthState.validateName(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "s", "-surname", "'surname", "surnam''e", "surn--ame", "s'-urname", "su-'rname",
            "surname-", "surname'", "surnámé" })
    void validateIncorrectLastName(String input) {
        Assertions.assertFalse(AuthState.validateName(input));
    }

}