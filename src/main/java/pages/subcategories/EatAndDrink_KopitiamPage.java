package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class EatAndDrink_KopitiamPage extends AbstractSubCategoryPage {

    public EatAndDrink_KopitiamPage(Page page) {
        super(
                page,
                "/eatanddrink",
                "Kopitiam",
                "Kopitiam",
                "Eat & Drink"
        );
    }
}