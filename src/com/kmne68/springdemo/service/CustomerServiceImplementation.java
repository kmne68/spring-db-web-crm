package com.kmne68.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmne68.springdemo.dao.CustomerDAO;
import com.kmne68.springdemo.entity.Customer;


@Service
public class CustomerServiceImplementation implements CustomerService {
	
	
	// need to inject customer dao
	@Autowired
	private CustomerDAO customerDAO;
	

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		return customerDAO.getCustomers();
	}

}