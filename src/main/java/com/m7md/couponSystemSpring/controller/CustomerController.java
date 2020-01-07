/**
 * 
 */
package com.m7md.couponSystemSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couponSystem.beans.Category;
import com.m7md.couponSystemSpring.beans.Coupon;
import com.m7md.couponSystemSpring.login.LoginService;
import com.m7md.couponSystemSpring.login.Session;
import com.m7md.couponSystemSpring.services.CustomerService;
import com.m7md.couponSystemSpring.services.CustomerServiceImp;

/**
 * @author scary
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	private Session exists(String token) {
		return LoginService.tokens.get(token);
	}

	@PostMapping("/purchaseCoupon/{couponId}/{token}")
	public ResponseEntity<String> purchaseCoupon(@PathVariable int couponId, @PathVariable String token)
			throws Exception {

		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				if (((CustomerServiceImp) session.getLogin()).purchaseCoupon(couponId) != null)
					return new ResponseEntity<>("Customer purchaed coupon :  " + couponId, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.UNAUTHORIZED);
			}
		}
		return null;
	}

	@GetMapping("/getAllCustomerCoupons/{customer_id}/{token}")
	public List<Coupon> getAllCustomerCoupons(@PathVariable int customer_id, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CustomerServiceImp) session.getLogin()).getAllCustomerPurchases(customer_id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/getCustomerByCouponType/{couponType}/{token}")
	public List<Coupon> getCustomerByCouponType(@PathVariable Category couponType, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CustomerServiceImp) session.getLogin()).couponByType(couponType);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/getCustomerByPrice/{price}/{token}")
	public List<Coupon> getCustomerByPrice(@PathVariable double price, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((CustomerServiceImp) session.getLogin()).couponByPrice(price);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/getAllCoupons")
	public ResponseEntity<List<Coupon>> getAllCoupons() {
		ResponseEntity<List<Coupon>> result = new ResponseEntity<List<Coupon>>(customerService.allCoupons(),
				HttpStatus.OK);
		return result;
	}

}
