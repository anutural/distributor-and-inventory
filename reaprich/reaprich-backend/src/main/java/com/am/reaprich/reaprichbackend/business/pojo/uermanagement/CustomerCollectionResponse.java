package com.am.reaprich.reaprichbackend.business.pojo.uermanagement;

import com.am.reaprich.reaprichbackend.data.entities.actors.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerCollectionResponse {
    private List<Customer> customers;
    private String error;
}
