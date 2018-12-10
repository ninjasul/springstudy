package me.ninjasul.mongo;

import com.google.gson.Gson;
import me.ninjasul.mongo.account.Account;
import me.ninjasul.mongo.account.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootApplication
public class NinjasulSpringBootMongoApplication {

    private static final Logger logger = LoggerFactory.getLogger(NinjasulSpringBootMongoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NinjasulSpringBootMongoApplication.class, args);
    }

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Bean
    public ApplicationRunner applicationRunner() {

        return args -> {
           /* Account account = new Account();
            account.setUsername("dy");
            account.setEmail("ninjasul@gmail.com");

            mongoTemplate.insert(account);*/


            List<Account> accounts = accountRepository.findAll();

            for( Account curAccount : accounts ) {
                logger.info(new Gson().toJson(curAccount));
            }

            logger.info("finished");
        };
    }
}
