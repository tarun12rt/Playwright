package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FooterPage extends BasePage {

    private final Locator onlineLinksPolicy;

    public FooterPage(Page page) {
        super(page);
        this.onlineLinksPolicy = page.getByText("Online Links Policy");
    }

    public void scrollToFooter() {
        onlineLinksPolicy.scrollIntoViewIfNeeded();
    }

    public boolean isFooterVisible() {
        return safeIsVisible(onlineLinksPolicy);
    }

    public void open() {
        openBaseUrl();
    }

    public boolean verifyFooterIsVisible() {
        scrollToFooter();
        return isFooterVisible();
    }

}
