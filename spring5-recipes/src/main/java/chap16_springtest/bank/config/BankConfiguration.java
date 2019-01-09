package chap16_springtest.bank.config;

import chap16_springtest.bank.dao.AccountDao;
import chap16_springtest.bank.dao.InMemoryAccountDao;
import chap16_springtest.bank.dao.JdbcAccountDao;
import chap16_springtest.bank.service.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class BankConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:h2:mem:bank-test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    /*@Bean
    public InMemoryAccountDao accountDao() {
        return new InMemoryAccountDao();
    }*/

    @Bean
    public AccountDao accountDao() {
        JdbcAccountDao accountDao = new JdbcAccountDao();
        accountDao.setDataSource(dataSource());
        return accountDao;
    }

    @Bean
    public AccountServiceImpl accountService() {
        return new AccountServiceImpl(accountDao());
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}