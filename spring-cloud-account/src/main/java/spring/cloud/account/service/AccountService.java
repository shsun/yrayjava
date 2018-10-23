package spring.cloud.account.service;

import spring.cloud.client.model.AccountModel;
import spring.cloud.demo.model.ResultModel;

public interface AccountService {

	ResultModel<AccountModel> detail(String userName);

	ResultModel<String> validateUserIdAndPassword(String userName, String password);
}
