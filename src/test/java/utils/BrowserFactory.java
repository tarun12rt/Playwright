package utils;

import com.microsoft.playwright.*;
import config.Config;


public class BrowserFactory {

    public static Browser getBrowser(Playwright playwright) {

        String browserName = Config.get("browser").toLowerCase();
        boolean headless = Config.getBoolean("headless");

        BrowserType.LaunchOptions options =
                new BrowserType.LaunchOptions().setHeadless(headless);

        switch (browserName) {
            case "chromium":
                return playwright.chromium().launch(options);
            case "firefox":
                return playwright.firefox().launch(options);
            case "webkit":
                return playwright.webkit().launch(options);
            default:
                throw new RuntimeException("Invalid browser in config.properties");
        }
    }
}
