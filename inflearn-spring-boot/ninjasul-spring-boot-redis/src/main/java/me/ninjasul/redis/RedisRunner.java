package me.ninjasul.redis;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Properties;

@Component
public class RedisRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(RedisRunner.class);

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set("dy", "ninjasul");
        values.set("springboot", "2.0");
        values.set("hello", "world");

        Account account = new Account();
        account.setEmail("ninjasul@gmail.com");
        account.setUsername("dy");

        accountRepository.save(account);

        Optional<Account> byId = accountRepository.findById(account.getId());

        logger.info(byId.get().getUsername());
        logger.info(byId.get().getEmail());
    }
}