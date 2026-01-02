package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FooterPage extends BasePage {

    private final Locator onlineLinksPolicy;
    private final Locator about8Days;

    public FooterPage(Page page) {
        super(page);
        this.onlineLinksPolicy = page.getByText("Online Links Policy");
        this.about8Days = page.getByText("About 8days");

    }


    public boolean isFooterVisible() {
        return safeIsVisible(onlineLinksPolicy);
    }

    public void open() {
        openBaseUrl();
    }

    public boolean verifyFooterIsVisible() {
        scrollTo(onlineLinksPolicy);
        return isFooterVisible();
    }

}
