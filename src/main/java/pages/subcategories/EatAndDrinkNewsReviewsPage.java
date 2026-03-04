package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class EatAndDrinkNewsReviewsPage extends AbstractSubCategoryPage {

    public EatAndDrinkNewsReviewsPage(Page page) {
        super(
                page,
                "/eatanddrink",     // Main menu href
                "News & Reviews",   // Sub menu text
                "News & Reviews",   // Page heading
                "Eat & Drink"       // Breadcrumb (category parent)
        );
    }
}