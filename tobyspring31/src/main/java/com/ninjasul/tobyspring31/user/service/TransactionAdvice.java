package com.ninjasul.tobyspring31.user.service;

import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class TransactionAdvice implements MethodInterceptor {

    @Autowired
    @Setter
    PlatformTransactionManager transactionManager;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            Object ret = invocation.proceed();
            transactionManager.commit(status);
            return ret;
        } catch ( RuntimeException e ) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}
