package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stash.StashesContainer;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage {

    private SelenideElement productName = $x("//div[contains(@class, 'inventory_details_name')]");
    private SelenideElement addTargetGoodButton = $x("//button[@name='add-to-cart-sauce-labs-bike-light']");
    private SelenideElement removeTargetGoodButton = $x("//button[@name='remove-sauce-labs-bike-light']");

    @And("^Проверяем, что страница товара загрузилась$")
    public void checkThatProductPageHasBeenOpened() {
        String lastOpenedProductName = StashesContainer.get("LAST_OPENED_PRODUCT_NAME").toString();
        productName.shouldHave(Condition.text(lastOpenedProductName));
    }

    @When("^Добавляем товар в корзину$")
    public void addGoodIntoBasket() {
        addTargetGoodButton.click();
    }

    @Then("^Проверяем, что текст кнопки товара изменился на Remove$")
    public void checkRemoveTextVisible() {
        removeTargetGoodButton.shouldBe(Condition.visible);
    }
}
