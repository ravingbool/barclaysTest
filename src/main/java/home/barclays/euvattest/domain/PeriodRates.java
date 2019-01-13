package home.barclays.euvattest.domain;

import java.util.Objects;

/**
 * The class represents the VAT rate per a period
 *
 * @author Maksim Bulankin
 * */
public class PeriodRates extends AbstractEntity {

    /**we only interested in standard rate so it is only one field here*/
    private float standard;

    public float getStandard() {
        return standard;
    }

    public void setStandard(float standard) {
        this.standard = standard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodRates that = (PeriodRates) o;
        return Float.compare(that.standard, standard) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(standard);
    }
}