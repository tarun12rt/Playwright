package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class KopitiamPage extends AbstractSubCategoryPage {

    public KopitiamPage(Page page) {
        super(
                page,
                "/eatanddrink",
                "Kopitiam",
                "Kopitiam",
                "Eat & Drink"
        );
    }
}