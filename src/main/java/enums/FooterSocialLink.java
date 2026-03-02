package enums;

public enum FooterSocialLink {

    FACEBOOK("facebook", "https://www.facebook.com/8DAYSSG"),
    INSTAGRAM("instagram", "https://www.instagram.com/8daysSG/"),
    TELEGRAM("telegram", "https://telegram.me/eight_days"),
    RSS("rss", "/rss");

    private final String locatorKey;
    private final String expectedDomain;

    FooterSocialLink(String locatorKey, String expectedDomain) {
        this.locatorKey = locatorKey;
        this.expectedDomain = expectedDomain;
    }

    public String getLocatorKey() { return locatorKey; }
    public String getExpectedDomain() { return expectedDomain; }
}

