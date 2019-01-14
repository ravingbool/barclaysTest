package home.barclays.euvattest.service;

import home.barclays.euvattest.dao.impl.JSONFromUrlDAO;
import home.barclays.euvattest.domain.RateType;
import home.barclays.euvattest.domain.VatRate;
import home.barclays.euvattest.service.impl.VatRateServiceUrlFromJSON;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class VatRateServiceUrlFromJSONTest {

    @Test
    public void test() {

        JSONFromUrlDAO dao = new JSONFromUrlDAO();
        VatRateService service = new VatRateServiceUrlFromJSON(dao);

        Map<Double, List<VatRate>> result = service.getTheHighestLowestVATCountries(1,
                true, RateType.STANDARD);

        Assert.assertNotEquals(0, result.size());

    }
}