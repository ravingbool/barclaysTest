package home.barclays.euvattest.service.impl;

import home.barclays.euvattest.dao.DataAccessObject;
import home.barclays.euvattest.domain.RateType;
import home.barclays.euvattest.service.VatRateService;
import home.barclays.euvattest.domain.VatRate;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class VatRateServiceUrlFromJSON implements VatRateService {

    private DataAccessObject dao;

    public VatRateServiceUrlFromJSON(DataAccessObject dao) {
        this.dao = dao;
    }

    @Override
    public Map<Double, List<VatRate>> getTheHighestLowestVATCountries(int numberOfItemsToShow, boolean ascending, RateType rateType) {

        List<VatRate> list = dao.getAll();

        if (list.isEmpty()) {
            return new HashMap<>();
        }

        Comparator<Double> comparator = ascending ? Comparator.naturalOrder() : Comparator.reverseOrder();
        Collector collector = null;

        // can be extended later by editing the cases
        switch (rateType) {
            case STANDARD:
                collector = groupingBy(VatRate::getStandardRate);
            default:
                collector = groupingBy(VatRate::getStandardRate);
        }

        Map<Double, List<VatRate>> groupedByVATRate = (Map<Double, List<VatRate>>) list.stream().collect(collector);
        List<Double> keys = groupedByVATRate.keySet().stream().sorted(comparator).limit(numberOfItemsToShow).
                collect(Collectors.toList());

        Map<Double, List<VatRate>> result = new LinkedHashMap<>();
        keys.forEach(item -> result.put(item, groupedByVATRate.get(item)));

        return result;
    }

}