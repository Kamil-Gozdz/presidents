package com.presidents.repository;

import com.presidents.model.entity.President;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PresidentRepositoryTest {

    @Autowired
    PresidentsRepository presidentsRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void shouldFindAllPresidentsWhenSearchForAll() {
        //given
        testEntityManager.persist(President.builder()
                .name("jacek")
                .surname("placek")
                .politicalParty("party1")
                .build());
        //when
        var presidents = presidentsRepository.findAll();

        //then
        Assertions.assertEquals(1, presidents.size());
    }
    @Test
    void shouldFindPresidentsByNameWhenNameIsProvidedCorrectly() {
        //given
        testEntityManager.persist(President.builder()
                .name("jacek")
                .surname("placek")
                .politicalParty("party1")
                .build());
        //when
        var presidents = presidentsRepository.findPresidentsByName("jacek");

        //then
        Assertions.assertEquals(1, presidents.size());
    }

}
