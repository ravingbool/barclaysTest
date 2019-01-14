package home.barclays.euvattest.run;

import home.barclays.euvattest.dao.impl.JSONFromUrlDAO;
import home.barclays.euvattest.domain.RateType;
import home.barclays.euvattest.domain.VatRate;
import home.barclays.euvattest.service.VatRateService;
import home.barclays.euvattest.service.impl.VatRateServiceUrlFromJSON;
import home.barclays.euvattest.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The main class of the app: creates needed classes and calls the particular methods
 *
 * @author Maksim Bulankin
 */
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) {

        VatRateService service = new VatRateServiceUrlFromJSON(new JSONFromUrlDAO());

        int numberOfCountriesToShow = Integer.parseInt((String) PropertiesUtils.getProperties().get("numberOfCountriesToShow"));

        if (numberOfCountriesToShow < 0) {
            LOG.error("Please, make sure the number of countries is integer and greater than zero");
            return;
        }

        Map<Double, List<VatRate>> rates = service.getTheHighestLowestVATCountries(numberOfCountriesToShow, true,
                RateType.STANDARD);

        if (rates.isEmpty()) {
            LOG.info("There are no available rates, can't do further actions");
            return;
        }

        LOG.info("countries with the lowest VAT rates:");
        rates.forEach((key, value) -> LOG.info("{} - {}", key, value.stream().
                map(VatRate::getCountryName).collect(Collectors.joining(", "))));

        rates = service.getTheHighestLowestVATCountries(numberOfCountriesToShow, false, RateType.STANDARD);

        LOG.info("countries with the highest VAT rates:");
        rates.forEach((key, value) -> LOG.info("{} - {}", key, value.stream().
                map(VatRate::getCountryName).collect(Collectors.joining(", "))));

    }
}