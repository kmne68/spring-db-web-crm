package com.kmne68.springdemo.dao;

import java.util.List;

import com.kmne68.springdemo.entity.Customer;


public interface CustomerDAO {

	public List<Customer> getCustomers();
}