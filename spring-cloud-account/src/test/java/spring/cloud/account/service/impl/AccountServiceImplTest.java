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
import spring.cloud.client.model.AccountModel;
import spring.cloud.demo.model.ResultModel;

/**
 * @author sh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AccountApplication.class)
public class AccountServiceImplTest {
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
        ResultModel<AccountModel> resultModel = accountService.detail("100000");

        Assert.assertEquals();
    }
}
