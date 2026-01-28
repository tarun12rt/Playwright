package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.util.*;
import java.util.stream.Collectors;

public class LinkCrawlerValidator {

    private static final int TIMEOUT = 10000;

    private final Playwright playwright;
    private final Browser browser;
    private final Page page;
    private final APIRequestContext apiRequest;

    private final Set<String> visitedLinks = new HashSet<>();
    private final Queue<String> linksToVisit = new LinkedList<>();

    private final String baseUrl;
    private final String baseDomain;

    private int totalLinksScanned = 0;
    private int brokenLinksCount = 0;

    // ===================== CONSTRUCTOR =====================
    public LinkCrawlerValidator(String startUrl) {
        playwright = Playwright.create();

        browser = playwright.chromium()
                            .launch(new BrowserType.LaunchOptions().setHeadless(false));

        page = browser.newPage();
        apiRequest = playwright.request().newContext();

        baseUrl = startUrl.endsWith("/")
                ? startUrl.substring(0, startUrl.length() - 1)
                : startUrl;

        baseDomain = extractDomain(startUrl);
        linksToVisit.add(startUrl);
    }

    // ===================== START CRAWLING =====================
    public void startCrawling() {

        while (!linksToVisit.isEmpty()) {

            String currentUrl = linksToVisit.poll();

            if (visitedLinks.contains(currentUrl)) {
                continue;
            }

            visitedLinks.add(currentUrl);
            System.out.println("\nNavigating to: " + currentUrl);
/*
            try {
                page.navigate(
                        currentUrl,
                        new Page.NavigateOptions()
                                .setTimeout(TIMEOUT)
                                .setWaitUntil(LoadState.DOMCONTENTLOADED)
                );

                // 🔥 ONLY href extraction
                extractLinksFromPage();

            } catch (Exception e) {
                System.out.println("Status: PAGE LOAD FAILED");
            }*/
        }

        System.out.println("\n========== LINK VALIDATION ==========");
        validateAllLinks();
        printSummary();
        close();
    }

    // ===================== EXTRACT ONLY HREF LINKS =====================
    private void extractLinksFromPage() {

        List<String> hrefLinks = page.locator("a[href]").all()
                                     .stream()
                                     .map(locator -> locator.getAttribute("href")) // ✅ ONLY href
                                     .filter(Objects::nonNull)
                                     .map(this::normalizeUrl)
                                     .filter(this::isValidLink)
                                     .collect(Collectors.toList());

        for (String link : hrefLinks) {
            if (!visitedLinks.contains(link)) {
                linksToVisit.add(link);
            }
        }
    }

    // ===================== NORMALIZE URL =====================
    private String normalizeUrl(String href) {

        // Absolute URL
        if (href.startsWith("http")) {
            return href;
        }

        // Relative URL
        if (href.startsWith("/")) {
            return baseUrl + href;
        }

        return null;
    }

    // ===================== VALIDATE LINKS =====================
    private void validateAllLinks() {

        for (String link : visitedLinks) {
            totalLinksScanned++;

            System.out.println("\nNavigating to: " + link);

            try {
                APIResponse response = apiRequest.get(link);
                int status = response.status();

                if (status >= 400) {
                    brokenLinksCount++;
                    System.out.println("Status: BROKEN (" + status + ")");
                } else {
                    System.out.println("Status: OK (" + status + ")");
                }

            } catch (Exception e) {
                brokenLinksCount++;
                System.out.println("Status: ERROR");
            }
        }
    }

    // ===================== SUMMARY =====================
    private void printSummary() {
        System.out.println("\n========== SUMMARY ==========");
        System.out.println("Total links scanned : " + totalLinksScanned);
        System.out.println("Broken links found  : " + brokenLinksCount);
        System.out.println("=============================");
    }

    // ===================== HELPERS =====================
    private boolean isValidLink(String link) {
        return link != null
                && link.contains(baseDomain)
                && !link.startsWith("mailto:")
                && !link.startsWith("tel:")
                && !link.contains("#");
    }

    private String extractDomain(String url) {
        return url.replaceFirst("https?://", "")
                  .split("/")[0];
    }

    private void close() {
        apiRequest.dispose();
        page.close();
        browser.close();
        playwright.close();
    }

    // ===================== MAIN =====================
    public static void main(String[] args) {
        String startUrl = "https://www.mediacorp.sg/";
        new LinkCrawlerValidator(startUrl).startCrawling();
    }
}
