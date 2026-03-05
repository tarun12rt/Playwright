package pages.subcategories;

import com.microsoft.playwright.Page;
import pages.base.AbstractSubCategoryPage;

public class LiveAndLearn_TravelPage extends AbstractSubCategoryPage {

    public LiveAndLearn_TravelPage(Page page) {
        super(
                page,
                "/liveandlearn",
                "Travel",
                "TRAVEL",
                "LIVE & LEARN"
        );
    }
}