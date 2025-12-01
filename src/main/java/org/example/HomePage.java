package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    private static final String URL = "https://www.youtube.com";
    private By searchInput = By.name("search_query");
    private By searchButton = By.xpath("//button[@aria-label='Search' and contains(@class,'ytSearchboxComponentSearchButton')]");
    private By cookieAcceptButton =  By.xpath("//button[.//span[contains(text(),'elfogad') or contains(text(),'accept')]]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookiesIfPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Modal iframe (YouTube sometimes uses one)
            driver.switchTo().frame("iframe");

        } catch (Exception ignored) {}

        try {
            WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", acceptBtn);
            acceptBtn.click();

        } catch (TimeoutException e) {
            System.out.println("No cookie popup detected.");
        }

        // Return to main document
        driver.switchTo().defaultContent();
    }

    public void openHomePage() {
        open(URL);

    }

    public void clickSearchButton() {
        WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        acceptBtn.click();
    }

    public void search(String query) {
        type(searchInput, query);
    }
}
