package utils;

import config.Config;
import org.json.JSONObject;

public class BrowserStackCapabilityManager {

    public static JSONObject getCapabilities() {

        JSONObject caps = new JSONObject();

        String executionType = Config.get("executionType");

        if (executionType.equalsIgnoreCase("desktop")) {

            caps.put("browser", Config.get("browserName"));
            caps.put("browser_version", Config.get("browserVersion"));

            JSONObject bsOptions = new JSONObject();
            bsOptions.put("os", Config.get("os"));
            bsOptions.put("osVersion", Config.get("osVersion"));

            caps.put("browserstack.options", bsOptions);
        }

        else if (executionType.equalsIgnoreCase("mobile")) {

            caps.put("device", Config.get("deviceName"));

            JSONObject bsOptions = new JSONObject();
            bsOptions.put("osVersion", Config.get("deviceOSVersion"));

            caps.put("browserstack.options", bsOptions);
        }

        return caps;
    }
}