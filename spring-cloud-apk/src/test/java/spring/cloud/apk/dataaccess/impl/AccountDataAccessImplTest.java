package spring.cloud.apk.dataaccess.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.cloud.apk.BaseIntegrationTest;
import spring.cloud.apk.dataaccess.dataobject.AccountDo;

import java.util.Optional;

/**
 * @author sh
 */
public class AccountDataAccessImplTest extends BaseIntegrationTest {
    @Autowired
    private AccountDataAccessImpl accountDataAccess;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void selectByPrimaryKey() {
        String userId = "100000";

        Optional<AccountDo> accountDoOp = this.accountDataAccess.selectByPrimaryKey(userId);
        if (!accountDoOp.isPresent()) {
            Assert.assertTrue("no-this-user", false);
        } else {
            AccountDo data = accountDoOp.get();
            Assert.assertEquals(userId, data.getUserId());
            Assert.assertEquals("admin", data.getUserName());
        }
    }
}
