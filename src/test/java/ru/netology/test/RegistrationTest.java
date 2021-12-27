package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationInfo;
import ru.netology.page.RegistrationPage;

public class RegistrationTest {
    private RegistrationPage registrationPage;

    @BeforeEach
    void setUp() {
        registrationPage = new RegistrationPage();
    }

    @Test
    void shouldCheckRegistrationActiveUser() {
        var registeredUser = DataGenerator.getRegisteredUser("active");
        registrationPage.registrationValid(registeredUser);
    }

    @Test
    void shouldCheckRegistrationBlockedUser() {
        var registeredUser = DataGenerator.getRegisteredUser("blocked");
        registrationPage.registrationInvalid(registeredUser);
    }

    @Test
    void shouldCheckRegistrationWithInvalidLogin() {
        var registeredUser = DataGenerator.getRegisteredUser("active");
        registrationPage.loginInvalid(registeredUser);
    }

    @Test
    void shouldCheckRegistrationWithInvalidPassword() {
        var registeredUser = DataGenerator.getRegisteredUser("active");
        registrationPage.passwordInvalid(registeredUser);
    }

    @Test
    void shouldCheckSendEmptyForm() {
        registrationPage.emptyFields();
    }

    @Test
    void shouldCheckSendEmptyLogin() {
        var onlyPassword = DataGenerator.getRandomPassword();
        registrationPage.emptyLogin(onlyPassword);
    }

    @Test
    void shouldCheckSendEmptyPassword() {
        var onlyLogin = DataGenerator.getRandomLogin();
        registrationPage.emptyPassword(onlyLogin);
    }
}
