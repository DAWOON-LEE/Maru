package com.bird.maru.common.config.Quartz;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.spi.JobFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class QuartzConfiguration {

    @Value("${spring.datasource.hikari.jdbc-url}")
    private String dbURL;
    @Value(("${spring.datasource.hikari.username}"))
    private String username;
    @Value(("${spring.datasource.hikari.password}"))
    private String password;
    private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) throws Exception {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        // 스프링에서 Quartz 스케줄러를 빈으로 등록하기 위해 QuartzScheduler를 설정합니다.
        Properties properties = new Properties();

        properties.setProperty("org.quartz.scheduler.instanceName", "MY_CLUSTERED_JOB_SCHEDULER");
        properties.setProperty("org.quartz.scheduler.instanceId", "AUTO");

        // ThreadPool 설정
        properties.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        properties.setProperty("org.quartz.threadPool.threadCount", "10");
        properties.setProperty("org.quartz.threadPool.threadPriority", "5");

        // JDBC JobStore를 사용하도록 설정합니다.
        properties.setProperty("org.quartz.jobStore.misfireThreshold", "60000");
        properties.setProperty("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        properties.setProperty("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        properties.setProperty("org.quartz.jobStore.dataSource", "qzDS");
        properties.setProperty("org.quartz.jobStore.tablePrefix", "QRTZ_");
        properties.setProperty("org.quartz.jobStore.useProperties", "false");

        properties.setProperty("org.quartz.jobStore.isClustered", "true");
        properties.setProperty("org.quartz.jobStore.clusterCheckinInterval", "20000");


        // Datasources 설정
        properties.setProperty("org.quartz.dataSource.qzDS.provider", "hikaricp");
        properties.setProperty("org.quartz.dataSource.qzDS.driver", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("org.quartz.dataSource.qzDS.URL", dbURL);
        properties.setProperty("org.quartz.dataSource.qzDS.user", username);
        properties.setProperty("org.quartz.dataSource.qzDS.password", password);
        properties.setProperty("org.quartz.dataSource.qzDS.maxConnections", "5");
        properties.setProperty("org.quartz.dataSource.qzDS.validationQuery", "select 1");

        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setQuartzProperties(properties);
//        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");

        // 스케줄러가 시작될 때 실행할 리스너를 등록합니다.
        schedulerFactoryBean.setGlobalJobListeners(new MyJobListener());

        // Quartz의 트랜젝션 관리자를 설정합니다.
        schedulerFactoryBean.setTransactionManager(transactionManager);

        return schedulerFactoryBean;
    }

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        return new AutowiringSpringBeanJobFactory(applicationContext);
    }

    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }

    @Bean
    public Scheduler scheduler(JobFactory jobFactory) throws Exception {
        return schedulerFactoryBean(jobFactory).getScheduler();
    }

}
