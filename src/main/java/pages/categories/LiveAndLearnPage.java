package pages.categories;

import com.microsoft.playwright.Page;
import pages.base.AbstractCategoryPage;

public class LiveAndLearnPage extends AbstractCategoryPage {

    public LiveAndLearnPage(Page page) {
        super(
                page,
                "Live & Learn",     // Page Heading
                "/liveandlearn",    // Menu href
                "Live & Learn"      // Breadcrumb text
        );
    }
}