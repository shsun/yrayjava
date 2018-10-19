package spring.cloud.gateway.feignService;

import org.springframework.stereotype.Component;
import spring.cloud.client.model.AccountModel;
import spring.cloud.demo.model.ResultModel;

@Component
public class XAdAccountErrorService implements AccountFeignService {


    @Override
    public ResultModel<AccountModel> detailByUserId(String userId) {

        ResultModel<AccountModel> rst;

        AccountModel accountModel = new AccountModel();
        accountModel.setUserName("mock-namee");
        rst = ResultModel.createSuccess(accountModel);

        return rst;
    }

    @Override
    public ResultModel<String> validateUserIdAndPassword(String userId, String password) {

        ResultModel<String> rst;
        rst = ResultModel.createSuccess();

        return rst;
    }

}
