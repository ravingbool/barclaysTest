package util;

import home.barclays.euvattest.domain.Period;
import home.barclays.euvattest.domain.PeriodRates;
import home.barclays.euvattest.domain.VatRate;
import home.barclays.euvattest.comparator.StandardVatRateComparator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StandardVatRateVatComparatorTest {

    private List<VatRate> rates = new ArrayList<>();

    @Before
    public void init() {

        VatRate rate = new VatRate();
        rate.setCountryName("bla bla");

        Period period1 = new Period();
        period1.setEffectiveFrom(LocalDate.now());

        PeriodRates periodRates1 = new PeriodRates();
        periodRates1.setStandard(111);

        period1.setRates(periodRates1);

        Period period2 = new Period();
        period2.setEffectiveFrom(period1.getEffectiveFrom().minusYears(3));

        PeriodRates periodRates2 = new PeriodRates();
        periodRates2.setStandard(10);

        period2.setRates(periodRates2);

        rate.getPeriods().add(period1);
        rate.getPeriods().add(period2);

        VatRate rate1 = new VatRate();
        rate1.setCountryName("bla bla1");

        Period period3 = new Period();
        period3.setEffectiveFrom(LocalDate.now().minusYears(1));

        PeriodRates periodRates3 = new PeriodRates();
        periodRates3.setStandard(6);

        period3.setRates(periodRates3);

        Period period4 = new Period();
        period4.setEffectiveFrom(period3.getEffectiveFrom().minusYears(4));

        PeriodRates periodRates4 = new PeriodRates();
        periodRates4.setStandard(10);

        period4.setRates(periodRates4);

        rate1.getPeriods().add(period3);
        rate1.getPeriods().add(period4);

        rates.add(rate1);
        rates.add(rate);
    }

    @Test
    public void test() {
        StandardVatRateComparator comparator = new StandardVatRateComparator();
        rates.sort(comparator);

        Assert.assertEquals("bla bla", rates.get(0).getCountryName());

    }
}