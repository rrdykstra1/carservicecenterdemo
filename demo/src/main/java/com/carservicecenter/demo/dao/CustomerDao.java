package com.carservicecenter.demo.dao;

import com.carservicecenter.demo.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerDao {

    void addCustomer(Customer customer);

    void deleteCustomer(Customer customer);
}
