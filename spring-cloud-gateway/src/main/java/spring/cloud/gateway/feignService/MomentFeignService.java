package spring.cloud.gateway.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import spring.cloud.client.model.MomentModel;
import spring.cloud.demo.model.ListResultModel;
import spring.cloud.demo.model.ResultModel;
import spring.cloud.gateway.config.GlobalConstants;

/**
 * Created by Harry on 15/12/2017.
 */
@FeignClient(name = GlobalConstants.BIZ_SERVICE_NAME, path = "/moment", fallback = XAdMomentErrorService.class)
public interface MomentFeignService {

    @GetMapping("/list")
    ListResultModel<MomentModel> listFirstPageMoment(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize
    );

    @PostMapping("")
    ResultModel<MomentModel> addMoment(
            @RequestParam("userId") String userId,
            @RequestParam("content") String content
    );

}
