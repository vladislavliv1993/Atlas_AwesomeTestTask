package ui.steps;

import com.codeborne.selenide.WebDriverRunner;

import io.cucumber.java.After;

public class AfterStep {

    @After("@webui")
    public void tearDown() {
        WebDriverRunner.getWebDriver().quit();
    }
}
