package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.*;
import org.example.lesson17.CalculatorPage;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CalculatorTest {
    private AndroidDriver driver;
    private CalculatorPage calculator;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("R58R54YDV0Z")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.google.android.calculator")
                .setAppActivity("com.android.calculator2.Calculator")
                .setNoReset(true)
                .setNewCommandTimeout(Duration.ofSeconds(30))
                .setFullReset(false);

        try {
            URL appiumServerUrl = new URL("http://127.0.0.1:4723");
            driver = new AndroidDriver(appiumServerUrl, options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            calculator = new CalculatorPage(driver);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось инициализировать драйвер Appium", e);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            attachScreenshot();
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] attachScreenshot() {
        return driver.getScreenshotAs(OutputType.BYTES);
    }

    @DataProvider(name = "arithmeticOperations")
    public Object[][] arithmeticOperations() {
        return new Object[][]{
                {2, 3, "+", "5"},
                {5, 3, "-", "2"},
                {3, 4, "×", "12"},
                {10, 2, "÷", "5"}
        };
    }

    @Test(dataProvider = "arithmeticOperations", priority = 1)
    @Feature("Арифметические операции")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка базовых арифметических операций в калькуляторе")
    @Story("Основной функционал калькулятора")
    public void testCalculatorOperations(int num1, int num2, String operator, String expected) {
        Allure.step("Очистка калькулятора", calculator::clear);
        Allure.step("Ввод первого числа: " + num1, () -> calculator.inputNumber(String.valueOf(num1)));
        Allure.step("Выбор операции: " + operator, () -> calculator.pressOperator(operator));
        Allure.step("Ввод второго числа: " + num2, () -> calculator.inputNumber(String.valueOf(num2)));
        Allure.step("Нажатие кнопки 'равно'", calculator::pressEquals);

        String result = calculator.getResult();
        Allure.step("Проверка результата", () -> {
            Assert.assertEquals(result, expected,
                    String.format("Ожидаемый результат: %s, фактический: %s", expected, result));
        });
    }

    @Test(priority = 2)
    @Feature("Обработка ошибок")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка обработки деления на ноль")
    @Story("Крайние случаи")
    public void testDivisionByZero() {
        Allure.step("Подготовка теста деления на ноль", () -> {
            calculator.clear();
            calculator.inputNumber("10");
            calculator.pressOperator("÷");
            calculator.inputNumber("0");
            calculator.pressEquals();
        });

        String result = calculator.getResult();
        Allure.step("Проверка сообщения об ошибке", () -> {
            Assert.assertTrue(
                    result.equals("На 0 делить нельзя") || result.contains("Infinity") || result.equals("Ошибка"),
                    "Не получено ожидаемое сообщение об ошибке. Фактический результат: " + result
            );
        });
    }
}