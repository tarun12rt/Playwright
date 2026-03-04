package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class CelebFoodiesPage extends AbstractSubCategoryPage {

    public CelebFoodiesPage(Page page) {
        super(
                page,
                "/eatanddrink",
                "Celeb Foodies",
                "Celeb Foodies",
                "Eat & Drink"
        );
    }
}