package com.kmne68.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kmne68.springdemo.entity.Customer;


@Repository
public class CustomerDAOImplementation implements CustomerDAO {

	// inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create query, sort by last name
		Query<Customer> query = currentSession.createQuery("FROM Customer ORDER BY lastName", Customer.class);		
		
		// execute query to get result list
		List<Customer> customers = query.getResultList();
		
		// return customer list
		return customers;
		
	}


	@Override
	public void saveCustomer(Customer customer) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer to the db
		currentSession.saveOrUpdate(customer);
		
	}
	
	
	@Override
	public Customer getCustomer(int id) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Customer customer = currentSession.get(Customer.class, id);
		
		return customer;
	}


	@Override
	public void deleteCustomer(int id) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete the object with the primary key that was passed in
		Query query = currentSession.createQuery("DELETE FROM Customer WHERE id=:customerId");
		query.setParameter("customerId", id);
		
		query.executeUpdate();
	}


	@Override
	public List<Customer> searchCustomers(String searchName) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = null;
		
		// only search by name if searchName is not empty
		if(searchName != null && searchName.trim().length() > 0) {
			
			// search for firstName or lastName...case insensitive
			query = currentSession.createQuery("FROM Customer WHERE LOWER(first_name) LIKE :name or LOWER(last_name) LIKE :name", Customer.class);
			query.setParameter("name", "%" + searchName.toLowerCase() + "%");
		
		} else {
			
			// searchName is empty...just get all customers
			query = currentSession.createQuery("from Customer", Customer.class);
			
		}
		
		// execute the query and get the result list
		List<Customer> customers = query.getResultList();
		
		// return the results
		return customers;
		
	}


}
