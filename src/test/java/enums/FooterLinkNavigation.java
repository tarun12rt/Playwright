package enums;

public enum FooterLinkNavigation {

    ABOUT_8DAYS("About 8days", "/about"),
    CONTACT_US("Contact Us", "/contactus"),
    ADVERTISE("Advertise", "https://www.mediacorp.sg/business/advertising"),
    MEDIACORP_DIGITAL_NETWORK("Mediacorp Digital Network", "/mediacorp-digital-network");

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

