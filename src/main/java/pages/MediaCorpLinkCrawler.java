package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class MediaCorpLinkCrawler {

    private static final int TIMEOUT = 10000;
    private static final String ALLOWED_DOMAIN = "www.mediacorp.sg";
    private static final int MAX_LINKS = 150; // 🛑 HARD STOP

    private final Playwright playwright;
    private final Browser browser;
    private final Page page;
    private final APIRequestContext api;

    private final Queue<String> queue = new LinkedList<>();
    private final Set<String> visited = new HashSet<>();

    // 📊 Counters
    private int total = 0;
    private int ok = 0;
    private int error = 0;

    // 📄 CSV storage
    private final List<LinkResult> results = new ArrayList<>();

    // ===================== RESULT MODEL =====================
    static class LinkResult {
        String url;
        String status;
        String httpCode;

        LinkResult(String url, String status, String httpCode) {
            this.url = url;
            this.status = status;
            this.httpCode = httpCode;
        }
    }

    // ===================== CONSTRUCTOR =====================
    public MediaCorpLinkCrawler() {
        playwright = Playwright.create();
        browser = playwright.chromium()
                            .launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
        api = playwright.request().newContext();
    }

    // ===================== START =====================
    public void start(String startUrl) {
        queue.add(startUrl);

        while (!queue.isEmpty()) {

            if (total >= MAX_LINKS) {
                System.out.println("\n🛑 Max link limit reached. Stopping crawl.");
                break;
            }

            String url = queue.poll();
            if (visited.contains(url)) continue;

            visited.add(url);
            total++;

            System.out.println("\nNavigated to: " + url);

            boolean isOk = checkStatus(url);

            // 🔒 Extract ONLY from allowed domain
            if (isOk && url.contains(ALLOWED_DOMAIN)) {
                extractAndQueueLinks(url);
            }
        }

        printSummary();
        exportToCSV("mediacorp_link_status.csv");
        close();
    }

    // ===================== STATUS CHECK =====================
    private boolean checkStatus(String url) {
        try {
            APIResponse response = api.get(url);
            int statusCode = response.status();

            if (statusCode >= 400) {
                error++;
                System.out.println("Status: BROKEN (" + statusCode + ")");
                results.add(new LinkResult(url, "BROKEN", String.valueOf(statusCode)));
                return false;
            } else {
                ok++;
                System.out.println("Status: OK");
                results.add(new LinkResult(url, "OK", String.valueOf(statusCode)));
                return true;
            }

        } catch (Exception e) {
            error++;
            System.out.println("Status: ERROR");
            results.add(new LinkResult(url, "ERROR", "-"));
            return false;
        }
    }

    // ===================== EXTRACT LINKS =====================
    private void extractAndQueueLinks(String url) {
        try {
            page.navigate(
                    url,
                    new Page.NavigateOptions()
                            .setTimeout(TIMEOUT)
                            .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
            );

            List<String> links = page.locator("a[href]").all()
                                     .stream()
                                     .map(l -> l.getAttribute("href"))
                                     .filter(Objects::nonNull)
                                     .map(this::normalizeUrl)
                                     .filter(this::isCleanLink)
                                     .filter(link -> !visited.contains(link))
                                     .collect(Collectors.toList());

            queue.addAll(links);

        } catch (Exception ignored) {
        }
    }

    // ===================== URL NORMALIZATION =====================
    private String normalizeUrl(String url) {
        if (!url.startsWith("http")) return null;
        return url.split("#")[0].split("\\?")[0];
    }

    // ===================== NOISE FILTER =====================
    private boolean isCleanLink(String url) {
        if (url == null) return false;

        return !url.contains("click")
                && !url.contains("recommend")
                && !url.contains("utm_")
                && !url.contains("session");
    }

    // ===================== CSV EXPORT =====================
    private void exportToCSV(String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {

            writer.println("URL,Status,HTTP_Code");

            for (LinkResult result : results) {
                writer.printf("\"%s\",%s,%s%n",
                        result.url,
                        result.status,
                        result.httpCode);
            }

            System.out.println("\n📄 CSV exported successfully: " + fileName);

        } catch (Exception e) {
            System.out.println("❌ Failed to export CSV: " + e.getMessage());
        }
    }

    // ===================== SUMMARY =====================
    private void printSummary() {
        System.out.println("\n========== SUMMARY ==========");
        System.out.println("Total links checked : " + total);
        System.out.println("OK links            : " + ok);
        System.out.println("Error/Broken links  : " + error);
        System.out.println("=============================");
    }

    // ===================== CLEANUP =====================
    private void close() {
        api.dispose();
        page.close();
        browser.close();
        playwright.close();
    }

    // ===================== MAIN =====================
    public static void main(String[] args) {
        new MediaCorpLinkCrawler()
                .start("https://www.8days.sg");
    }
}
