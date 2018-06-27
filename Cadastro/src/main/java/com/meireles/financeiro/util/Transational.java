package com.meireles.financeiro.util;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação associativa à classe/metodo da interceptada.
 *
 **/
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.TYPE, ElementType.METHOD} )
public @interface Transational {
}
