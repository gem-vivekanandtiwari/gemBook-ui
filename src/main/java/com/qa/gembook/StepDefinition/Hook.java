package com.qa.gembook.StepDefinition;

import com.gemini.generic.exception.GemException;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.generic.utils.GemJarConstants;
import com.gemini.generic.utils.GemJarGlobalVar;
import com.gemini.generic.utils.GemJarUtils;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.gemini.generic.ui.utils.DriverManager.setWebDriver;
import static oracle.jdbc.replay.OracleDataSource.URL;

public class Hook {
    @Before
    public void start() throws GemException, MalformedURLException {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--incognito");
//        DriverManager.initializeChrome(options);
//        DriverAction.maximizeBrowser();
//        DriverAction.launchUrl("https://gembook.geminisolutions.com/#/");

        //firefox
        String remoteURL = GemJarUtils.getGemJarKeyValue(GemJarConstants.REMOTE_WEBDRIVER_URL);
        if (remoteURL == null) {
            remoteURL = GemJarConstants.DEFAULT_REMOTE_WEBDRIVER_URL;
        }
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        options.addArguments("--incognito");
        options.addArguments("--window-size=1920,1080");
        WebDriver driver = remoteURL != null ? new RemoteWebDriver(new URL(remoteURL), options) : new RemoteWebDriver(options);
        setWebDriver(driver);
        DriverAction.launchUrl(GemJarUtils.getGemJarConfigData("launchUrl"));
        DriverAction.maximizeBrowser();
       // DriverAction.maximizeToDefaultBrowserSize();
        DriverAction.setImplicitTimeOut(Long.parseLong(GemJarGlobalVar.implicitTime));
        DriverAction.setPageLoadTimeOut(Long.parseLong(GemJarGlobalVar.pageTimeout));
        DriverAction.setScriptTimeOut(Long.parseLong(GemJarGlobalVar.scriptTimeout));


    }
}
