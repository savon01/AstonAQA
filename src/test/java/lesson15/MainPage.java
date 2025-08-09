package lesson15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainPage {
    private WebDriver driver;

    // Локатор баннера с cookies
    private By cookiesAcceptButton = By.cssSelector(".cookies__btn");

    // Локаторы для карточек товаров
    private By productTitles = By.cssSelector(".product-card__name");
    private By addToCartButtons = By.cssSelector(".product-card__add-basket");

    private By sizeListItems = By.cssSelector(".sizes-list__item");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookiesIfPresent() {
        List<WebElement> cookiesPopup = driver.findElements(cookiesAcceptButton);
        if (!cookiesPopup.isEmpty()) {
            cookiesPopup.get(0).click();
        }
    }

    public List<String> addFirstNProductsToCart(int count) {
        List<WebElement> titles = driver.findElements(productTitles);
        List<WebElement> buttons = driver.findElements(addToCartButtons);

        List<String> addedProductNames = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = titles.get(i).getText();
            addedProductNames.add(name);
            buttons.get(i).click();

            // Проверяем, появилось ли окно с размерами
            List<WebElement> sizes = driver.findElements(sizeListItems);
            if (!sizes.isEmpty()) {
                sizes.get(0).click(); // Кликаем первый доступный размер
            }
        }
        return addedProductNames;
    }
}
