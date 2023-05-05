/*
 * bdd-spring-selenium
 * User
 * @since 04/05/2023
 */
package com.example.bddspringselenium.model;

import lombok.Data;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Data
public class User {

    Long id;

    String email;

    String password;
}
