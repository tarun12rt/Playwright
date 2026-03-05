package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class EatAndDrink_RecipesPage extends AbstractSubCategoryPage {

    public EatAndDrink_RecipesPage(Page page) {
        super(
                page,
                "/eatanddrink",
                "Recipes",
                "Recipes",
                "Eat & Drink"
        );
    }
}