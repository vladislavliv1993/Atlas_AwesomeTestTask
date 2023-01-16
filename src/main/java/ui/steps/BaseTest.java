package ui.steps;

import com.codeborne.selenide.Selenide;

import io.cucumber.java.en.Given;

public class BaseTest {

    @Given("Открываем сайт {string}")
    public void openWebSite(String url) {
        Selenide.open(url);

    }
}
