package ui.support.browser;

import org.openqa.selenium.WebDriver;
import ui.support.utils.ConfigXmlReader;
import ui.support.utils.XmlConfig;

import java.util.Locale;


public class BrowserFactory {

    public WebDriver getBrowserOptions() {
        String isRemote = System.getProperty("isRemote");
        String browser = System.getProperty("browser").toLowerCase(Locale.ROOT);

        ConfigXmlReader configXmlReader = XmlConfig.createConfigXml(ConfigXmlReader.class);

        if (Boolean.parseBoolean(isRemote)) {
            configXmlReader.driverXmlReader.headless = "--headless=new";
        } else {
            browser = configXmlReader.driverXmlReader.localBrowser;
        }

        switch (browser) {
            case BrowserType.BROWSER_CHROME:
                ChromeBrowserService chromeService = new ChromeBrowserService();
                chromeService.openBrowser(configXmlReader.driverXmlReader);
                return chromeService.getDriver();
            //TO ADD MORE BROWSER CASES, IF NEEDED: FIREFOX, EDGE, ETC.
        }
        return null;
    }
}
