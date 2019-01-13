package home.barclays.euvattest.dao;

import java.util.List;

/**
 * The interface provides the access to data from different possible sources
 *
 * @author Maksim Bulankin
 * */
public interface DataAccessObject<T> {

    /**
     * Get all items from the source
     * */
    List<T> getAll();
}