package ui.pages;

import com.codeborne.selenide.SelenideElement;

import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationPage {
    private SelenideElement loginInput = $x("//input[@placeholder='Username']");
    private SelenideElement passwordInput = $x("//input[@placeholder='Password']");
    private SelenideElement loginButton = $x("//input[@type='submit']");

    public static final String LOGIN = "standard_user";
    public static final String PASSWORD = "secret_sauce";


    @When("^Авторизуемся на сайте saucedemo$")
    public void authorizeOnWebSite() {
        loginInput.sendKeys(LOGIN);
        passwordInput.sendKeys(PASSWORD);
        loginButton.click();
    }
}
