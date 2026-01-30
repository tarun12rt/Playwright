package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtent() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/HTMLReport/ExtentReport.html");

            spark.config().setReportName("Playwright Automation Report");
            spark.config().setDocumentTitle("Automation Results");

            // 🔽 Add Download ZIP button in Extent Report
            spark.config().setJs(
                    "document.addEventListener('DOMContentLoaded', function() {" +
                            "  const header = document.querySelector('.nav-right');" +
                            "  if(header) {" +
                            "    const btn = document.createElement('a');" +
                            "    btn.innerHTML = '⬇ Download Report';" +
                            "    btn.href = '../AutomationReport.zip';" +
                            "    btn.setAttribute('download','AutomationReport.zip');" +
                            "    btn.style.cssText = '" +
                            "      margin-left:15px;" +
                            "      padding:6px 12px;" +
                            "      background:#1f6feb;" +
                            "      color:#ffffff;" +
                            "      border-radius:4px;" +
                            "      text-decoration:none;" +
                            "      font-weight:600;" +
                            "    ';" +
                            "    header.appendChild(btn);" +
                            "  }" +
                            "});"
            );

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}
