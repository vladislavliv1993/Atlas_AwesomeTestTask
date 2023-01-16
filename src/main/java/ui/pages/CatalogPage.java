package ui.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stash.StashesContainer;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CatalogPage {

    private ElementsCollection products = $$x("//div[@class='inventory_item']");

    private SelenideElement mainPageInventoryList = $x("//div[@class='inventory_list']");
    private SelenideElement productSorterButton = $x("//select[@class='product_sort_container']");
    private SelenideElement sortDescButton = $x("//option[@value='za']");

    private static final String PRODUCT_NAME = ".//div[@class='inventory_item_name']";

    @Then("^Проверяем, что главная страница загрузилась$")
    public void checkMainPageLoaded() {
        mainPageInventoryList.shouldBe(Condition.visible);
    }

    @When("^Сортируем товары в порядке от Z до A$")
    public void sortProductsReversedOrder() {
        productSorterButton.click();
        sortDescButton.click();
    }

    @And("^Выбираем товар в левом нижнем углу и проверяем, что страница с товаром открывается$")
    public void chooseLeftBottomCornerItem() {
        products.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1));

        SelenideElement product;
        if (products.size() % 2 == 0) {
            product = products.get(products.size() - 2);
        } else {
            product = products.last();
        }
        SelenideElement productName = product.$x(PRODUCT_NAME);
        String productNameText = productName.getText();
        productName.click();
        StashesContainer.put("LAST_OPENED_PRODUCT_NAME", productNameText);
        productName.shouldNotBe(Condition.visible);
    }
}
