/*
 * bdd-spring
 * Account
 * @since 20/01/2023
 */
package br.com.jadson.bddspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Data
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
            throw new RuntimeException("Overdraft limit exceeded.");

        return balance;
    }

    public double deposit(Double valor) {
        if(valor < 0)
            throw new IllegalArgumentException("You can not deposit a negative value");

        balance = balance + valor;
        return balance;
    }
}
