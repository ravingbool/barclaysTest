package home.barclays.euvattest.service;

import home.barclays.euvattest.domain.RateType;
import home.barclays.euvattest.domain.VatRate;

import java.util.List;

/**
 * The service layer. Implements a business logic
 *
 * @author Maksim Bulankin
 * */
public interface VatRateService {

    /**
     * @return the particular number of countries with the highest or the lowest VAT rates in EU
     *
     * @param numberOfItemsToShow the number of items which will be shown in list
     * @param ascending sort the list of countries in ascending order or not
     * @param rateType a type of rate
     * */
    List<VatRate> getListOfTheHighestLowestVATCountries(int numberOfItemsToShow, boolean ascending, RateType rateType);

}