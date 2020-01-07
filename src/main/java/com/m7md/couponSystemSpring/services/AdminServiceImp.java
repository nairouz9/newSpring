/**
 * 
 */
package com.m7md.couponSystemSpring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponSystem.classes.ClientType;
import com.couponSystem.classes.CouponSystemException;
import com.m7md.couponSystemSpring.beans.Company;
import com.m7md.couponSystemSpring.beans.Customer;
import com.m7md.couponSystemSpring.login.ClientLogin;
import com.m7md.couponSystemSpring.repositories.CompanyRepo;
import com.m7md.couponSystemSpring.repositories.CustomerRepo;

/**
 * @author scary
 *
 */
@Service
public class AdminServiceImp implements AdminService, ClientLogin {
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private CustomerRepo customerRepo;

	public AdminServiceImp() {
		super();
	}

	@Override
	public ClientLogin login(String name, String password, ClientType type) {

		return null;
	}

	@Override
	public boolean performLogin(String name, String password) throws CouponSystemException {
		if (name.equals("admin") && password.equals("1234")) {
			return true;
		}
		return false;
	}

	@Override
	public Company createCompany(Company company) throws CouponSystemException {
		if (isCompanyEmailExists(company.getEmail()) == false) {
			companyRepo.save(company);
		} else {
			throw new CouponSystemException("the company " + company + " already exists.", null);
		}
		return company;
	}

	@Override
	public void deleteCompany(long id) {
		companyRepo.deleteById(id);
	}

	@Override
	public List<Company> allCompanies() {
		return companyRepo.findAll();
	}

	@Override
	public boolean isCompanyEmailExists(String companyEmail) {
		if (companyRepo.findByEmail(companyEmail) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Company companyById(long id) {

		return companyRepo.findById(id).get();
	}

	@Override
	public void updateCompany(Company company, String password, String email) {
		company.setEmail(email);
		company.setPassword(password);
		companyRepo.save(company);
	}

	@Override
	public Customer createCustomer(Customer customer) throws Exception {
		if (checkIfCustomerEmailAlreadyExists(customer.getEmail()) == false) {
			customerRepo.save(customer);
		} else {
			throw new CouponSystemException("customer " + customer.getEmail() + " already exists", null);
		}
		return customer;
	}

	@Override
	public void deleteCustomer(long id) {
		customerRepo.deleteById(null);
	}

	@Override
	public List<Customer> allCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customer customerById(long id) {
		return customerRepo.findById(id).get();
	}

	@Override
	public boolean checkIfCustomerEmailAlreadyExists(String customerEmail) {
		if (customerRepo.findByEmail(customerEmail) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void updateCustomer(Customer customer, String password) {
		customer.setPassword(password);
		customerRepo.save(customer);
	}

}
