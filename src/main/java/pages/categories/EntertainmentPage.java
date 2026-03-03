package pages.categories;

import com.microsoft.playwright.Page;
import pages.base.AbstractCategoryPage;

public class EntertainmentPage extends AbstractCategoryPage {

    public EntertainmentPage(Page page) {
        super(
                page,
                "Entertainment",          // Page Heading
                "/entertainment",         // Menu href
                "Entertainment"           // Breadcrumb text
        );
    }
}