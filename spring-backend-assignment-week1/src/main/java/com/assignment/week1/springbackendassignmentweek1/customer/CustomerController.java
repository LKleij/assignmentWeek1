package com.assignment.week1.springbackendassignmentweek1.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("customers")
    public List<CustomerDTO> displayPaymentCustomers(
            @RequestParam String type,
            @RequestBody CustomerListDTO customerList
    ) {
        return customerService.getCustomersByType(customerList.getCustomers(), type);
    }
}
