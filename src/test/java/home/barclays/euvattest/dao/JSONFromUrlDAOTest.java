package home.barclays.euvattest.dao;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import home.barclays.euvattest.dao.impl.JSONFromUrlDAO;
import home.barclays.euvattest.domain.VatRate;;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONFromUrlDAOTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock JsonNode node = new JsonNode() {
        @Override
        public <T extends JsonNode> T deepCopy() {
            return null;
        }

        @Override
        public JsonNode get(int i) {
            return null;
        }

        @Override
        public JsonNode path(String s) {
            return null;
        }

        @Override
        public JsonNode path(int i) {
            return null;
        }

        @Override
        protected JsonNode _at(JsonPointer jsonPointer) {
            return null;
        }

        @Override
        public JsonNodeType getNodeType() {
            return null;
        }

        @Override
        public String asText() {
            return null;
        }

        @Override
        public JsonNode findValue(String s) {
            return null;
        }

        @Override
        public JsonNode findPath(String s) {
            return null;
        }

        @Override
        public JsonNode findParent(String s) {
            return null;
        }

        @Override
        public List<JsonNode> findValues(String s, List<JsonNode> list) {
            return null;
        }

        @Override
        public List<String> findValuesAsText(String s, List<String> list) {
            return null;
        }

        @Override
        public List<JsonNode> findParents(String s, List<JsonNode> list) {
            return null;
        }

        @Override
        public String toString() {
            return null;
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public JsonToken asToken() {
            return null;
        }

        @Override
        public JsonParser.NumberType numberType() {
            return null;
        }

        @Override
        public JsonParser traverse() {
            return null;
        }

        @Override
        public JsonParser traverse(ObjectCodec objectCodec) {
            return null;
        }

        @Override
        public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        }

        @Override
        public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {

        }
    };

    @Spy
    @InjectMocks
    private final DataAccessObject dao = new JSONFromUrlDAO();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {

        List<VatRate> expected = new ArrayList<>();

        VatRate vatRate1 = new VatRate();
        vatRate1.setCountryName("Bla bla");
        vatRate1.setStandardRate(12d);

        VatRate vatRate2 = new VatRate();
        vatRate2.setCountryName("Bla bla1");
        vatRate2.setStandardRate(12d);

        expected.add(vatRate1);
        expected.add(vatRate2);

        try {
            Mockito.when(objectMapper.readTree(Mockito.any(URL.class))).thenReturn(node);
            Mockito.when(node.get(Mockito.anyString())).thenReturn(node);
            Mockito.when(node.toString()).thenReturn("");
            Mockito.when(objectMapper.readValue(Mockito.anyString(), Mockito.any(TypeReference.class))).thenReturn(expected);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<VatRate> rateList = dao.getAll();

        VatRate rate = rateList.get(0);

        Assert.assertEquals(2, rateList.size());
        Assert.assertEquals("Bla bla", rate.getCountryName());

    }
}