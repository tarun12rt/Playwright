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

    protected Locator byText(String text) {
        return page.getByText(text);
    }

    protected Locator byCss(String css) {
        return page.locator(css);
    }

    protected void switchToNewTab() {
        Page newPage = page.context().waitForPage(() -> {
            // action that opens new tab must be triggered before calling this
        });
        newPage.waitForLoadState();
    }

    protected void closeCurrentTabAndSwitchToMain(Page currentPage) {

        // If current page is NOT the main page → close it
        if (currentPage != page) {
            currentPage.close();
        }

        // Ensure main page is active
        page.bringToFront();
        page.waitForLoadState();
    }

    protected Page clickAndSwitchToNewPage(Locator locator) {

        Page originalPage = page;
        BrowserContext context = page.context();

        try {
            // detect new tab/window
            Page newPage = context.waitForPage(() -> locator.click());

//            newPage.waitForLoadState();
            System.out.println("✅ New tab/window opened. Switched to new page.");
            return newPage;

        } catch (Exception e) {
            // Same tab navigation
            locator.click();
            page.waitForLoadState();
            System.out.println("ℹ️ Clicked element and remained on the same page.");
            return originalPage;
        }
    }

    protected void restoreToOriginalPage(Page navigatedPage) {

        // New tab / window
        if (navigatedPage != page) {
            navigatedPage.close();
            page.bringToFront();
            page.waitForLoadState();
        }
        // Same tab navigation
        else {
            page.goBack();
            page.waitForLoadState();
        }
    }
public static void click(Locator locator) {
    locator.waitFor();
    locator.click();
}
    public static void type(Locator locator, String text) {
    locator.waitFor();
    locator.fill(text);
}

public static String getText(Locator locator) {
    locator.waitFor();
    return locator.textContent().trim();
}

    public static void waitForVisible(Locator locator) {
    locator.waitFor(new Locator.WaitForOptions()
            .setState(WaitForSelectorState.VISIBLE));
}

    public static Page switchToNewPage(Page page, Runnable action) {
    BrowserContext context = page.context();
    Page newPage = context.waitForPage(action);
    newPage.waitForLoadState();
    return newPage;
}

    public static void selectByValue(Locator locator, String value) {
    locator.selectOption(value);
}
public static int getElementCount(Page page, String selector) {
    return page.locator(selector).count();
}
    
public static void jsClick(Page page, String selector) {
    page.evaluate("document.querySelector(arguments[0]).click()", selector);
}
  public static void retryClick(Locator locator, int attempts) {
    for (int i = 0; i < attempts; i++) {
        try {
            locator.click();
            break;
        } catch (Exception e) {
            if (i == attempts - 1) throw e;
        }
    }
}

    public static void assertText(Locator locator, String expected) {
    String actual = locator.textContent().trim();
    Assertions.assertEquals(expected, actual);
}

    public static void takeScreenshot(Page page, String name) {
    page.screenshot(new Page.ScreenshotOptions()
            .setPath(Paths.get("screenshots/" + name + ".png"))
            .setFullPage(true));
}

    public static void scrollIntoView(Locator locator) {
    locator.scrollIntoViewIfNeeded();
}

    public static void selectByValue(Locator locator, String value) {
    locator.selectOption(value);
}

    Page childPage = WindowUtils.switchToNewPage(page, () -> {
    page.click("#openTab");
});

    public static Page switchToNewPage(Page page, Runnable action) {
    BrowserContext context = page.context();
    Page newPage = context.waitForPage(action);
    newPage.waitForLoadState();
    return newPage;
}
}
