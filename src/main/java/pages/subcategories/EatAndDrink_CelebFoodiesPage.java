package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class EatAndDrink_CelebFoodiesPage extends AbstractSubCategoryPage {

    public EatAndDrink_CelebFoodiesPage(Page page) {
        super(
                page,
                "/eatanddrink",
                "Celeb Foodies",
                "Celeb Foodies",
                "Eat & Drink"
        );
    }
}