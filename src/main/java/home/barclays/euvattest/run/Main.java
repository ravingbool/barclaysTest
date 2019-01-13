package home.barclays.euvattest.run;

import home.barclays.euvattest.dao.DataAccessObject;
import home.barclays.euvattest.dao.impl.JSONFromUrlDAO;
import home.barclays.euvattest.domain.RateType;
import home.barclays.euvattest.domain.VatRate;
import home.barclays.euvattest.service.VatRateService;
import home.barclays.euvattest.service.impl.VatRateServiceUrlFromJSON;
import home.barclays.euvattest.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/** The main class of the app: creates needed classes and calls the particular methods
 * Some checks for input are omitted for shortness (e.g. does input number of countries is valid integer)
 *
 * @author Maksim Bulankin
 * */
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) {

        DataAccessObject dao = new JSONFromUrlDAO();
        VatRateService service = new VatRateServiceUrlFromJSON(dao);

        int numberOfCountriesToShow = Integer.parseInt((String)PropertiesUtils.getProperties().get("numberOfCountriesToShow"));

        List<VatRate> rates = service.getListOfTheHighestLowestVATCountries(numberOfCountriesToShow, true,
                RateType.STANDARD);

        if(rates.isEmpty()) {
            LOG.info("the list of rates is empty, can't do further actions");
            return;
        }

        LOG.info("countries with the lowest VAT rates:");
        rates.forEach(item -> LOG.info("{} - {}", item.getCountryName(), item.getCurrentPeriod().getRates().getStandard()));

        rates = service.getListOfTheHighestLowestVATCountries(numberOfCountriesToShow, false, RateType.STANDARD);

        LOG.info("countries with the highest VAT rates:");
        rates.forEach(item -> LOG.info("{} - {}", item.getCountryName(), item.getCurrentPeriod().getRates().getStandard()));

    }
}