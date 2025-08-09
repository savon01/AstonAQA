package lesson15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartPage {
    private WebDriver driver;

    private By productTitles = By.cssSelector(".good-info__good-name");
    private By productPrices = By.cssSelector(".list-item__price-new");
    private By productQuantities = By.cssSelector("input[type='number']");
    private By totalSum = By.cssSelector("span[data-link*='basketPriceWithCurrencyV2']");
    private By deliverySumLocator = By.cssSelector("span.b-right");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }


    public void checkProductNames(List<String> expectedNames) {
        List<String> actualNames = driver.findElements(productTitles)
                .stream().map(WebElement::getText).collect(Collectors.toList());

        boolean allMatch = true;
        StringBuilder errorMsg = new StringBuilder();

        for (String expected : expectedNames) {
            boolean foundMatch = actualNames.stream()
                    .anyMatch(actual -> actual.toLowerCase().contains(expected.toLowerCase())
                            || expected.toLowerCase().contains(actual.toLowerCase()));
            if (!foundMatch) {
                allMatch = false;
                errorMsg.append("\nНе найден товар, похожий на: ").append(expected);
            }
        }

        assertTrue(allMatch,
                "Состав товаров в корзине не совпадает.\nОжидалось (частичное совпадение): "
                        + expectedNames + "\nФактически: " + actualNames + errorMsg);
    }

    public void checkProductPricesArePositive() {
        List<String> prices = driver.findElements(productPrices)
                .stream().map(WebElement::getText).collect(Collectors.toList());
        for (String price : prices) {
            double value = Double.parseDouble(price.replaceAll("[^0-9]", ""));
            assertTrue(value > 0, "Цена товара должна быть > 0");
        }
    }


    public void checkProductQuantities() {
        List<Integer> quantities = driver.findElements(productQuantities)
                .stream()
                .map(e -> Integer.parseInt(e.getAttribute("value").trim()))
                .collect(Collectors.toList());

        for (int q : quantities) {
            assertTrue(q >= 1, "Количество товара должно быть >= 1, но было: " + q);
        }
    }


    public void checkTotalSumMatchesSumOfItems() {
        // Считаем сумму товаров: цена × количество
        List<Integer> prices = driver.findElements(productPrices)
                .stream()
                .map(e -> Integer.parseInt(e.getText().replaceAll("[^0-9]", "")))
                .collect(Collectors.toList());

        List<Integer> quantities = driver.findElements(productQuantities)
                .stream()
                .map(e -> Integer.parseInt(e.getAttribute("value").trim()))
                .collect(Collectors.toList());

        int sumOfItems = 0;
        for (int i = 0; i < prices.size(); i++) {
            sumOfItems += prices.get(i) * quantities.get(i);
        }

        // Общая сумма корзины
        int totalDisplayed = Integer.parseInt(
                driver.findElement(totalSum).getText().replaceAll("[^0-9]", "")
        );

        // Сумма доставки
        int deliverySum = 0;
        List<WebElement> deliveryElements = driver.findElements(deliverySumLocator);
        if (deliveryElements.size() >= 3) {
            deliverySum = Integer.parseInt(
                    deliveryElements.get(2).getText().replaceAll("[^0-9]", "")
            );
        }

        // Вычитаем доставку и сравниваем
        assertEquals(totalDisplayed - deliverySum, sumOfItems,
                "Общая сумма товаров (без доставки) не совпадает с суммой, рассчитанной по товарам");
    }
}
