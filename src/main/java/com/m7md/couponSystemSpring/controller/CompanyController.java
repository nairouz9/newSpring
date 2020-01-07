/**
 * 
 */
package com.m7md.couponSystemSpring.controller;

import java.sql.Date;
import java.util.List;

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
import com.m7md.couponSystemSpring.beans.Coupon;
import com.m7md.couponSystemSpring.beans.Income;
import com.m7md.couponSystemSpring.login.LoginService;
import com.m7md.couponSystemSpring.login.Session;
import com.m7md.couponSystemSpring.repositories.CouponRepo;
import com.m7md.couponSystemSpring.services.CompanyServiceImp;
import com.m7md.couponSystemSpring.services.IncomeService;

/**
 * @author scary
 *
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CouponRepo couponRepository;

	@Autowired
	private IncomeService incomeService;

	private Session exists(String token) {
		return LoginService.tokens.get(token);
	}

	@PostMapping("/insertCoupon/{token}")
	public ResponseEntity<String> insertCoupon(@RequestBody Coupon coupon, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				((CompanyServiceImp) session.getLogin()).createCoupon(coupon);
				System.out.println("the coupon create");
				return new ResponseEntity<>("coupon created  " + coupon, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.UNAUTHORIZED);
			}
		}
		return null;
	}

	@GetMapping("/getAllCompanyCoupons/{company_id}/{token}")
	public List<Coupon> getAllCompanyCoupons(@PathVariable int company_id, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CompanyServiceImp) session.getLogin()).getAllCompanyCoupons(company_id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@PostMapping("/updateCoupon/{token}")
	public ResponseEntity<Coupon> updateCoupon(@PathVariable String token, @RequestParam long id,
			@RequestParam Date endDate, @RequestParam double price) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Coupon coupon = null;
				coupon = couponRepository.findById(id).get();
				if (coupon != null) {
					((CompanyServiceImp) session.getLogin()).updateCoupon(coupon, endDate, price);
					ResponseEntity<Coupon> result = new ResponseEntity<>(coupon, HttpStatus.OK);
					return result;
				}
			} catch (Exception e) {
				System.out.println("Cannot update coupon");
			}
		}
		return null;
	}

	@DeleteMapping("/deleteCoupon/{couponId}/{token}")
	public void deleteCoupon(@PathVariable long couponId, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Coupon coupon = null;
				coupon = couponRepository.findById(couponId).get();
				if (coupon != null) {
					((CompanyServiceImp) session.getLogin()).deleteCoupon((int) couponId);
				}
			} catch (Exception e) {
				System.out.println("Failed to delete coupon " + couponId + e.getMessage());
			}
		}
	}

	@GetMapping("/getCompany/{id}/{token}")
	public Company getCompany(@PathVariable long id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CompanyServiceImp) session.getLogin()).getCompany(id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/viewIncomeByCompanyId/{companyId}/{token}")
	public List<Income> viewIncomeByCompanyId(@PathVariable long companyId, @PathVariable String token)
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

}
