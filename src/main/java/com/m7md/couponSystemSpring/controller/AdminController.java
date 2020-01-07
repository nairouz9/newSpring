/**
 * 
 */
package com.m7md.couponSystemSpring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m7md.couponSystemSpring.beans.Company;
import com.m7md.couponSystemSpring.beans.Customer;
import com.m7md.couponSystemSpring.beans.Income;
import com.m7md.couponSystemSpring.login.LoginService;
import com.m7md.couponSystemSpring.login.Session;
import com.m7md.couponSystemSpring.repositories.CompanyRepo;
import com.m7md.couponSystemSpring.services.AdminService;
import com.m7md.couponSystemSpring.services.AdminServiceImp;
import com.m7md.couponSystemSpring.services.IncomeService;

/**
 * @author scary
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private IncomeService incomeService;
	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	private Map<String, Session> tokens;

	private Session exists(String token) {
		return LoginService.tokens.get(token);
	}

	@GetMapping("/getAllCompnies/{token}")
	public ResponseEntity<List<Company>> getAllCompany(@PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("wrong session");
		}
		session.setLastAccesed(System.currentTimeMillis());
		ResponseEntity<List<Company>> result = new ResponseEntity<List<Company>>(adminService.allCompanies(),
				HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCompany/{id}/{token}")
	public Company getCompany(@PathVariable int id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((AdminServiceImp) session.getLogin()).companyById(id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@PostMapping("/createCompany/{token}")
	public ResponseEntity<String> createCompany(@RequestBody Company company, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		System.out.println(session);
		if (session == null) {
			throw new Exception("wrong session");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {

				((AdminServiceImp) session.getLogin()).createCompany(company);
				return new ResponseEntity<>("company created", HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<>("wrong", HttpStatus.UNAUTHORIZED);
			}
		}
		return null;

	}

	@PostMapping("/updateCompany/{token}")
	public ResponseEntity<String> updateCompany(@PathVariable String token, @RequestParam int id,
			@RequestParam String password, @RequestParam String email) throws Exception {

		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Company company = null;
				company = adminService.companyById(id);
				if (company != null) {
					((AdminServiceImp) session.getLogin()).updateCompany(company, password, email);
					return new ResponseEntity<>("company " + company.getName() + " was updated", HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				System.out.println("Failed to update company !!");
			}
		}
		return null;
	}

	@DeleteMapping("/deleteCompany/{id}/{token}")
	public void deleteCompany(@PathVariable int id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Company company = null;
				company = adminService.companyById(id);
				if (company != null) {
					try {
						((AdminServiceImp) session.getLogin()).deleteCompany(id);
						System.out.println("Admin successfully deleted company " + id);
					} catch (Exception e) {
						e.getMessage();
					}
				}
			} catch (Exception e) {
				System.err.println("Failed to delete company, please insert another id");
			}
		}
	}

	@GetMapping("/getAllCustomers/{token}")
	public ResponseEntity<List<Customer>> allcustomers(@PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				if (((AdminServiceImp) session.getLogin()).allCustomers() != null) {
					ResponseEntity<List<Customer>> result = new ResponseEntity<List<Customer>>(
							adminService.allCustomers(), HttpStatus.OK);
					return result;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/getCustomer/{id}/{token}")
	public Customer getCustomer(@PathVariable int id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((AdminServiceImp) session.getLogin()).customerById(id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@PostMapping("/createCustomer/{token}")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		System.out.println(session);
		if (session == null) {
			throw new Exception("wrong session");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				((AdminServiceImp) session.getLogin()).createCustomer(customer);
				return new ResponseEntity<>("customer created", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>("wrong", HttpStatus.UNAUTHORIZED);
			}
		}
		return null;

	}

	@PostMapping("/updateCustomer/{token}")
	public ResponseEntity<String> updateCustomer(@PathVariable String token, @RequestParam int id,
			@RequestParam String password) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Customer customer = null;
				customer = adminService.customerById(id);
				if (customer != null) {
					((AdminServiceImp) session.getLogin()).updateCustomer(customer, password);
					return new ResponseEntity<>("customer " + customer.getEmail() + " was updated", HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				System.out.println("Failed to update company !!");
			}
		}
		return null;
	}

	@DeleteMapping("/deleteCustomer/{id}/{token}")
	public void deleteCustomer(@PathVariable int id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Customer customer = null;
				customer = adminService.customerById(id);
				if (customer != null) {
					((AdminServiceImp) session.getLogin()).deleteCustomer(id);
				}
			} catch (Exception e) {
				System.err.println("Failed to delete customer, please insert another id");
			}
		}
	}

	@GetMapping("/allCompanies")
	public ResponseEntity<List<Company>> allCompanies() {
		return new ResponseEntity<List<Company>>(companyRepo.findAll(), HttpStatus.OK);
	}

	@GetMapping("/viewIncomeByCompanyId/{companyId}/{token}")
	public List<Income> viewIncomeByCompanyId(@PathVariable int companyId, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return incomeService.viewIncomeByCompany(companyId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/viewIncomeByCustomerId/{customerId}/{token}")
	public List<Income> viewIncomeByCustomerId(@PathVariable int customerId, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return incomeService.viewIncomeByCustomer(customerId);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

}
