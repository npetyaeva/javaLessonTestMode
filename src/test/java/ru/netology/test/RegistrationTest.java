package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String actual = registrationPage.registrationInvalid(registeredUser);
        assertEquals("Ошибка! Пользователь заблокирован", actual);
    }

    @Test
    void shouldCheckRegistrationWithInvalidLogin() {
        var registeredUser = DataGenerator.getRegisteredUserInvalidLogin("active");
        String actual = registrationPage.loginOrPasswordInvalid(registeredUser);
        assertEquals("Ошибка! Неверно указан логин или пароль", actual);
    }

    @Test
    void shouldCheckRegistrationWithInvalidPassword() {
        var registeredUser = DataGenerator.getRegisteredUserInvalidPassword("active");
        String actual = registrationPage.loginOrPasswordInvalid(registeredUser);
        assertEquals("Ошибка! Неверно указан логин или пароль", actual);
    }

    @Test
    void shouldCheckSendEmptyForm() {
        String actual = registrationPage.emptyFields();
        assertEquals("Поле обязательно для заполнения", actual);
    }

    @Test
    void shouldCheckSendEmptyLogin() {
        var onlyPassword = DataGenerator.getRandomPassword();
        String actual = registrationPage.emptyLogin(onlyPassword);
        assertEquals("Поле обязательно для заполнения", actual);
    }

    @Test
    void shouldCheckSendEmptyPassword() {
        var onlyLogin = DataGenerator.getRandomLogin();
        String actual = registrationPage.emptyPassword(onlyLogin);
        assertEquals("Поле обязательно для заполнения", actual);
    }
}
