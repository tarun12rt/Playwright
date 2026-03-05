package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class LiveAndLearn_LifestylePage extends AbstractSubCategoryPage {

    public LiveAndLearn_LifestylePage(Page page) {
        super(
                page,
                "/liveandlearn",
                "Lifestyle",
                "LIFESTYLE",
                "LIVE & LEARN"
        );
    }
}