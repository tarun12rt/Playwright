package pages.categories;

import com.microsoft.playwright.Page;
import pages.base.AbstractCategoryPage;

public class EatAndDrinkPage extends AbstractCategoryPage {

    public EatAndDrinkPage(Page page) {
        super(
                page,
                "Eat & Drink",     // Page Heading
                "/eatanddrink",    // Menu href
                "Eat & Drink"      // Breadcrumb text
        );
    }
}