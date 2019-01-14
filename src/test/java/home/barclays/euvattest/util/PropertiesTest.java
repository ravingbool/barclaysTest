package home.barclays.euvattest.util;

import org.junit.Assert;
import org.junit.Test;

public class PropertiesTest {

    private static final String URL = "http://jsonvat.com";

    @Test
    public void propertiesTest() {
        Assert.assertEquals(URL, PropertiesUtils.getProperties().get("hostUrl"));
    }
}