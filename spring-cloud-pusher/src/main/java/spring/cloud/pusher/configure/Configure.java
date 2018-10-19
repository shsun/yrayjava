package spring.cloud.pusher.configure;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import spring.cloud.pusher.quartz.XAdCampaign2RedisJob;
import spring.cloud.pusher.quartz.XAdFeedbackJob;

/**
 * @author shsun
 */
@Configuration
public class Configure {

    /**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "myFirstExerciseJobBean")
    public MethodInvokingJobDetailFactoryBean myFirstExerciseJobBean(XAdCampaign2RedisJob job) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false); // 是否并发
        jobDetail.setName("general-myFirstExerciseJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setTargetObject(job); // 被执行的对象
        jobDetail.setTargetMethod("myJobBusinessMethod"); // 被执行的方法
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "myFirstExerciseJobTrigger")
    public CronTriggerFactoryBean myFirstExerciseJobTrigger(@Qualifier("myFirstExerciseJobBean") MethodInvokingJobDetailFactoryBean bean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(bean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-myFirstExerciseJobTrigger");
        return tigger;
    }

    /**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "mySecondExerciseJobBean")
    public MethodInvokingJobDetailFactoryBean mySecondExerciseJobBean(XAdFeedbackJob job) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false); // 是否并发
        jobDetail.setName("general-mySecondExerciseJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setTargetObject(job); // 被执行的对象
        jobDetail.setTargetMethod("myJobBusinessMethod"); // 被执行的方法
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "mySecondExerciseJobTrigger")
    public CronTriggerFactoryBean mySecondExerciseJobTrigger(@Qualifier("mySecondExerciseJobBean") MethodInvokingJobDetailFactoryBean bean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(bean.getObject());
        tigger.setCronExpression("0/10 * * * * ?");
        tigger.setName("general-mySecondExerciseJobTrigger");
        return tigger;
    }
}


