package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.impl.CustomerH2Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CustomerH2RepositoryTest {

    private static CustomerRepository customerRepository;

    @BeforeAll
    public static void before() {
        customerRepository = new CustomerH2Repository();
    }

    @Test
    public void createCustomerSuccess() {
        Assertions.assertTrue(customerRepository.createCustomer("name", "email"));
    }

    @Test
    public void getAllSuccess() {
        customerRepository.createCustomer("name", "email");

        List<Customer> customers = customerRepository.getAll();
        Assertions.assertTrue(customers.size() > 0);
    }
}
