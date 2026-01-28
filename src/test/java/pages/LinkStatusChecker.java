package pages;

import com.microsoft.playwright.*;

import java.util.List;

public class LinkStatusChecker {

    private final Playwright playwright;
    private final Browser browser;
    private final Page page;
    private final APIRequestContext apiRequest;

    public LinkStatusChecker() {
        playwright = Playwright.create();
        browser = playwright.chromium()
                            .launch(new BrowserType.LaunchOptions().setHeadless(false));

        page = browser.newPage();
        apiRequest = playwright.request().newContext();
    }

    // ===================== CHECK LINKS =====================
    public void checkLinks(List<String> links) {

        for (String link : links) {
            System.out.println("\nNavigated to: " + link);

            try {
                // Navigate in browser (visual confirmation)
                page.navigate(link);

                // Check HTTP status using API
                APIResponse response = apiRequest.get(link);
                int status = response.status();

                if (status >= 400) {
                    System.out.println("Status: BROKEN (" + status + ")");
                } else {
                    System.out.println("Status: OK");
                }

            } catch (Exception e) {
                System.out.println("Status: ERROR");
            }
        }
    }

    // ===================== CLEANUP =====================
    public void close() {
        apiRequest.dispose();
        page.close();
        browser.close();
        playwright.close();
    }
}
