package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class Entertainment_LocalPage extends AbstractSubCategoryPage {

    public Entertainment_LocalPage(Page page) {
        super(
                page,
                "/entertainment",  // Main menu href
                "Local",           // Sub menu text
                "Local",           // Page heading
                "Local"            // Breadcrumb text
        );
    }
}