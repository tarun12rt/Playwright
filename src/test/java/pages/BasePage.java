package pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import config.Config;

public class BasePage {

    protected Page page;
    protected final int GLOBAL_TIMEOUT;

    public BasePage(Page page) {
        this.page = page;
        this.GLOBAL_TIMEOUT = Config.getInt("globalWait", 10000);

        // Apply globally to Playwright (BEST PRACTICE)
        page.setDefaultTimeout(GLOBAL_TIMEOUT);
        page.setDefaultNavigationTimeout(GLOBAL_TIMEOUT);
    }

    /* ================= WAIT UTILITIES ================= */

    protected void waitForVisible(Locator locator) {
        locator.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(GLOBAL_TIMEOUT)
        );
    }

    protected void waitForHidden(Locator locator) {
        locator.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.HIDDEN)
                        .setTimeout(GLOBAL_TIMEOUT)
        );
    }

    protected void waitForUrlContains(String partialUrl) {
        page.waitForURL(url -> url.contains(partialUrl));
    }

    /* ================= SAFE ACTIONS ================= */

    protected void safeClick(Locator locator) {
        locator.click();  // Playwright auto-waits
    }

    protected void clickIfVisible(Locator locator) {
        if (locator.isVisible()) {
            locator.click();
        }
    }

    protected void safeFill(Locator locator, String text) {
        locator.fill(text);  // auto-wait
    }

    protected void clearAndFill(Locator locator, String text) {
        locator.fill("");   // better than clear()
        locator.fill(text);
    }

    protected boolean safeIsVisible(Locator locator) {
        try {
            return locator.isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    /* ================= TEXT UTILITIES ================= */

    protected String getText(Locator locator) {
        return locator.textContent().trim();
    }

    protected boolean isTextPresent(String text) {
        return page.getByText(text).isVisible();
    }

    /* ================= SCROLL UTILITIES ================= */

    protected void scrollTo(Locator locator) {
        locator.scrollIntoViewIfNeeded();
    }

    protected void scrollToBottom() {
        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
    }

    /* ================= COMMON NAVIGATION ================= */

    protected void openBaseUrl() {
        page.navigate(Config.get("baseUrl"));
    }

    protected String getPageTitle() {
        return page.title();
    }

    protected String getCurrentUrl() {
        return page.url();
    }

    protected Locator byText(String text) {
        return page.getByText(text);
    }

    protected Locator byCss(String css) {
        return page.locator(css);
    }

    /* ================= TAB / WINDOW HANDLING ================= */

    protected Page clickAndSwitchToNewPage(Locator locator) {

        BrowserContext context = page.context();

        Page newPage = context.waitForPage(() -> locator.click());

        newPage.waitForLoadState();
        return newPage;
    }

    protected void restoreToOriginalPage(Page navigatedPage) {

        if (navigatedPage != page) {
            navigatedPage.close();
            page.bringToFront();
        } else {
            page.goBack();
        }
    }

    protected boolean isNewTabOpened(Page navigatedPage) {
        return navigatedPage != page;
    }

    protected Page getMainPage() {
        return page;
    }
}