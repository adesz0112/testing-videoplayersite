package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class HomePageTest extends BaseTest{


    @Test

    void openYoutubeHomePage() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();

        Assertions.assertTrue(driver.getTitle().toLowerCase().contains("youtube"),
                "YouTube home page did not open!");
    }

    @Test

    void searchYoutubeVideoPage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.acceptCookiesIfPresent();
        homePage.search("dua lipa");
        homePage.clickSearchButton();
        Assertions.assertTrue(
                driver.getCurrentUrl().contains("search_query=dua+lipa"),
                "URL is incorrect after searching!"
        );

    }
}
