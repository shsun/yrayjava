package spring.cloud.biz.service;

import spring.cloud.biz.dataaccess.dataobject.ZMomentDo;
import spring.cloud.client.model.MomentModel;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;

/**
 * Created by Harry on 15/12/2017.
 */
public interface MomentService {
    /**
     * retrieve all moment
     *
     * @param page
     * @param pageSize
     * @return
     */
    ListResultModel<ZMomentDo> listFirstPageMoment(Integer page, Integer pageSize);

    /**
     * add one momnet
     *
     * @param userId
     * @param content
     * @return
     */
    ResultModel<ZMomentDo> addMoment(String userId, String content);
}
