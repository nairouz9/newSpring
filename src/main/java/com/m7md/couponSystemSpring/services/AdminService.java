/**
 * 
 */
package com.m7md.couponSystemSpring.services;

import java.util.List;

import com.couponSystem.classes.CouponSystemException;
import com.m7md.couponSystemSpring.beans.Company;
import com.m7md.couponSystemSpring.beans.Customer;

/**
 * @author scary
 *
 */
public interface AdminService {

	// company
	boolean performLogin(String name, String password) throws CouponSystemException;

	Company createCompany(Company company) throws CouponSystemException;

	void deleteCompany(long id);

	List<Company> allCompanies();

	boolean isCompanyEmailExists(String companyEmail);

	Company companyById(long id);

	void updateCompany(Company company, String password, String email);

	// customer
	Customer createCustomer(Customer customer) throws Exception;

	void deleteCustomer(long id);

	List<Customer> allCustomers();

	Customer customerById(long id);

	boolean checkIfCustomerEmailAlreadyExists(String customerEmail);

	void updateCustomer(Customer customer, String password);
}