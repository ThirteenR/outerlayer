package com.rsq.wind.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Author: shaoqing
 * date-time: 2020-10-26 22:06
 **/
@Configuration
@MapperScan(basePackages = SlaveDataSource.BASE_PACKAGES, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSource {
    public static final String BASE_PACKAGES = "com.rsq.wind.slave.dao";
    @Value("${spring.datasource.slave.url}")
    private String dbUrl;

    @Value("${spring.datasource.slave.username}")
    private String username;

    @Value("${spring.datasource.slave.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${mybatis.slaveMapperLocations}")
    private String mapperLocation;

    @Bean(name="slaverDataSource")   //声明其为Bean实例
//    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource slaverDataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        return datasource;
    }

    @Bean(name = "slaveTransactionManager")
//    @Primary
    public DataSourceTransactionManager slaveTransactionManager() {
        return new DataSourceTransactionManager(slaverDataSource());
    }

    @Bean(name = "slaveSqlSessionFactory")
//    @Primary
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaverDataSource") DataSource slaverDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(slaverDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocation));
        //mybatis 数据库字段与实体类属性驼峰映射配置
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }




}
