package home.barclays.euvattest.service;

import home.barclays.euvattest.dao.DataAccessObject;
import home.barclays.euvattest.dao.impl.JSONFromUrlDAO;
import home.barclays.euvattest.domain.RateType;
import home.barclays.euvattest.domain.VatRate;
import home.barclays.euvattest.service.impl.VatRateServiceUrlFromJSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VatRateServiceUrlFromJSONTest {

    @Mock
    private final DataAccessObject dao = new JSONFromUrlDAO();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNotEmptyList() {

        List<VatRate> expected = new ArrayList<>();

        VatRate vatRate1 = new VatRate();
        vatRate1.setCountryName("Bla bla");
        vatRate1.setStandardRate(12d);

        VatRate vatRate2 = new VatRate();
        vatRate2.setCountryName("Bla bla1");
        vatRate2.setStandardRate(13d);

        expected.add(vatRate1);
        expected.add(vatRate2);

        Mockito.when(dao.getAll()).thenReturn(expected);

        VatRateService service = new VatRateServiceUrlFromJSON(dao);

        Map<Double, List<VatRate>> result = service.getTheHighestLowestVATCountries(1,
                true, RateType.STANDARD);

        Assert.assertNotEquals(0, result.size());

    }

    @Test
    public void testTheLowestVATCountries() {

        List<VatRate> expected = new ArrayList<>();

        VatRate vatRate1 = new VatRate();
        vatRate1.setCountryName("First");
        vatRate1.setStandardRate(12d);

        VatRate vatRate2 = new VatRate();
        vatRate2.setCountryName("Second");
        vatRate2.setStandardRate(13d);

        VatRate vatRate3 = new VatRate();
        vatRate3.setCountryName("Third");
        vatRate3.setStandardRate(14d);

        VatRate vatRate4 = new VatRate();
        vatRate4.setCountryName("Fourth");
        vatRate4.setStandardRate(15d);

        expected.add(vatRate1);
        expected.add(vatRate2);
        expected.add(vatRate3);
        expected.add(vatRate4);

        Mockito.when(dao.getAll()).thenReturn(expected);

        VatRateService service = new VatRateServiceUrlFromJSON(dao);

        Map<Double, List<VatRate>> result = service.getTheHighestLowestVATCountries(3,
                true, RateType.STANDARD);

       Assert.assertEquals(3, result.size());
       Assert.assertNull(result.get(15d));
       Assert.assertEquals("Second", result.get(13d).get(0).getCountryName());

    }

    @Test
    public void testTheHighestVATCountries() {

        List<VatRate> expected = new ArrayList<>();

        VatRate vatRate1 = new VatRate();
        vatRate1.setCountryName("First");
        vatRate1.setStandardRate(12d);

        VatRate vatRate2 = new VatRate();
        vatRate2.setCountryName("Second");
        vatRate2.setStandardRate(13d);

        VatRate vatRate3 = new VatRate();
        vatRate3.setCountryName("Third");
        vatRate3.setStandardRate(14d);

        VatRate vatRate4 = new VatRate();
        vatRate4.setCountryName("Fourth");
        vatRate4.setStandardRate(15d);

        expected.add(vatRate1);
        expected.add(vatRate2);
        expected.add(vatRate3);
        expected.add(vatRate4);

        Mockito.when(dao.getAll()).thenReturn(expected);

        VatRateService service = new VatRateServiceUrlFromJSON(dao);

        Map<Double, List<VatRate>> result = service.getTheHighestLowestVATCountries(3,
                false, RateType.STANDARD);

        Assert.assertEquals(3, result.size());
        Assert.assertNull(result.get(12d));
        Assert.assertEquals("Second", result.get(13d).get(0).getCountryName());

    }

    @Test
    public void tesAllVATEqualsCountries() {

        List<VatRate> expected = new ArrayList<>();

        VatRate vatRate1 = new VatRate();
        vatRate1.setCountryName("First");
        vatRate1.setStandardRate(12d);

        VatRate vatRate2 = new VatRate();
        vatRate2.setCountryName("Second");
        vatRate2.setStandardRate(12d);

        VatRate vatRate3 = new VatRate();
        vatRate3.setCountryName("Third");
        vatRate3.setStandardRate(12d);

        VatRate vatRate4 = new VatRate();
        vatRate4.setCountryName("Fourth");
        vatRate4.setStandardRate(12d);

        expected.add(vatRate1);
        expected.add(vatRate2);
        expected.add(vatRate3);
        expected.add(vatRate4);

        Mockito.when(dao.getAll()).thenReturn(expected);

        VatRateService service = new VatRateServiceUrlFromJSON(dao);

        Map<Double, List<VatRate>> result = service.getTheHighestLowestVATCountries(3,
                false, RateType.STANDARD);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("First", result.get(12d).get(0).getCountryName());

    }
}