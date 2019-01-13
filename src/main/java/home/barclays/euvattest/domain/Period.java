package home.barclays.euvattest.domain;

import home.barclays.euvattest.util.LocalDateDeserializer;
import home.barclays.euvattest.util.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The class represents a period for the VAT rate data
 *
 * @author Maksim Bulankin
 * */
public class Period extends AbstractEntity {

    /** the date which a period is valid from*/
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate effectiveFrom;

    /** the rates for a particular period*/
    private PeriodRates rates;

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    @JsonSetter("effective_from")
    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public PeriodRates getRates() {
        return rates;
    }

    public void setRates(PeriodRates rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return effectiveFrom.equals(period.effectiveFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(effectiveFrom);
    }
}