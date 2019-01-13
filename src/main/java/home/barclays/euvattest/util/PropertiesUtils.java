package home.barclays.euvattest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**a helper class for properties in the app
 *
 * @author Maksim Bulankin
 * */
public final class PropertiesUtils {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtils.class);

    private static final Properties APP_PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE_NAME = "app.properties";

    private PropertiesUtils() {}

    public static Properties getProperties() {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream resourceStream = loader.getResourceAsStream(PROPERTIES_FILE_NAME)) {
            APP_PROPERTIES.load(resourceStream);
        } catch (IOException e) {
            LOG.error("an error occurs while loading a property file", e);
        }

        return APP_PROPERTIES;
    }
}