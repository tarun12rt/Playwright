Playwright Java Automation Framework
===================================

A production-ready Playwright automation framework built using Java, TestNG,
Page Object Model (POM), Component-based design, and ThreadLocal parallel execution.

This framework is designed to be:
- Scalable
- Parallel-safe
- CI/CD ready
- Interview-grade


TECH STACK
----------

Language        : Java (JDK 11)
Automation Tool : Playwright
Test Runner     : TestNG
Design Pattern  : POM + Component-based POM
Parallel Exec   : ThreadLocal
Reporting       : Extent Reports
Build Tool      : Maven


FRAMEWORK ARCHITECTURE
----------------------

src/test/java
 ├── base
 │   ├── BaseTestSingleExecution.java
 │   └── BaseTestParallelExecution.java   (ThreadLocal based)
 │
 ├── pages
 │   ├── BasePage.java                    (Reusable safe actions)
 │   ├── HomePage.java
 │   └── components
 │       └── FooterComponent.java
 │
 ├── tests
 │   └── FooterTest.java
 │
 ├── utils
 │   ├── BrowserFactory.java
 │   ├── ExtentManager.java
 │   └── TimeUtil.java
 │
 └── config
     └── Config.java

src/test/resources
 └── config.properties

testng.xml


KEY DESIGN PRINCIPLES
--------------------
- Tests contain only assertions
- Page Objects contain UI interaction logic
- Components represent reusable UI blocks
- BaseTest handles test lifecycle and infrastructure
- ThreadLocal ensures safe parallel execution


HOW TO RUN TESTS
----------------

PREREQUISITES
- Java 11 or above
- Maven
- Node.js (required for Playwright)


INSTALL PLAYWRIGHT BROWSERS
---------------------------
npx playwright install


RUN TESTS (SEQUENTIAL)
----------------------
Option 1: Run from IDE
- Right-click test class → Run

Option 2: Run from terminal
mvn test


RUN TESTS (PARALLEL)
--------------------
Run using TestNG suite:
- Right-click testng.xml → Run


CONFIGURATION (config.properties)
---------------------------------
browser=chromium
headless=false

video.recording=true
video.onFailureOnly=false

baseUrl=https://www.8days.sg


SUPPORTED BROWSERS
------------------
- chromium
- firefox
- webkit


PARALLEL EXECUTION (ThreadLocal)
--------------------------------
This framework supports parallel execution using ThreadLocal.

Example testng.xml:

<suite name="Playwright Parallel Suite"
       parallel="methods"
       thread-count="2">

    <test name="UI Tests">
        <classes>
            <class name="tests.FooterTest"/>
        </classes>
    </test>

</suite>


WHAT PARALLEL EXECUTION GUARANTEES
---------------------------------
- One browser per thread
- No shared state
- Independent screenshots, videos, and traces


SAMPLE PARALLEL TEST
--------------------
@Test
public void verify8DaysFooter() {

    System.out.println(
        "Running on Thread ID: " + Thread.currentThread().getId()
    );

    FooterPage footerPage = new FooterPage(page());
    footerPage.open();

    Assert.assertTrue(
        footerPage.verifyFooterIsVisible(),
        "Footer is not visible"
    );

    test().pass("Footer verified successfully");
}


REPORTS AND ARTIFACTS
---------------------

SCREENSHOTS
screenshots/
 └── verify8DaysFooter.png

VIDEOS
videos/
 └── verify8DaysFooter_<timestamp>.webm

TRACES
traces/
 └── verify8DaysFooter.zip

View trace using:
npx playwright show-trace traces/verify8DaysFooter.zip


EXTENT REPORT
-------------
Location:
reports/ExtentReport.html

Includes:
- Test execution steps
- Failure screenshots
- Parallel execution logs
- Thread-safe reporting

NOTE:
Report screenshots will be added later.


KEY HIGHLIGHTS
--------------
- Page Object Model (POM)
- Component-based UI design
- ThreadLocal parallel execution
- Config-driven browser and environment
- Video, trace, and screenshot debugging
- CI/CD ready architecture


INTERVIEW-READY SUMMARY
-----------------------
Designed and implemented a Playwright Java automation framework using POM and
component-based architecture, with ThreadLocal parallel execution, config-driven
setup, and integrated reporting with video and trace support.


FUTURE ENHANCEMENTS
-------------------
- Allure reporting
- API + UI hybrid testing
- Environment-based configs (qa / stage / prod)
- GitHub Actions CI pipeline


AUTHOR
------
Tarun Kumar
QA Automation Engineer
Playwright | Selenium | Java | TestNG
