package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final String URL = "https://www.youtube.com";
    private By searchInput = By.name("search_query");
    private By searchButton = By.id("search-icon-legacy");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        open(URL);
    }

    public void clickSearchButton() {
        click(searchButton);
    }

    public void search(String query) {
        type(searchInput, query);
        clickSearchButton();
    }
}
