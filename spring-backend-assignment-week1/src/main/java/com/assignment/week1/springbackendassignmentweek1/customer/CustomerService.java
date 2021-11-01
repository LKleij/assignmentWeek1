package com.assignment.week1.springbackendassignmentweek1.customer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    List<CustomerDTO> getCustomersByType(List<CustomerDTO> customers, String accountType) {
        return customers
                .stream()
                .filter(customer -> customer.getAccountType().equals(accountType))
                .collect(Collectors.toList());
    }
}
