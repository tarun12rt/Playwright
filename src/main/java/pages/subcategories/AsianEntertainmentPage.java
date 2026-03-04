package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class AsianEntertainmentPage extends AbstractSubCategoryPage {

    public AsianEntertainmentPage(Page page) {
        super(
                page,
                "/entertainment",  // Main menu href
                "Asian",           // Sub menu text
                "Asian",           // Page heading
                "Asian"            // Breadcrumb text
        );
    }
}