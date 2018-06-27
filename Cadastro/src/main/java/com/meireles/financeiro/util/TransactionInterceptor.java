package com.meireles.financeiro.util;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;

/**
 * Classe interceptadora.
 * Este interceptador precisa ser registrado no beans.xml
 */
@Interceptor
@Transational //Interface criada Transational
public class TransactionInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Inject EntityManager manager;

    @AroundInvoke
    public Object invoke (InvocationContext context) throws Exception {
        EntityTransaction transaction = manager.getTransaction();
        boolean criador = false;

        try{
            if (!transaction.isActive()){
                transaction.begin();
                transaction.rollback(); //p/ desfazer qualquer operação.

                transaction.begin();
                criador = true;
            }
            return context.proceed();

        } catch ( Exception e ){
            if (transaction != null && criador){
                transaction.rollback();
            }
            throw e;
        } finally {
            if (transaction != null && transaction.isActive() && criador){
                transaction.commit();
            }
        }
    }
}
