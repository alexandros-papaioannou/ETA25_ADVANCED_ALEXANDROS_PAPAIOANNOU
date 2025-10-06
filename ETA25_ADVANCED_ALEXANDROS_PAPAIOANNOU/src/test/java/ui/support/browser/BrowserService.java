package ui.support.browser;

import ui.support.utils.DriverXmlReader;

public interface BrowserService {

    void openBrowser(DriverXmlReader driverXmlReader);

    Object getBrowserOptions(DriverXmlReader driverXmlReader);
}
