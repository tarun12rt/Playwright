package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class EatAndDrink_NewsReviewsPage extends AbstractSubCategoryPage {

    public EatAndDrink_NewsReviewsPage(Page page) {
        super(
                page,
                "/eatanddrink",     // Main menu href
                "News & Reviews",   // Sub menu text
                "News & Reviews",   // Page heading
                "Eat & Drink"       // Breadcrumb (category parent)
        );
    }
}