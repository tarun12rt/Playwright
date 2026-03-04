package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class HawkerFoodPage extends AbstractSubCategoryPage {

    public HawkerFoodPage(Page page) {
        super(
                page,
                "/eatanddrink",
                "Hawker Food",
                "Hawker Food",
                "Eat & Drink"
        );
    }
}