package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class SeeAndDo_WhatsBuzzingPage extends AbstractSubCategoryPage {

    public SeeAndDo_WhatsBuzzingPage(Page page) {
        super(
                page,
                "/seeanddo",          // Main menu href
                "What's Buzzing",     // Sub menu text
                "WHAT'S BUZZING",     // Page heading
                "SEE & DO"            // Breadcrumb text
        );
    }
}