package com.assignment.week1.springbackendassignmentweek1.customer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void itShouldDisplayCustomersBasedOnAccountType() throws Exception {
        when(customerService.getCustomersByType(any(), anyString()))
                .thenAnswer(i -> {
                    String accountType = i.getArguments()[1].toString();
                    List<CustomerDTO> customers = (List<CustomerDTO>) i.getArguments()[0]; // given that type is of List<CustomerDTO>

                    return customers.stream()
                            .filter(customer -> customer.getAccountType().equals(accountType))
                            .collect(Collectors.toList());
                });

        CustomerDTO customer1 = new CustomerDTO("John David", "Savings");
        CustomerDTO customer2 = new CustomerDTO("Abraham William", "Savings");
        CustomerDTO customer3 = new CustomerDTO("Kevin Peterson", "Payments");
        CustomerDTO customer4 = new CustomerDTO("David Warner", "Payments");
        CustomerDTO customer5 = new CustomerDTO("Dwayne Bravo", "Savings");
        CustomerListDTO customers = new CustomerListDTO(
                Arrays.asList(customer1, customer2, customer3, customer4, customer5)
        );

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        System.out.println("POST body:\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customers));

        RequestBuilder request = MockMvcRequestBuilders
                .post("/customers")
                .content(objectMapper.writeValueAsString(customers))
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("type", "Payments")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andReturn();

        List<CustomerDTO> customersPaymentsExpected = Arrays.asList(customer3, customer4);
        List<CustomerDTO> customersPaymentsActual = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {}
        );

        System.out.println("Output:\n" + result.getResponse().getContentAsString());
        assertThat(customersPaymentsActual).isEqualTo(customersPaymentsExpected);
    }


}
