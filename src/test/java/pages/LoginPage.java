package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    private final Locator sample;

    public LoginPage(Page page) {
        super(page); // ✅ VERY IMPORTANT
        this.sample = page.getByText("sample");
    }

    public void open() {
        openBaseUrl();
    }

    public boolean clickProfileIconAndVerifyLoginPage() {
        return true;
    }
}
