package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class LiveAndLearn_HomeMattersPage extends AbstractSubCategoryPage {

    public LiveAndLearn_HomeMattersPage(Page page) {
        super(
                page,
                "/liveandlearn",
                "Home Matters",
                "HOME MATTERS",
                "LIVE & LEARN"
        );
    }
}