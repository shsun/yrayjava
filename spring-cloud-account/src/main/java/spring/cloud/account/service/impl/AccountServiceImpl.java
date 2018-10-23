package spring.cloud.account.service.impl;

import base.BConstants;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import spring.cloud.account.dataaccess.AccountDataAccess;
import spring.cloud.account.dataaccess.dataobject.AccountDo;
import spring.cloud.account.service.AccountService;
import spring.cloud.client.model.AccountModel;
import spring.cloud.client.uitils.CopyProperityUtils;
import spring.cloud.demo.model.ResultModel;
import spring.cloud.demo.model.TraceIdHelper;

import java.util.Optional;

@Service("accountService")
@RefreshScope
public class AccountServiceImpl implements AccountService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDataAccess accountDataAccess;

    @Override
    public ResultModel<AccountModel> detail(String userName) {

        System.out.println(BConstants.ABC);

        System.out.println("AccountServiceImpl.validateUserIdAndPassword() userName=" + userName);

        String debug = "";

        ResultModel<AccountModel> rst;

        if (Strings.isNullOrEmpty(userName)) {
            debug += "1,";
            rst = ResultModel.createFail("invalidParam");
        } else {
            debug += "2,";
            Optional<AccountDo> accountDoOp = this.accountDataAccess.selectByPrimaryKey(userName);
            if (!accountDoOp.isPresent()) {
                LOGGER.error("traceId:{}, AccountService.detail, user does not exist, userName:{}", TraceIdHelper.getTraceId(), userName);
                rst = ResultModel.createFail("noThisUser");
            } else {
                AccountModel accountModel = new AccountModel();
                CopyProperityUtils.copyAllProperies(accountDoOp.get(), accountModel);
                rst = ResultModel.createSuccess(accountModel);
            }
        }
        return rst;
    }

    @Override
    public ResultModel<String> validateUserIdAndPassword(String userName, String password) {

        System.out.println(BConstants.ABC);

        System.out.println("AccountController.validateUserIdAndPassword() userName=" + userName + ", password=" + password);

        ResultModel<String> rst;

        if (Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(password)) {
            rst = ResultModel.createFail("invalidParam");
        } else {
            userName = userName.trim();
            password = password.trim();

            Optional<AccountDo> accountDoOp = this.accountDataAccess.selectByPrimaryKey(userName);
            if (!accountDoOp.isPresent()) {
                LOGGER.error("traceId:{}, AccountService.detail, user does not exist, userName:{}", TraceIdHelper.getTraceId(), userName);
                //hide the error msg
                rst = ResultModel.createFail("wrongUserOrPwd");
            } else {
                AccountDo accountDo = accountDoOp.get();
                if (password.equals(accountDo.getPassword())) {
                    rst = ResultModel.createSuccess();
                } else {
                    rst = ResultModel.createFail("wrongUserOrPwd");
                }
            }
        }
        return rst;
    }
}