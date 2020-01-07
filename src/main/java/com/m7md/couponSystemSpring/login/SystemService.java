/**
 * 
 */
package com.m7md.couponSystemSpring.login;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.couponSystem.classes.ClientType;
import com.m7md.couponSystemSpring.DailyJob;
import com.m7md.couponSystemSpring.beans.Company;
import com.m7md.couponSystemSpring.beans.Customer;
import com.m7md.couponSystemSpring.repositories.CompanyRepo;
import com.m7md.couponSystemSpring.repositories.CustomerRepo;
import com.m7md.couponSystemSpring.services.AdminService;
import com.m7md.couponSystemSpring.services.CompanyService;
import com.m7md.couponSystemSpring.services.CustomerService;

/**
 * @author scary
 *
 */
@Service
public class SystemService {

	@Autowired
	private DailyJob dailyJob;

	@Autowired
	private AdminService adminService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ApplicationContext context;

	@PostConstruct
	public void init() {
		dailyJob.startThread();
	}

	@PreDestroy
	public void destroy() {
		dailyJob.stopThread();
	}

	public ClientLogin login(String user, String password, ClientType type) {

		switch (type) {
		case ADMINISTRATOR:
			if (user.equals("admin") && password.equals("1234")) {
				adminService = context.getBean(AdminService.class);
				return (ClientLogin) adminService;
			} else {
				return null;
			}
		case COMPANY:
			Company company = companyRepo.findByEmailAndPassword(user, password);
			if (company != null) {
				companyService = context.getBean(CompanyService.class);
				return (ClientLogin) companyService;
			} else {
				return null;
			}
		case CUSTOMER:
			Customer customer = customerRepo.findByEmailAndPassword(user, password);
			if (customer != null) {
				customerService = context.getBean(CustomerService.class);
				return (ClientLogin) customerService;
			} else {
				return null;
			}
		}
		return null;
	}
}