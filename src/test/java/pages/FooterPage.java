package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class FooterPage extends BasePage {

    private final Locator onlineLinksPolicy;
    private final Locator about8Days;
    private final Locator contactUs;
    private final Locator advertise;
    private final Locator mediacorpDigitalNetwork;
    private final Locator termsOfUse;
    private final Locator privacyPolicy;
    private final Locator vulnerabilityDisclosure;
    private final Locator entertainment;
    private final Locator eatAndDrink;
    private final Locator seeAndDo;
    private final Locator liveAndLearn;

    public FooterPage(Page page) {
        super(page);
        this.onlineLinksPolicy = page.getByText("Online Links Policy");
        this.about8Days = page.getByText("About 8days");
        this.contactUs = page.locator("//a[normalize-space()='Contact Us']");
        this.advertise = page.locator("//a[normalize-space()='Advertise']");
        this.mediacorpDigitalNetwork = page.getByText("Mediacorp Digital Network");
        this.termsOfUse = page.getByText("Terms Of Use");
        this.privacyPolicy = page.getByText("Privacy Policy");
        this.vulnerabilityDisclosure = page.getByText("Vulnerability Disclosure");
        this.entertainment = page.locator("//a[normalize-space()='ENTERTAINMENT']");
        this.eatAndDrink = page.locator("//a[normalize-space()='EAT & DRINK']");
        this.seeAndDo = page.locator("//a[normalize-space()='SEE & DO']");
        this.liveAndLearn = page.locator("//a[normalize-space()='LIVE & LEARN']");


    }


    public boolean isFooterVisible() {
        return safeIsVisible(onlineLinksPolicy);
    }

    public void open() {
        openBaseUrl();
    }

    public boolean verifyFooterIsVisible() {
        scrollTo(onlineLinksPolicy);
        return isFooterVisible();
    }

    public boolean verifyFooterLinksAreVisible() {
        scrollTo(onlineLinksPolicy);
        return safeIsVisible(about8Days) && safeIsVisible(onlineLinksPolicy) && safeIsVisible(contactUs) && safeIsVisible(advertise) && safeIsVisible(mediacorpDigitalNetwork) && safeIsVisible(termsOfUse) && safeIsVisible(privacyPolicy) && safeIsVisible(vulnerabilityDisclosure);
    }

    public boolean verifyFooterLinks() {
        scrollTo(onlineLinksPolicy);
        boolean b1 = safeIsVisible(about8Days);
        boolean b2 = safeIsVisible(onlineLinksPolicy);
        boolean b3 = safeIsVisible(contactUs);
        boolean b4 = safeIsVisible(advertise);
        boolean b5 = safeIsVisible(mediacorpDigitalNetwork);
        boolean b6 = safeIsVisible(termsOfUse);
        boolean b7 = safeIsVisible(privacyPolicy);
        boolean b8 = safeIsVisible(vulnerabilityDisclosure);

        return b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8;
    }

    public boolean verifyCategoryLinksInFooterAreVisible() {
        boolean b1 = safeIsVisible(entertainment);
        boolean b2 = safeIsVisible(eatAndDrink);
        boolean b3 = safeIsVisible(seeAndDo);
        boolean b4 = safeIsVisible(liveAndLearn);

        return b1 && b2 && b3 && b4;
    }
}
