package spring.cloud.pusher.configure;

import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import spring.cloud.pusher.quartz.XAdCampaign2RedisJob;
import spring.cloud.pusher.quartz.XAdFeedbackJob;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;

/**
 * @author shsun
 */
@Configuration
public class Configure {

    private static final Logger LOGGER = LoggerFactory.getLogger(XAdCampaign2RedisJob.class);

//
//    @Autowired
//    @Qualifier(value = "primaryDataSource")
//    private DataSource primaryDataSource;

    /**
     * 调度器工厂Bean
     */
    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(Trigger... triggers) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();

        Properties p = new Properties();
        try {
            p.load(this.getClass().getClassLoader().getResourceAsStream("quartz.properties"));
        } catch (IOException e) {
            LOGGER.error("加载quartz.properties失败", e);
            throw new Error(e);
        }
        bean.setQuartzProperties(p);
        bean.setOverwriteExistingJobs(true);
        // 延时启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        bean.setStartupDelay(5);
        bean.setTriggers(triggers);
        return bean;
    }


    /**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "campaign2RedisJob")
    public JobDetailFactoryBean campaign2RedisJobBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setName("general-campaign2RedisJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setJobClass(XAdCampaign2RedisJob.class);
        jobDetail.setDurability(true);
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "campaign2RedisJobTrigger")
    public CronTriggerFactoryBean campaign2RedisJobTrigger(@Qualifier(value = "campaign2RedisJob") JobDetailFactoryBean myFirstExerciseJobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(myFirstExerciseJobBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-campaign2RedisJob");
        return tigger;

    }

    /**
     * 方法调用任务明细工厂Bean
     */
    @Autowired
    @Bean(name = "feedbackJob")
    public JobDetailFactoryBean feedbackJobJobBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setName("general-feedbackJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setJobClass(XAdFeedbackJob.class);
        jobDetail.setDurability(true);
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "feedbackJobTrigger")
    public CronTriggerFactoryBean feedbackJobJobBeanTrigger(@Qualifier(value = "feedbackJob") JobDetailFactoryBean myFirstExerciseJobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(myFirstExerciseJobBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-feedbackJob");
        return tigger;
    }
}


