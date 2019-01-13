package domain;

import home.barclays.euvattest.domain.Period;
import home.barclays.euvattest.domain.PeriodRates;
import home.barclays.euvattest.domain.VatRate;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class VatRateTest {

    @Test
    public void currentPeriodTest() {

        VatRate rate = new VatRate();
        rate.setCountryName("bla bla");

        Period period1 = new Period();
        period1.setEffectiveFrom(LocalDate.now());

        PeriodRates periodRates1 = new PeriodRates();
        periodRates1.setStandard(11);

        Period period2 = new Period();
        period2.setEffectiveFrom(period1.getEffectiveFrom().minusYears(1));

        PeriodRates periodRates2 = new PeriodRates();
        periodRates2.setStandard(10);

        rate.getPeriods().add(period1);
        rate.getPeriods().add(period2);

        Assert.assertEquals(rate.getCurrentPeriod(), period1);
        Assert.assertEquals(rate.getCurrentPeriod().getEffectiveFrom(), period1.getEffectiveFrom());
    }

    @Test
    public void currentPeriodOnlyOnePeriodTest() {

        VatRate rate = new VatRate();
        rate.setCountryName("bla bla");

        Period period1 = new Period();
        period1.setEffectiveFrom(LocalDate.now());

        PeriodRates periodRates1 = new PeriodRates();
        periodRates1.setStandard(11);

        rate.getPeriods().add(period1);

        Assert.assertEquals(period1.getEffectiveFrom(), rate.getCurrentPeriod().getEffectiveFrom());
    }

    @Test
    public void currentPeriodOnlyNoPeriodsTest() {
        VatRate rate = new VatRate();
        rate.setCountryName("bla bla");

        Assert.assertNull(rate.getCurrentPeriod());
    }

}