package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.utils.DataGenerator;
import ru.netology.utils.RegistrationPage;

public class RegistrationTest {
    private static final DataGenerator dataGenerator = new DataGenerator();
    private final RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    void setUp() {
        registrationPage.open("http://localhost:9999/");
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
