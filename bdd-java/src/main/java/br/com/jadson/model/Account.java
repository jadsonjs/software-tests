/*
 * bdd-java
 * Belly
 * @since 01/12/2022
 */
package br.com.jadson.model;

import br.com.jadson.exceptions.BusinessException;
import lombok.Data;

/**
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Data
public class Account {

    private Long id;

    private Double balance = 0.0d;


    public Account(Double balance){
        if(balance == null || balance < 0 )
            throw new IllegalArgumentException("Invalid balance");
        this.balance = balance;
    }

    public Account(String owner, Integer number, Double balance) {
        this(balance);
    }


    public boolean hasBalence(){
        return balance > 0 ;
    }

    public double withdraw(double value){
        if(value < 0)
            throw new IllegalArgumentException("You can not withdraw a negative value");

        balance = balance - value;

        if(balance < 0)
            throw new BusinessException("Overdraft limit exceeded.");

        return balance;
    }

    public double deposit(Double valor) {
        if(valor < 0)
            throw new IllegalArgumentException("You can not deposit a negative value");

        balance = balance + valor;
        return balance;
    }

}
