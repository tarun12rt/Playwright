package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class EatAndDrink_HawkerFoodPage extends AbstractSubCategoryPage {

    public EatAndDrink_HawkerFoodPage(Page page) {
        super(
                page,
                "/eatanddrink",
                "Hawker Food",
                "Hawker Food",
                "Eat & Drink"
        );
    }
}