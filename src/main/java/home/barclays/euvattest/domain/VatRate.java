package home.barclays.euvattest.domain;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * The class represents a VAT rate and all its periods for particular country
 * some of the fields from source are omitted for brevity
 *
 * @author Maksim Bulankin
 * */
public class VatRate extends AbstractEntity {

    private String countryName;
    private List<Period> periods = new ArrayList<>();

    public String getCountryName() {
        return countryName;
    }

    @JsonSetter("name")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    /**
     * @return the current valid period for the VAT rate
     * */
    public Period getCurrentPeriod() {
        if (!periods.isEmpty()) {
            return periods.stream().max(Comparator.comparing(Period::getEffectiveFrom)).orElse(periods.get(0));
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VatRate vatRate = (VatRate) o;
        return countryName.equals(vatRate.countryName) &&
                Objects.equals(periods, vatRate.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName, periods);
    }
}