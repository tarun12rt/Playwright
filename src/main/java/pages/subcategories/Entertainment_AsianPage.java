package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class Entertainment_AsianPage extends AbstractSubCategoryPage {

    public Entertainment_AsianPage(Page page) {
        super(
                page,
                "/entertainment",  // Main menu href
                "Asian",           // Sub menu text
                "Asian",           // Page heading
                "Asian"            // Breadcrumb text
        );
    }
}