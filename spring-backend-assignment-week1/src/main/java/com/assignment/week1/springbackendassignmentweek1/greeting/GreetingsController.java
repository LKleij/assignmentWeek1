package com.assignment.week1.springbackendassignmentweek1.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingsController {

    @Autowired
    private GreetingsService greetingsService;

    @GetMapping(path = "greetings/{name}")
    public String sayHiToUserUsingPathParam(@PathVariable("name") String name) {
        return greetingsService.sayHiWithNameOnly(name);
    }

    @GetMapping(path = "greetingsByQueryParam")
    public String sayHiToUserUsingRequestParam(@RequestParam String name) {
        return greetingsService.sayHiWithNameOnly(name);
    }

    @PostMapping("greetingsByPost")
    public String sayHiToUserUsingJson(@RequestBody UserDTO user) {
        return greetingsService.sayWelcomeWithNameAndDepartment(
                user.userInfoDTO.getName(),
                user.userInfoDTO.getDepartment()
        );
    }
}
