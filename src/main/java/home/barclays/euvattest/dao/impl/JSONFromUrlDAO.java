package home.barclays.euvattest.dao.impl;

import home.barclays.euvattest.dao.DataAccessObject;
import home.barclays.euvattest.domain.VatRate;
import home.barclays.euvattest.util.PropertiesUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The implementation of the DAO. Works with JSON hosted in the Internet and parse
 * JSON with Jackson library
 *
 * @author Maksim Bulankin
 * */
public class JSONFromUrlDAO implements DataAccessObject<VatRate> {

    private static final Logger LOG = LoggerFactory.getLogger(JSONFromUrlDAO.class);

    private ObjectMapper objectMapper;

    public List<VatRate> getAll() {
        try {
            Properties properties = PropertiesUtils.getProperties();
            JsonNode rootNode = objectMapper.readTree(new URL((String) properties.get("hostUrl")));
            return objectMapper.readValue(rootNode.get((String)properties.get("parsableNodeName")).toString(),
                    new TypeReference<List<VatRate>>(){});

        } catch (IOException e) {
            LOG.error("the error occurs while collecting the data from the remote JSON file", e);
        }

        return new ArrayList<>();
    }

    public JSONFromUrlDAO() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

}