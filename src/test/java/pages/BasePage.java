package pages;

import com.microsoft.playwright.BrowserContext;
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

    protected void waitForUrlContains(String partialUrl) {
        page.waitForURL(url -> url.contains(partialUrl));
    }

    /* ================= SAFE ACTIONS ================= */

    protected void safeClick(Locator locator) {
        waitForVisible(locator);
        locator.click();
    }

    protected void clickIfVisible(Locator locator) {
        if (locator.isVisible()) {
            locator.click();
        }
    }

    protected void safeFill(Locator locator, String text) {
        waitForVisible(locator);
        locator.fill(text);
    }

    protected void clearAndFill(Locator locator, String text) {
        waitForVisible(locator);
        locator.clear();
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

    /* ================= TEXT UTILITIES ================= */

    protected String getText(Locator locator) {
        waitForVisible(locator);
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
        int pagesBefore = context.pages().size();

        locator.click();

        // Wait briefly for new tab
        page.waitForTimeout(500);

        if (context.pages().size() > pagesBefore) {
            Page newPage = context.pages().get(context.pages().size() - 1);
            newPage.waitForLoadState();
            System.out.println("✅ New tab/window opened");
            return newPage;
        }

        page.waitForLoadState();
        System.out.println("ℹ️ Stayed on same page");
        return page;
    }

    protected void restoreToOriginalPage(Page navigatedPage) {

        if (navigatedPage != page) {
            navigatedPage.close();
            page.bringToFront();
            page.waitForLoadState();
        } else {
            page.goBack();
            page.waitForLoadState();
        }
    }

    protected boolean isNewTabOpened(Page navigatedPage) {
        return navigatedPage != page;
    }

    protected Page getMainPage() {
        return page;
    }
}
