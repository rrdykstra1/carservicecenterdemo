package com.carservicecenter.demo.service;

import com.carservicecenter.demo.model.Customer;
import com.carservicecenter.demo.repository.CustomerRepository;
import com.carservicecenter.demo.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }
}
