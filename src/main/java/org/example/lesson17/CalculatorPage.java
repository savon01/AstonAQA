package org.example.lesson17;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class CalculatorPage {
    private final AndroidDriver driver;

    public CalculatorPage(AndroidDriver driver) {
        this.driver = driver;
    }

    @Step("Clear calculator")
    public void clear() {
        try {
            driver.findElement(AppiumBy.id("com.google.android.calculator:id/clr")).click();
        } catch (Exception e) {
            // Если кнопка очистки не найдена, просто продолжаем
        }
    }

    @Step("Input number: {number}")
    public void inputNumber(String number) {
        for (char digit : number.toCharArray()) {
            String digitId = "com.google.android.calculator:id/digit_" + digit;
            driver.findElement(AppiumBy.id(digitId)).click();
        }
    }

    @Step("Press operator: {operator}")
    public void pressOperator(String operator) {
        String operatorId;
        switch (operator) {
            case "+":
                operatorId = "op_add";
                break;
            case "-":
                operatorId = "op_sub";
                break;
            case "×":
                operatorId = "op_mul";
                break;
            case "/":
            case "÷":
                operatorId = "op_div";
                break;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/" + operatorId)).click();
    }

    @Step("Press equals button")
    public void pressEquals() {
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/eq")).click();
    }

    @Step("Get calculation result")
    public String getResult() {
        try {
            WebElement resultElement = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));
            return resultElement.getText();
        } catch (Exception e) {
            WebElement resultPreview = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_preview"));
            return resultPreview.getText();
        }
    }
}
