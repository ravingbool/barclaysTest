package home.barclays.euvattest.service;

import home.barclays.euvattest.domain.RateType;
import home.barclays.euvattest.domain.VatRate;

import java.util.List;
import java.util.Map;

/**
 * The service layer. Implements a business logic
 *
 * @author Maksim Bulankin
 * */
public interface VatRateService {

    /**
     * @return Countries with the highest or the lowest VAT rates in EU
     *
     * @param numberOfCountriesToShow the number of items which will be shown in list
     * @param ascending sort the list of the EU countries in ascending VAT order or not
     * @param rateType a type of the rate
     * */
    Map<Double, List<VatRate>> getTheHighestLowestVATCountries(int numberOfCountriesToShow, boolean ascending, RateType rateType);
}