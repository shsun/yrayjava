package spring.cloud.pusher.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import spring.cloud.demo.cache.CacheService;

/**
 *
 */
@Component
@EnableScheduling
public class XAdCampaign2RedisJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(XAdCampaign2RedisJob.class);


    @Autowired
    private CacheService cacheService;

    private static int KEY = 0;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("XAdCampaign2RedisJob???????????????????????????????????????????????????????????????????????");
        
        KEY += 1;

        try {
            cacheService.putString("XAdCampaign2RedisJob__" + KEY, "value_" + KEY);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }


    }
}
