package com.ninjasul.tobyspring31.user.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TransactionHandler implements InvocationHandler {

    @Setter
    Object target;

    @Autowired
    @Setter
    protected PlatformTransactionManager transactionManager;

    @Setter
    private String pattern;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if( method.getName().startsWith(pattern) ) {
            return invokeInTransaction( method, args );
        }
        else {
            return method.invoke(target, args);
        }
    }

    private Object invokeInTransaction(Method method, Object[] args) throws Throwable {

        TransactionStatus status = beginTransaction();

        try {
            Object ret = method.invoke(target, args);
            transactionManager.commit(status);
            return ret;
        }
        catch( InvocationTargetException e ) {
            transactionManager.rollback(status);
            throw e.getTargetException();
        }

    }

    private TransactionStatus beginTransaction() throws Exception {
        return transactionManager.getTransaction(new DefaultTransactionDefinition());
    }
}