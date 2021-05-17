package br.com.jadson.integrationtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntegrationTestApplicationTest {

    @Test
    void test(){
        Assertions.assertTrue(true);
    }

}