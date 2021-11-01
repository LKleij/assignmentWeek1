package com.assignment.week1.springbackendassignmentweek1.greeting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = GreetingsController.class)
public class GreetingsControllerTest {

    @MockBean
    private GreetingsService greetingsService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void itShouldDisplayGreetingUsingPathParam() throws Exception {

        when(greetingsService.sayHiWithNameOnly(anyString()))
                .thenAnswer(i -> String.format("Hello, %s!", i.getArguments()[0]));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/greetings/{name}", "Lucas")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andReturn();

        System.out.println("Output:\n" + result.getResponse().getContentAsString());
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Hello, Lucas!");
    }

    @Test
    public void itShouldDisplayGreetingUsingQueryParam() throws Exception {

        when(greetingsService.sayHiWithNameOnly(anyString()))
                .thenAnswer(i -> String.format("Hello, %s!", i.getArguments()[0]));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/greetingsByQueryParam")
                .queryParam("name", "Lucas")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andReturn();

        System.out.println("Output:\n" + result.getResponse().getContentAsString());
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Hello, Lucas!");
    }

    @Test
    public void itShouldDisplayGreetingUsingPostRequest() throws Exception {

        when(greetingsService.sayWelcomeWithNameAndDepartment(anyString(), anyString()))
                .thenAnswer(i -> {
                    String[] firstAndLastName = ((String) i.getArguments()[0]).split(" ");
                    return String.format("Welcome %s from %s department!",
                            firstAndLastName[0],
                            i.getArguments()[1]
                    );
                });

        UserInfoDTO userInfo = new UserInfoDTO("John David", "Admin");
        UserDTO user = new UserDTO(userInfo);

        System.out.println("POST body:\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
        RequestBuilder request = MockMvcRequestBuilders
                .post("/greetingsByPost")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andReturn();

        System.out.println("Output:\n" + result.getResponse().getContentAsString());
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Welcome John from Admin department!");
    }
}
