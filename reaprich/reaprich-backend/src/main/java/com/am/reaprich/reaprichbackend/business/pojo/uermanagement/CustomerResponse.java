package com.am.reaprich.reaprichbackend.business.pojo.uermanagement;

import com.am.reaprich.reaprichbackend.data.entities.actors.Customer;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerResponse {
    private Customer customer;
    private String error;
}
