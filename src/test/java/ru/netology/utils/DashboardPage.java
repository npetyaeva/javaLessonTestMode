package ru.netology.utils;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement header = $("h2");

    public DashboardPage() {
        header.shouldBe(visible);
    }
}
