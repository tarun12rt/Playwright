package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class LocalEntertainmentPage extends AbstractSubCategoryPage {

    public LocalEntertainmentPage(Page page) {
        super(
                page,
                "/entertainment",  // Main menu href
                "Local",           // Sub menu text
                "Local",           // Page heading
                "Local"            // Breadcrumb text
        );
    }
}