package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class SeeAndDo_StreamItPage extends AbstractSubCategoryPage {

    public SeeAndDo_StreamItPage(Page page) {
        super(
                page,
                "/seeanddo",     // Main menu href
                "Stream It",     // Sub menu text
                "STREAM IT",     // Page heading
                "SEE & DO"       // Breadcrumb text
        );
    }
}