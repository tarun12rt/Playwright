package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage extends BasePage {

    // --- Locators ---
    private final Locator emailInput;



    public HomePage(Page page) {
        super(page);

        emailInput = page.locator("#username");

    }

    public void open() {
        openBaseUrl();
    }
}
