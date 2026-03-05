package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class Entertainment_HollywoodPage extends AbstractSubCategoryPage {

    public Entertainment_HollywoodPage(Page page) {
        super(
                page,
                "/entertainment",  // Main menu href
                "Hollywood",       // Sub menu text
                "Hollywood",       // Page heading
                "Hollywood"        // Breadcrumb text
        );
    }
}