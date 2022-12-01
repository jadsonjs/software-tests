/*
 * bdd-java
 * BusinessException
 * @since 01/12/2022
 */
package br.com.jadson.exceptions;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String s) {
        super(s);
    }
}
