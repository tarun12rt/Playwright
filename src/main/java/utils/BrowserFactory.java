package utils;

import com.microsoft.playwright.*;
import config.Config;

import java.util.Arrays;

public class BrowserFactory {

    public static Browser getBrowser(Playwright playwright) {

        String browserName = Config.get("browser").toLowerCase();
        boolean headless = Config.getBoolean("headless");

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless);

        // ✅ Always open browser in maximized mode (headed only)
        if (!headless) {
            options.setArgs(Arrays.asList("--start-maximized"));
        }

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