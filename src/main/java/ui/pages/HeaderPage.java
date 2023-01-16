package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import io.cucumber.java.en.And;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderPage {

    private SelenideElement basketButton = $x("//a[@class='shopping_cart_link']");

    private static final String BASKET_PRODUCTS_COUNTER = ".//span[@class='shopping_cart_badge']";

    @And("Проверяем, что счетчик числа товаров в корзине равен {int}")
    public void checkBasketCounterIncremented(int productsQuantity) {
        if (productsQuantity < 0) {
            throw new IllegalArgumentException("Количество товаров в корзине не может быть меньше нуля");
        }

        if (productsQuantity == 0) {
            basketButton.$x(BASKET_PRODUCTS_COUNTER).shouldNotBe(Condition.visible);
        } else {
            basketButton.$x(BASKET_PRODUCTS_COUNTER)
                    .shouldHave(
                            Condition.text(String.valueOf(productsQuantity))
                    );
        }


    }
}
