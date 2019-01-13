package home.barclays.euvattest.comparator;

import home.barclays.euvattest.domain.RateType;
import home.barclays.euvattest.domain.VatRate;

import java.util.Comparator;

public final class VatRateComparatorFactory {

    private VatRateComparatorFactory() {
    }

    public static Comparator<VatRate> getVatRateComparator(RateType rateType) {
        switch (rateType) {
            case STANDARD:
                return new StandardVatRateComparator();
                default:
                    return new StandardVatRateComparator();
        }
    }
}