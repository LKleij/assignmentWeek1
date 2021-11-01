package com.assignment.week1.springbackendassignmentweek1.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListDTO {
    @JsonProperty("customers")
    private List<CustomerDTO> customers;
}
