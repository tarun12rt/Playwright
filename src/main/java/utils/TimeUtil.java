package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    public static String getTimestamp() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        return LocalDateTime.now().format(formatter);
    }
}
