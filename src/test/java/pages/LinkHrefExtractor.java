package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;

import java.util.*;
import java.util.stream.Collectors;

public class LinkHrefExtractor {

    private static final int TIMEOUT = 10000;

    public List<String> extractLinks(String startUrl) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium()
                                    .launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        List<String> links = new ArrayList<>();

        try {
            page.navigate(
                    startUrl,
                    new Page.NavigateOptions()
                            .setTimeout(TIMEOUT)
                            .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
            );

            links = page.locator("a[href]").all()
                        .stream()
                        .map(locator -> locator.getAttribute("href"))
                        .filter(Objects::nonNull)
                        .map(href -> normalizeUrl(startUrl, href))
                        .filter(Objects::nonNull)
                        .distinct()
                        .collect(Collectors.toList());

        } finally {
            page.close();
            browser.close();
            playwright.close();
        }

        return links;
    }

    private String normalizeUrl(String baseUrl, String href) {

        if (href.startsWith("http")) {
            return href;
        }

        if (href.startsWith("/")) {
            return baseUrl.endsWith("/")
                    ? baseUrl.substring(0, baseUrl.length() - 1) + href
                    : baseUrl + href;
        }

        return null;
    }
}
