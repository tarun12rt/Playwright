package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class RecipesPage extends AbstractSubCategoryPage {

    public RecipesPage(Page page) {
        super(
                page,
                "/eatanddrink",
                "Recipes",
                "Recipes",
                "Eat & Drink"
        );
    }
}