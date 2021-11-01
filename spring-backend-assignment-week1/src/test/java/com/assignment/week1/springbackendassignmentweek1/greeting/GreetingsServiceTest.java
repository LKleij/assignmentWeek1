package com.assignment.week1.springbackendassignmentweek1.greeting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GreetingsServiceTest {

    @Autowired
    private GreetingsService underTest;

    @Test
    void itShouldReturnHiWithNameOnly() {

        String name = "Lucas";

        assertThat(underTest.sayHiWithNameOnly(name))
                .isEqualTo(String.format("Hello, %s!", name));
    }

    @Test
    void itShouldReturnWelcomeWithNameAndDepartment() {

        String name = "Lucas";
        String department = "Admin";

        assertThat(underTest.sayWelcomeWithNameAndDepartment(name, department))
                .isEqualTo(String.format("Welcome %s from %s department!", name, department));
    }
}