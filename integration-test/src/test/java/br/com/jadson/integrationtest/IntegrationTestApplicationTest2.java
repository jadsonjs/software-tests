package br.com.jadson.integrationtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("test")
class IntegrationTestApplicationTest2 {

    @Autowired
    AmbienteBean ambienteBean;

    @Test
    void test(){
        Assertions.assertEquals("Test", ambienteBean.print());
    }


    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * This method test data recovery from datasource
     */
    @Test
    @Sql(statements = "delete from ALUNO ", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql({"/insert1.sql", "/insert2.sql"})
    @Sql(statements = "delete from ALUNO ", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testeDatabaseData(){
        int qtdAlunos = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM ALUNO", Integer.class);

        Assertions.assertEquals(4, qtdAlunos);

    }

}

