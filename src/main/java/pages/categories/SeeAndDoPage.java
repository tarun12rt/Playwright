package pages.categories;

import com.microsoft.playwright.Page;
import pages.base.AbstractCategoryPage;

public class SeeAndDoPage extends AbstractCategoryPage {

    public SeeAndDoPage(Page page) {
        super(
                page,
                "See & Do",       // Page Heading
                "/seeanddo",      // Menu href
                "See & Do"        // Breadcrumb text
        );
    }
}