package spring.cloud.gateway.feignService;

import org.springframework.stereotype.Component;
import spring.cloud.client.model.MomentModel;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;

@Component
public class XAdMomentErrorService implements MomentFeignService {
    @Override
    public ListResultModel<MomentModel> listFirstPageMoment(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public ResultModel<MomentModel> addMoment(String userId, String content) {
        return null;
    }
}
