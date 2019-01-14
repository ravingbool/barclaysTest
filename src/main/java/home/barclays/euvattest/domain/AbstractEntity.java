package home.barclays.euvattest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The abstract class contains all the common features for the entities in app can be fulfilled with fields etc
 *
 * @author Maksim Bulankin
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractEntity {
}