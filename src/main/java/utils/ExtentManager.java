package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ExtentManager {

    private static ExtentReports extent;

    private static final String LATEST_REPORT_DIR = "test-output/latest-report/";
    private static final String HISTORY_DIR = "test-output/history/";
    private static final String SCREENSHOT_DIR = "test-output/latest-report/screenshots/";
    private static final String VIDEO_DIR = "test-output/latest-report/videos/";
    private static final String ZIP_PATH = "test-output/latest-report.zip";

    public static ExtentReports getExtent() {

        if (extent == null) {

            try {

                /* ===== Create Required Directories ===== */

                Files.createDirectories(Paths.get(LATEST_REPORT_DIR));
                Files.createDirectories(Paths.get(HISTORY_DIR));
                Files.createDirectories(Paths.get(SCREENSHOT_DIR));
                Files.createDirectories(Paths.get(VIDEO_DIR));

                String reportPath = LATEST_REPORT_DIR + "ExtentReport.html";

                ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

                spark.config().setReportName("Playwright Automation Report");
                spark.config().setDocumentTitle("Automation Results");
                spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

                /* ===== Download Report Button ===== */

                spark.config().setJs(
                        "document.addEventListener('DOMContentLoaded', function() {" +
                                "const header = document.querySelector('.nav-right');" +
                                "if(header){" +
                                "const btn=document.createElement('a');" +
                                "btn.innerHTML='⬇ Download Report';" +
                                "btn.href='../latest-report.zip';" +
                                "btn.setAttribute('download','AutomationReport.zip');" +
                                "btn.style.cssText='margin-left:15px;padding:6px 12px;background:#1f6feb;color:white;border-radius:4px;text-decoration:none;font-weight:600';" +
                                "header.appendChild(btn);" +
                                "}" +
                                "});"
                );

                extent = new ExtentReports();
                extent.attachReporter(spark);

                /* ===== Environment Information ===== */

                extent.setSystemInfo("Automation Framework", "Playwright + Java + TestNG");
                extent.setSystemInfo("Tester", System.getProperty("user.name"));
                extent.setSystemInfo("OS", System.getProperty("os.name"));
                extent.setSystemInfo("Java Version", System.getProperty("java.version"));
                extent.setSystemInfo("Host", InetAddress.getLocalHost().getHostName());
                extent.setSystemInfo("Browser", "Chromium");
                extent.setSystemInfo("Report Generated",
                        new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));

                /* ===== Shutdown Hook ===== */

                Runtime.getRuntime().addShutdownHook(new Thread(() -> {

                    try {

                        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                                .format(new Date());

                        Path source = Paths.get(reportPath);
                        Path historyDestination = Paths.get(HISTORY_DIR + "report_" + timestamp + ".html");

                        /* Copy report to history */

                        if (Files.exists(source)) {

                            Files.copy(
                                    source,
                                    historyDestination,
                                    StandardCopyOption.REPLACE_EXISTING
                            );

                        }

                        /* ===== Zip latest report folder ===== */

                        Path zipPath = Paths.get(ZIP_PATH);
                        Path sourceFolder = Paths.get(LATEST_REPORT_DIR);

                        try (ZipOutputStream zs =
                                     new ZipOutputStream(Files.newOutputStream(zipPath))) {

                            Files.walk(sourceFolder)
                                 .filter(path -> !Files.isDirectory(path))
                                 .forEach(path -> {

                                     ZipEntry zipEntry =
                                             new ZipEntry(sourceFolder.relativize(path).toString());

                                     try {

                                         zs.putNextEntry(zipEntry);
                                         Files.copy(path, zs);
                                         zs.closeEntry();

                                     } catch (Exception e) {
                                         e.printStackTrace();
                                     }

                                 });

                        }

                    }

                    catch (Exception e) {
                        e.printStackTrace();
                    }

                }));

            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return extent;
    }
}