package home.barclays.euvattest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The class represents a VAT rate and all its periods for particular country
 * some of the fields from source are omitted for brevity
 *
 * @author Maksim Bulankin
 */
public class VatRate extends AbstractEntity {

    private String countryName;

    /**
     * for this test task it is enough to use only a standard rate, but it can be able to add the other rates from the source
     */
    private Double standardRate;

    public String getCountryName() {
        return countryName;
    }

    @JsonSetter("name")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(Double standardRate) {
        this.standardRate = standardRate;
    }

    @JsonProperty("periods")
    private void unpackNested(List<Map<String, Object>> periods) {
        if (periods.isEmpty()) {
            return;
        }
        Map<String, Object> map = periods.get(0);
        Map<String, Object> mapRates = (Map<String, Object>) map.get("rates");

        if (mapRates.isEmpty()) {
            return;
        }
        // below can also be the other rates, like super, reduces etc
        this.standardRate = (Double) mapRates.get("standard");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VatRate vatRate = (VatRate) o;
        return countryName.equals(vatRate.countryName) &&
                standardRate.equals(vatRate.standardRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName, standardRate);
    }
}