package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.RegistrationPage;

public class RegistrationTest {
    private static final DataGenerator dataGenerator = new DataGenerator();
    private RegistrationPage registrationPage;

    @BeforeEach
    void setUp() {
        registrationPage = new RegistrationPage();
    }

    @Test
    void shouldCheckRegistrationActiveUser() {
        var registeredUser = dataGenerator.getRegisteredUser("active");
        registrationPage.registrationValid(registeredUser);
    }

    @Test
    void shouldCheckRegistrationBlockedUser() {
        var registeredUser = dataGenerator.getRegisteredUser("blocked");
        registrationPage.registrationInvalid(registeredUser);
    }

    @Test
    void shouldCheckRegistrationWithInvalidLogin() {
        var registeredUser = dataGenerator.getRegisteredUser("active");
        registrationPage.loginInvalid(registeredUser);
    }

    @Test
    void shouldCheckRegistrationWithInvalidPassword() {
        var registeredUser = dataGenerator.getRegisteredUser("active");
        registrationPage.passwordInvalid(registeredUser);
    }

    @Test
    void shouldCheckSendEmptyForm() {
        registrationPage.emptyFields();
    }

    @Test
    void shouldCheckSendEmptyLogin() {
        var onlyPassword = dataGenerator.getRandomPassword();
        registrationPage.emptyLogin(onlyPassword);
    }

    @Test
    void shouldCheckSendEmptyPassword() {
        var onlyLogin = dataGenerator.getRandomLogin();
        registrationPage.emptyPassword(onlyLogin);
    }
}
