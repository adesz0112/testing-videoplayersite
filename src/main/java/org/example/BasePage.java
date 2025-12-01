package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private final int DEFAULT_WAIT = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT));
    }

    // ---- Locator Helpers ----

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    // ---- Wait Helpers ----

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected boolean waitForInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ---- Actions ----

    protected void click(By locator) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        // Extra small wait to ensure UI stabilizes
        sleep(200);

        // Click via Actions as a fallback if normal click fails
        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            new Actions(driver).moveToElement(element).click().perform();
        }
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForVisible(locator).getText();
    }

    protected void open(String url) {
        driver.get(url);
    }
}
