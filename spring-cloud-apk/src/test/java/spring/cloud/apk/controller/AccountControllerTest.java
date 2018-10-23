package spring.cloud.apk.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;

import spring.cloud.apk.BaseIntegrationTest;
import spring.cloud.apk.dataaccess.dataobject.AccountDo;
import spring.cloud.client.model.AccountModel;
import spring.cloud.demo.model.ResultModel;

public class AccountControllerTest extends BaseIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setupMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); // 初始化MockMvc对象
        session = new MockHttpSession();

        AccountDo user = new AccountDo();
        user.setUserId("100000");
        user.setPassword("123456");
        // inject an user to avoid interceptor
        session.setAttribute("user", user);
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void detailByUserId() throws Exception {
        String userId = "100000";
        String tmpURL = super.url + "apk/detail?userId=" + userId;
        // UT with getForObject
        try {
            ResultModel<JSONObject> resultModel = restTemplate.getForObject(tmpURL, ResultModel.class);
            AccountModel data = resultModel.getData().toJavaObject(AccountModel.class);
            Assert.assertEquals(userId, data.getUserId());
            Assert.assertEquals("admin", data.getUserName());
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage(), false);
        }
        // UT with getForEntity
        try {
            ResponseEntity<ResultModel> resultModel = restTemplate.getForEntity(tmpURL, ResultModel.class);
            AccountModel data = ((JSONObject) resultModel.getBody().getData()).toJavaObject(AccountModel.class);
            Assert.assertEquals(userId, data.getUserId());
            Assert.assertEquals("admin", data.getUserName());
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage(), false);
        }
    }

    public void detailByUserId_webmvc() throws Exception {
        String tmpURL = super.url + "/detail?userId=100000";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(tmpURL);
        mockHttpServletRequestBuilder.content(MediaType.ALL_VALUE);
        mockHttpServletRequestBuilder.session(session);
        mockHttpServletRequestBuilder.accept(MediaType.ALL);

        //
        ResultActions resultActions = mvc.perform(mockHttpServletRequestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print());

        System.out.println(resultActions);

    }

}
