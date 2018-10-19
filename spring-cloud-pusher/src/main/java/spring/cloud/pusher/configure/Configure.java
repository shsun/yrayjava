package spring.cloud.pusher.configure;

import org.quartz.Scheduler;
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
import spring.cloud.pusher.quartz.XAdJobFactory;
import spring.cloud.pusher.quartz.XAdCampaign2RedisJob;
import spring.cloud.pusher.quartz.XAdFeedbackJob;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import java.io.IOException;
import java.util.Properties;

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


    @Autowired
    private XAdJobFactory myJobFactory;


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(myJobFactory);
        System.out.println("myJobFactory:" + myJobFactory);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }


    /*
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

    @Bean(name = "campaign2RedisJob")
    public JobDetailFactoryBean campaign2RedisJobBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setName("general-campaign2RedisJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setJobClass(XAdCampaign2RedisJob.class);
        jobDetail.setDurability(true);
        return jobDetail;
    }

    @Bean(name = "campaign2RedisJobTrigger")
    public CronTriggerFactoryBean campaign2RedisJobTrigger(@Qualifier(value = "campaign2RedisJob") JobDetailFactoryBean myFirstExerciseJobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(myFirstExerciseJobBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-campaign2RedisJob");
        return tigger;

    }

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

    @Bean(name = "feedbackJobTrigger")
    public CronTriggerFactoryBean feedbackJobJobBeanTrigger(@Qualifier(value = "feedbackJob") JobDetailFactoryBean myFirstExerciseJobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(myFirstExerciseJobBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-feedbackJob");
        return tigger;
    }
    */
}


