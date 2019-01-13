package service;

import home.barclays.euvattest.dao.impl.JSONFromUrlDAO;
import home.barclays.euvattest.domain.RateType;
import home.barclays.euvattest.domain.VatRate;
import home.barclays.euvattest.service.VatRateService;
import home.barclays.euvattest.service.impl.VatRateServiceUrlFromJSON;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class VatRateServiceUrlFromJSONTest {

    @Test
    public void test() {

        JSONFromUrlDAO dao = new JSONFromUrlDAO();
        VatRateService service = new VatRateServiceUrlFromJSON(dao);

        List<VatRate> list = service.getListOfTheHighestLowestVATCountries(1, true,
                RateType.STANDARD);

        Assert.assertEquals("Luxembourg", list.get(0).getCountryName());

    }
}