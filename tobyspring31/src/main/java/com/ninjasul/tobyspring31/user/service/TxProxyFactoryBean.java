package com.ninjasul.tobyspring31.user.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import java.lang.reflect.Proxy;

/**
 * Transaction Handler를 Bean으로 등록하기 위해 FactoryBean을 구현
 */
@Setter
@Getter
public class TxProxyFactoryBean implements FactoryBean<Object> {

    Object target;

    PlatformTransactionManager transactionManager;

    String pattern;

    Class<?> serviceInterface;

    @Override
    public Object getObject() throws Exception {
        return createProxiedInstance(getTransactionHandler());
    }

    private TransactionHandler getTransactionHandler() {
        TransactionHandler txHandler = new TransactionHandler();
        txHandler.setTarget(target);
        txHandler.setTransactionManager(transactionManager);
        txHandler.setPattern(pattern);
        return txHandler;
    }

    private Object createProxiedInstance(TransactionHandler txHandler) {
        return Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] { serviceInterface },
                txHandler
        );
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}

