package com.assignment.week1.springbackendassignmentweek1.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService underTest;

    @Test
    public void itShouldReturnCustomersBasedOnAccountType() {

        CustomerDTO customer1 = new CustomerDTO("John David", "Savings");
        CustomerDTO customer2 = new CustomerDTO("Abraham William", "Savings");
        CustomerDTO customer3 = new CustomerDTO("Kevin Peterson", "Payments");
        CustomerDTO customer4 = new CustomerDTO("David Warner", "Payments");
        CustomerDTO customer5 = new CustomerDTO("Dwayne Bravo", "Savings");
        CustomerListDTO customers = new CustomerListDTO(
                Arrays.asList(customer1, customer2, customer3, customer4, customer5)
        );

        List<CustomerDTO> customersPaymentsExpected = Arrays.asList(customer3, customer4);
        List<CustomerDTO> customersPaymentsActual = underTest.getCustomersByType(
                customers.getCustomers(),
                "Payments"
        );

        assertThat(customersPaymentsActual).isEqualTo(customersPaymentsExpected);
    }

}