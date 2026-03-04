package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class HollywoodEntertainmentPage extends AbstractSubCategoryPage {

    public HollywoodEntertainmentPage(Page page) {
        super(
                page,
                "/entertainment",  // Main menu href
                "Hollywood",       // Sub menu text
                "Hollywood",       // Page heading
                "Hollywood"        // Breadcrumb text
        );
    }
}