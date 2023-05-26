/*
 * bdd-spring-selenium
 * Mail
 * @since 26/05/2023
 */
package com.example.bddspringselenium.model;

import lombok.Data;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Data
public class Mail {

    String to;

    String subject;

    String body;
}
