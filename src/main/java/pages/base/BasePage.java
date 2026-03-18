package pages.base;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
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

        handleAdPopUp();
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

    /* ================= COMMON NAVIGATION CLICK ================= */

    protected void clickAndWaitForPageLoad(Locator locator) {

        locator.scrollIntoViewIfNeeded();

        page.waitForNavigation(() -> {
            locator.click();
        });

        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    /* ================= COMMON VISIBILITY CHECK ================= */

    protected boolean waitUntilVisible(Locator locator) {
        try {
            locator.waitFor(
                    new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void handleAdPopUp() {
        try {
            FrameLocator adFrame = page.frameLocator(
                    "iframe[title='3rd party ad content'][width='1']"
            );

            Locator closeButton = adFrame.locator("button:has-text('Close Ad')");

            if (closeButton.count() > 0) {
                closeButton.first().click();
            }

        } catch (Exception e) {
            System.out.println("Ad popup not present.");
        }
    }

    /* ================= ATTRIBUTE UTILITIES ================= */

    protected String getAttribute(Locator locator, String attributeName) {
        return locator.getAttribute(attributeName);
    }

    protected boolean hasAttribute(Locator locator, String attributeName) {
        return locator.getAttribute(attributeName) != null;
    }

    protected String getPlaceholder(Locator locator) {
        return locator.getAttribute("placeholder");
    }

    protected String getInputValue(Locator locator) {
        return locator.inputValue();
    }

    /* ================= ELEMENT STATE CHECKS ================= */

    protected boolean isElementEnabled(Locator locator) {
        try {
            return locator.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementDisabled(Locator locator) {
        return !isElementEnabled(locator);
    }

    protected boolean isElementChecked(Locator locator) {
        try {
            return locator.isChecked();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean doesElementExist(Locator locator) {
        try {
            return locator.count() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    protected int getElementCount(Locator locator) {
        return locator.count();
    }

    /* ================= KEYBOARD INTERACTIONS ================= */

    protected void pressKey(String key) {
        page.keyboard().press(key);
    }

    protected void typeText(String text) {
        page.keyboard().type(text);
    }

    protected void typeTextWithDelay(Locator locator, String text, int delayMs) {
        locator.fill(text, new Locator.FillOptions().setNoWaitAfter(false));
    }

    protected void pressTab() {
        page.keyboard().press("Tab");
    }

    protected void pressEnter() {
        page.keyboard().press("Enter");
    }

    protected void pressEscape() {
        page.keyboard().press("Escape");
    }

    protected void selectAll() {
        page.keyboard().press("Control+A");
    }

    /* ================= MOUSE INTERACTIONS ================= */

    protected void hoverOver(Locator locator) {
        locator.hover();
    }

    protected void doubleClick(Locator locator) {
        locator.dblclick();
    }

    protected void rightClick(Locator locator) {
        locator.click(new Locator.ClickOptions().setButton(com.microsoft.playwright.options.MouseButton.RIGHT));
    }

    protected void clickAtCoordinates(int x, int y) {
        page.mouse().click(x, y);
    }

    protected void dragAndDrop(Locator sourceLocator, Locator targetLocator) {
        sourceLocator.dragTo(targetLocator);
    }

    /* ================= MULTIPLE ELEMENT OPERATIONS ================= */

    protected int countElements(String selector) {
        return page.locator(selector).count();
    }

    protected String getFirstElementText(String selector) {
        return page.locator(selector).first().textContent().trim();
    }

    protected String getLastElementText(String selector) {
        return page.locator(selector).last().textContent().trim();
    }

    protected String getNthElementText(String selector, int index) {
        return page.locator(selector).nth(index).textContent().trim();
    }

    protected java.util.List<String> getAllElementsText(String selector) {
        java.util.List<String> textList = new java.util.ArrayList<>();
        Locator locator = page.locator(selector);
        for (int i = 0; i < locator.count(); i++) {
            String text = locator.nth(i).textContent().trim();
            if (!text.isEmpty()) {
                textList.add(text);
            }
        }
        return textList;
    }

    /* ================= DROPDOWN/SELECT UTILITIES ================= */

    protected void selectByText(Locator selectLocator, String text) {
        selectLocator.selectOption(text);
    }

    protected void selectByValue(Locator selectLocator, String value) {
        selectLocator.selectOption(value);
    }

    protected String getSelectedOptionText(Locator selectLocator) {
        return selectLocator.locator("option[selected]").textContent().trim();
    }

    /* ================= PAGE STATE MANAGEMENT ================= */

    protected void reloadPage() {
        page.reload();
    }

    protected void goBack() {
        page.goBack();
    }

    protected void goForward() {
        page.goForward();
    }

    protected void waitForPageReady() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    protected void waitForDOMReady() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    protected void clearAllCookies() {
        page.context().clearCookies();
    }

    protected void clearBrowserCache() {
        page.evaluate("() => { sessionStorage.clear(); localStorage.clear(); }");
    }

    /* ================= JAVASCRIPT EXECUTION ================= */

    protected Object executeJavaScript(String script) {
        return page.evaluate(script);
    }

    protected Object executeJavaScriptWithArg(String script, Object arg) {
        return page.evaluate(script, arg);
    }

    protected boolean isElementInViewport(Locator locator) {
        return (boolean) page.evaluate(
                "element => element.getBoundingClientRect().top >= 0 && element.getBoundingClientRect().bottom <= window.innerHeight",
                locator.elementHandle()
        );
    }

    /* ================= ALERT HANDLING ================= */

    protected void acceptAlert() {
        page.onceDialog(dialog -> dialog.accept());
    }

    protected void dismissAlert() {
        page.onceDialog(dialog -> dialog.dismiss());
    }

    protected String getAlertText() {
        String[] alertText = new String[1];
        page.onceDialog(dialog -> {
            alertText[0] = dialog.message();
            dialog.accept();
        });
        return alertText[0];
    }

    protected void acceptAlertWithMessage(String message) {
        page.onceDialog(dialog -> {
            if (dialog.message().contains(message)) {
                dialog.accept();
            }
        });
    }

    /* ================= WAIT CONDITIONS ================= */

    protected void waitForElement(String selector) {
        page.waitForSelector(selector);
    }

    protected void waitForElementAndClick(String selector) {
        page.waitForSelector(selector);
        page.click(selector);
    }

    protected void waitForFunction(String function, int timeout) {
        page.waitForFunction(function, new Page.WaitForFunctionOptions().setTimeout(timeout));
    }

    /* ================= SCREENSHOT & DEBUG ================= */

    protected byte[] takeScreenshot() {
        return page.screenshot();
    }

    protected void takeScreenshotToFile(String filePath) {
        page.screenshot(new Page.ScreenshotOptions().setPath(new java.io.File(filePath).toPath()));
    }

    protected void highlightElement(Locator locator) {
        page.evaluate(
                "element => element.style.border = '3px solid red'",
                locator.elementHandle()
        );
    }

    /* ================= CHECKBOX & RADIO UTILITIES ================= */

    protected void checkCheckbox(Locator locator) {
        if (!locator.isChecked()) {
            locator.check();
        }
    }

    protected void uncheckCheckbox(Locator locator) {
        if (locator.isChecked()) {
            locator.uncheck();
        }
    }

    protected void selectRadioButton(Locator locator) {
        locator.check();
    }

    /* ================= LOCATOR BUILDERS ================= */

    protected Locator byXPath(String xpath) {
        return page.locator("xpath=" + xpath);
    }

    protected Locator byPlaceholder(String placeholder) {
        return page.getByPlaceholder(placeholder);
    }

    protected Locator byRole(String role) {
        return page.getByRole(com.microsoft.playwright.options.AriaRole.valueOf(role.toUpperCase()));
    }

    protected Locator byTestId(String testId) {
        return page.getByTestId(testId);
    }

    /* ================= FILE OPERATIONS ================= */

    protected void uploadFile(Locator locator, String filePath) {
        locator.setInputFiles(new java.io.File(filePath).toPath());
    }

    protected void uploadMultipleFiles(Locator locator, String[] filePaths) {
        java.nio.file.Path[] paths = new java.nio.file.Path[filePaths.length];
        for (int i = 0; i < filePaths.length; i++) {
            paths[i] = new java.io.File(filePaths[i]).toPath();
        }
        locator.setInputFiles(paths);
    }
}


