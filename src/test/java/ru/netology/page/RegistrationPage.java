package ru.netology.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.RegistrationInfo;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
    private final String currentUrl = "http://localhost:9999/";
    private final SelenideElement loginField = $("[name='login']");
    private final SelenideElement passwordField = $("[name='password']");
    private final SelenideElement button = $("[data-test-id='action-login']");
    private final SelenideElement emptyLogin = $("[data-test-id='login'] .input__sub");
    private final SelenideElement emptyPassword = $("[data-test-id='password'] .input__sub");
    private final SelenideElement errorLoginPassword = $("[data-test-id='error-notification'] .notification__content");

    public RegistrationPage() {
        Selenide.open(currentUrl);
        loginField.shouldBe(visible);
    }

    public DashboardPage registrationValid(RegistrationInfo registeredUser) {
        loginField.setValue(registeredUser.getLogin());
        passwordField.setValue(registeredUser.getPassword());
        button.click();
        return new DashboardPage();
    }

    public void registrationInvalid(RegistrationInfo registeredUser) {
        loginField.setValue(registeredUser.getLogin());
        passwordField.setValue(registeredUser.getPassword());
        button.click();
        errorLoginPassword.shouldBe(exactText("Ошибка! Пользователь заблокирован"));
    }

    public void loginInvalid(RegistrationInfo registrationUser) {
        loginField.setValue(registrationUser.getLogin() + "0");
        passwordField.setValue(registrationUser.getPassword());
        button.click();
        errorLoginPassword.shouldBe(exactText("Ошибка! Неверно указан логин или пароль"));
    }

    public void passwordInvalid(RegistrationInfo registrationUser) {
        loginField.setValue(registrationUser.getLogin());
        passwordField.setValue(registrationUser.getPassword() + "0");
        button.click();
        errorLoginPassword.shouldBe(exactText("Ошибка! Неверно указан логин или пароль"));
    }

    public void emptyFields() {
        button.click();
        emptyLogin.shouldBe(exactText("Поле обязательно для заполнения"));
    }

    public void emptyLogin(String password) {
        passwordField.setValue(password);
        button.click();
        emptyLogin.shouldBe(exactText("Поле обязательно для заполнения"));
    }

    public void emptyPassword(String login) {
        loginField.setValue(login);
        button.click();
        emptyPassword.shouldBe(exactText("Поле обязательно для заполнения"));
    }

}
