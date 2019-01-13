package home.barclays.euvattest.comparator;

import home.barclays.euvattest.domain.VatRate;

import java.util.Comparator;

/** the comparator which helps to compare countries with standard VAT rate for the current period
 *
 * @author Maksim Bulankin
 * */
public class StandardVatRateComparator implements Comparator<VatRate> {

    @Override
    public int compare(VatRate item1, VatRate item2) {
        if (item1.getCurrentPeriod().getRates().getStandard() == item2.getCurrentPeriod().getRates().getStandard()) {
            return 0;
        }
        return item1.getCurrentPeriod().getRates().getStandard() > item2.getCurrentPeriod().getRates().getStandard() ? -1 : 1;
    }
}