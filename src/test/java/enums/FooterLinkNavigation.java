package enums;

public enum FooterLinkNavigation {

    ABOUT_8DAYS("About 8days", "https://www.8days.sg/about"),
    CONTACT_US("Contact Us", "https://www.8days.sg/contactus"),
    ADVERTISE("Advertise", "https://www.mediacorp.sg/business/advertising"),
    TERMS_OF_USE("Terms Of Use", "https://www.8days.sg/mediacorp-digital-network"),
    PRIVACY_POLICY("Privacy Policy", "https://www.mediacorp.sg/terms-conditions"),
    VULNERABILITY("Vulnerability Disclosure", "https://www.mediacorp.sg/privacy-policy"),
    ONLINE_LINKS_POLICY("Online Links Policy", "https://mediacorp.vulnerability-disclosure.com/p/Policy"),
    MEDIACORP("Mediacorp Digital Network", "https://www.mediacorp.sg/online-links-policy");

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
