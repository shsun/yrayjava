package spring.cloud.dsp.config.datasourceConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class XAdSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.xad")
    public DataSource xadDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean
    public DataSourceTransactionManager xadTxManager(@Qualifier("xadDataSource") DataSource appDataSource) {
        return new DataSourceTransactionManager(appDataSource);
    }

}