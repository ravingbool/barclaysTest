package dao;

import home.barclays.euvattest.dao.impl.JSONFromUrlDAO;
import home.barclays.euvattest.domain.VatRate;;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class JSONFromUrlDAOTest {

    @Test
    public void test() {

        JSONFromUrlDAO dao = new JSONFromUrlDAO();
        List<VatRate> rateList = dao.getAll();

        VatRate rate = rateList.get(0);

        Assert.assertEquals("Spain", rate.getCountryName());

    }
}