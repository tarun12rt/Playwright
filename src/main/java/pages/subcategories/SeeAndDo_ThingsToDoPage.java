package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class SeeAndDo_ThingsToDoPage extends AbstractSubCategoryPage {

    public SeeAndDo_ThingsToDoPage(Page page) {
        super(
                page,
                "/seeanddo",      // Main menu href
                "Things To Do",   // Sub menu text
                "THINGS TO DO",   // Page heading
                "SEE & DO"        // Breadcrumb text
        );
    }
}