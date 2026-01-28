package pages;

import java.util.List;

public class LinkValidationRunner {

    public static void main(String[] args) {

        String startUrl = "https://www.mediacorp.sg/";

        // Step 1: Extract href links
        LinkHrefExtractor extractor = new LinkHrefExtractor();
        List<String> extractedLinks = extractor.extractLinks(startUrl);

        System.out.println("\nTotal links extracted: " + extractedLinks.size());

        // Step 2: Check link status
        LinkStatusChecker checker = new LinkStatusChecker();
        checker.checkLinks(extractedLinks);
        checker.close();
    }
}
