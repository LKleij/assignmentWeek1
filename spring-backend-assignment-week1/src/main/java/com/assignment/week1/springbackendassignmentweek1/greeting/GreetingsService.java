package com.assignment.week1.springbackendassignmentweek1.greeting;

import org.springframework.stereotype.Service;

@Service
public class GreetingsService {

    public String sayHiWithNameOnly(String name) {
        return String.format("Hello, %s!", name);
    }

    public String sayWelcomeWithNameAndDepartment(String name, String department) {
        String[] firstAndLastName = name.split(" ");
        return String.format("Welcome %s from %s department!", firstAndLastName[0], department);
    }

}
