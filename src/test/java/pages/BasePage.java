package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import config.Config;

public class BasePage {

    protected Page page;
    private static final int DEFAULT_TIMEOUT = 5000;

    public BasePage(Page page) {
        this.page = page;
    }

    /* ================= WAIT UTILITIES ================= */

    protected void waitForVisible(Locator locator) {
        locator.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(DEFAULT_TIMEOUT)
        );
    }

    protected void waitForHidden(Locator locator) {
        locator.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.HIDDEN)
                        .setTimeout(DEFAULT_TIMEOUT)
        );
    }

    /* ================= SAFE ACTIONS ================= */

    protected void safeClick(Locator locator) {
        waitForVisible(locator);
        locator.click();
    }

    protected void safeFill(Locator locator, String text) {
        waitForVisible(locator);
        locator.fill(text);
    }

    protected boolean safeIsVisible(Locator locator) {
        try {
            waitForVisible(locator);
            return locator.isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    protected String getPageTitle() {
        return page.title();
    }

    protected void openBaseUrl() {
        page.navigate(Config.get("baseUrl"));
    }

}
