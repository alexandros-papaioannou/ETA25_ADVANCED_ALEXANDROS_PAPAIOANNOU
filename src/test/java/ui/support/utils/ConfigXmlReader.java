package ui.support.utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="configuration")
public class ConfigXmlReader {

    @XmlElement(name = "DriverConfig")
    public DriverXmlReader driverXmlReader;
}
