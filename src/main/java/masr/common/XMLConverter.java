package masr.common;

import com.thoughtworks.xstream.XStream;

public class XMLConverter {
    public static String serializeObject(Object o, String alias) {
        XStream xstream = new XStream();
        xstream.alias(alias, o.getClass());

        return xstream.toXML(o);
    }
}
