package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import home.barclays.euvattest.util.LocalDateDeserializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeSerializerTest {

    private LocalDate date;

    @Before
    public void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        objectMapper.registerModule(module);
        try {
            date = objectMapper.readValue("\"0000-01-01\"", LocalDate.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(LocalDate.of(0, 1, 1), date);
    }
}