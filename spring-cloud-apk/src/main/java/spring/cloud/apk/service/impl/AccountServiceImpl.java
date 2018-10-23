package spring.cloud.apk.service.impl;

import base.BConstants;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import spring.cloud.apk.dataaccess.AccountDataAccess;
import spring.cloud.apk.dataaccess.dataobject.AccountDo;
import spring.cloud.apk.service.AccountService;
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
    public ResultModel<AccountModel> detail(String userId) {

        System.out.println(BConstants.ABC);

        System.out.println("AccountServiceImpl.validateUserIdAndPassword() userid=" + userId);

        String debug = "";

        ResultModel<AccountModel> rst;

        if (Strings.isNullOrEmpty(userId)) {
            debug += "1,";
            rst = ResultModel.createFail("invalidParam");
        } else {
            debug += "2,";
            Optional<AccountDo> accountDoOp = this.accountDataAccess.selectByPrimaryKey(userId);
            if (!accountDoOp.isPresent()) {
                LOGGER.error("traceId:{}, AccountService.detail, user does not exist, userId:{}", TraceIdHelper.getTraceId(), userId);
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
    public ResultModel<String> validateUserIdAndPassword(String userId, String password) {

        System.out.println(BConstants.ABC);

        System.out.println("AccountController.validateUserIdAndPassword() userid=" + userId + ", password=" + password);

        ResultModel<String> rst;

        if (Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(password)) {
            rst = ResultModel.createFail("invalidParam");
        } else {
            userId = userId.trim();
            password = password.trim();

            Optional<AccountDo> accountDoOp = this.accountDataAccess.selectByPrimaryKey(userId);
            if (!accountDoOp.isPresent()) {
                LOGGER.error("traceId:{}, AccountService.detail, user does not exist, userId:{}", TraceIdHelper.getTraceId(), userId);
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