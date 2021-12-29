package ru.netology.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.RegistrationInfo;

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

    private void login(RegistrationInfo registeredUser) {
        loginField.setValue(registeredUser.getLogin());
        passwordField.setValue(registeredUser.getPassword());
        button.click();
    }

    public DashboardPage registrationValid(RegistrationInfo registeredUser) {
        login(registeredUser);
        return new DashboardPage();
    }

    public String registrationInvalid(RegistrationInfo registeredUser) {
        login(registeredUser);
        errorLoginPassword.shouldBe(visible);
        return errorLoginPassword.text();
    }

    public String loginOrPasswordInvalid(RegistrationInfo registeredUser) {
        login(registeredUser);
        errorLoginPassword.shouldBe(visible);
        return errorLoginPassword.text();
    }

    public String emptyFields() {
        button.click();
        emptyLogin.shouldBe(visible);
        return emptyLogin.text();
    }

    public String emptyLogin(String password) {
        passwordField.setValue(password);
        button.click();
        emptyLogin.shouldBe(visible);
        return emptyLogin.text();
    }

    public String emptyPassword(String login) {
        loginField.setValue(login);
        button.click();
        emptyPassword.shouldBe(visible);
        return emptyPassword.text();
    }

}
