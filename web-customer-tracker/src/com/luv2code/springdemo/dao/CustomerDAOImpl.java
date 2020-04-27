package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		
		//get current hibernate session
		Session session=sessionFactory.getCurrentSession();
		
		//create query... and sort the data acc. to First Name
		Query<Customer> theQuery=session.createQuery("from Customer order by firstName",Customer.class);
		
		//execute query
		List<Customer> allCustomers=theQuery.getResultList();
		
		//return result		
		return allCustomers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		Session session=sessionFactory.getCurrentSession();
		
		//save the customer
		session.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session session=sessionFactory.getCurrentSession();
		
		//get data from db
		Customer theCustomer=session.get(Customer.class, theId);
		
		return theCustomer;
	}

}
