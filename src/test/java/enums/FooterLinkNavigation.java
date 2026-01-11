package enums;

public enum FooterLinkNavigation {

    ABOUT_8DAYS("About 8days", "/about"),
    CONTACT_US("Contact Us", "/contactus"),
//    ADVERTISE("Advertise", "https://www.mediacorp.sg/business/advertising"),
    MEDIACORP_DIGITAL_NETWORK("Mediacorp Digital Network", "/mediacorp-digital-network"),
//    TERMS_OF_USE("Terms Of Use", "https://www.mediacorp.sg/terms-conditions"),
//    PRIVACY_POLICY("Privacy Policy", "https://www.mediacorp.sg/privacy-policy"),
    VULNERABILITY_DISCLOSURE("Vulnerability Disclosure", "https://mediacorp.vulnerability-disclosure.com/p/Policy"),
//    ONLINE_LINKS_POLICY("Online Links Policy", "https://www.mediacorp.sg/anti-scam-policy"),
    ENTERTAINMENT("ENTERTAINMENT", "/entertainment"),
    EAT_AND_DRINK("EAT & DRINK", "/eatanddrink"),
    SEE_AND_DO("SEE & DO", "/seeanddo"),
    LIVE_AND_LEARN("LIVE & LEARN", "/liveandlearn");
//    FACEBOOK("", "https://www.facebook.com/8DAYSSG");
//    INSTAGRAM("", "https://www.instagram.com/8daysSG/#");
//    TELEGRAM("", "https://telegram.me/eight_days");
//    RSS("", "https://www.8days.sg/rss");

    private final String linkText;
    private final String expectedUrl;

    FooterLinkNavigation(String linkText, String expectedUrl) {
        this.linkText = linkText;
        this.expectedUrl = expectedUrl;
    }

    public String getLinkText() {
        return linkText;
    }

    public String getExpectedUrl() {
        return expectedUrl;
    }
}

