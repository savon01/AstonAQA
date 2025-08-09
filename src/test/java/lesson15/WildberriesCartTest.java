package lesson15;

import org.testng.annotations.Test;

import java.util.List;

public class WildberriesCartTest extends BaseTest {

    @Test
    public void addProductsAndCheckCart() {
        driver.get("https://www.wildberries.ru/");

        MainPage mainPage = new MainPage(driver);

        mainPage.acceptCookiesIfPresent();

        List<String> expectedNames = mainPage.addFirstNProductsToCart(3);

        driver.get("https://www.wildberries.ru/lk/basket");

        CartPage cartPage = new CartPage(driver);

        cartPage.checkProductNames(expectedNames);

        cartPage.checkProductPricesArePositive();

        cartPage.checkTotalSumMatchesSumOfItems();
    }
}
