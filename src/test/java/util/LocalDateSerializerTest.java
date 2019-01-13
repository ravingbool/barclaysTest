package util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import home.barclays.euvattest.util.LocalDateSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Month;

public class LocalDateSerializerTest {

    @Test
    public void serialises_LocalDateTime() throws IOException {
        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
        new LocalDateSerializer().serialize(LocalDate.of(2000, Month.JANUARY, 1),
                jsonGenerator, serializerProvider);
        jsonGenerator.flush();
        Assert.assertEquals("\"2000-01-01\"", jsonWriter.toString());
    }
}