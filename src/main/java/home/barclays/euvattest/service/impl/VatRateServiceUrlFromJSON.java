package home.barclays.euvattest.service.impl;

import home.barclays.euvattest.comparator.VatRateComparatorFactory;
import home.barclays.euvattest.dao.DataAccessObject;
import home.barclays.euvattest.domain.RateType;
import home.barclays.euvattest.service.VatRateService;
import home.barclays.euvattest.dao.impl.JSONFromUrlDAO;
import home.barclays.euvattest.domain.VatRate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VatRateServiceUrlFromJSON implements VatRateService {

    private DataAccessObject dao;

    public VatRateServiceUrlFromJSON(DataAccessObject dao) {
        this.dao = dao;
    }

    @Override
    public List<VatRate> getListOfTheHighestLowestVATCountries(int numberOfItemsToShow, boolean ascending,
                                                               RateType rateType) {

        List<VatRate> list = dao.getAll();

        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        Comparator<VatRate> comparator = VatRateComparatorFactory.getVatRateComparator(rateType);

        if (ascending) {
            list.sort(comparator.reversed());
        } else {
            list.sort(comparator);
        }

        return list.stream().limit(numberOfItemsToShow).collect(Collectors.toList());
    }

    public DataAccessObject getDao() {
        return dao;
    }

    public void setDao(JSONFromUrlDAO dao) {
        this.dao = dao;
    }

}