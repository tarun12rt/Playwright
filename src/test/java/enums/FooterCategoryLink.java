package enums;

public enum FooterCategoryLink {

    ENTERTAINMENT("ENTERTAINMENT", "/entertainment"),
    EAT_AND_DRINK("EAT & DRINK", "/eatanddrink"),
    SEE_AND_DO("SEE & DO", "/seeanddo"),
    LIVE_AND_LEARN("LIVE & LEARN", "/liveandlearn");

    private final String linkText;
    private final String expectedPath;

    FooterCategoryLink(String linkText, String expectedPath) {
        this.linkText = linkText;
        this.expectedPath = expectedPath;
    }

    public String getLinkText() { return linkText; }
    public String getExpectedPath() { return expectedPath; }
}

