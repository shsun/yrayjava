package spring.cloud.account.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.cloud.account.AccountApplication;
import spring.cloud.account.BaseIntegrationTest;
import spring.cloud.client.model.AccountModel;
import spring.cloud.demo.model.ResultModel;

/**
 * @author sh
 */
public class AccountServiceImplTest extends BaseIntegrationTest {
    @Autowired
    private AccountServiceImpl accountService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void detail() {
        String userId = "100000";
        ResultModel<AccountModel> resultModel = accountService.detail(userId);

        AccountModel data = resultModel.getData();
        Assert.assertEquals(userId, data.getUserId());
        Assert.assertEquals("admin", data.getUserName());

    }
}
