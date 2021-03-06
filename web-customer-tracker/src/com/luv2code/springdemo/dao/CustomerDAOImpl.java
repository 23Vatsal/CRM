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

	@Override
	public void deleteCustomer(int theId) {
		
		Session session=sessionFactory.getCurrentSession();
		
		Query theQuery=session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
		
		
		
	}

	@Override
	public List<Customer> doSearch(String theSearchName) {
		
		Session session=sessionFactory.getCurrentSession();
		
		Query theQuery=null;
		
		//only search if name is not null
		if(theSearchName!=null && theSearchName.trim().length()>0) {
			theQuery=session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName ", Customer.class);
			theQuery.setParameter("theName","%" +theSearchName.toLowerCase()+"%");
		}
		else {
			//search name is empty so return all customers
			theQuery=session.createQuery("from Customer order by firstName", Customer.class);
		}
		
		//execute query
		List<Customer> theCust=theQuery.getResultList();
		
		return theCust;
	}

}
