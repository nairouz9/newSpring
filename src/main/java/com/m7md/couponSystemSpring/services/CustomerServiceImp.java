/**
 * 
 */
package com.m7md.couponSystemSpring.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponSystem.beans.Category;
import com.couponSystem.classes.ClientType;
import com.couponSystem.classes.CouponSystemException;
import com.m7md.couponSystemSpring.beans.Coupon;
import com.m7md.couponSystemSpring.beans.Customer;
import com.m7md.couponSystemSpring.beans.Income;
import com.m7md.couponSystemSpring.config.Utilities;
import com.m7md.couponSystemSpring.enums.IncomeType;
import com.m7md.couponSystemSpring.login.ClientLogin;
import com.m7md.couponSystemSpring.repositories.CouponRepo;
import com.m7md.couponSystemSpring.repositories.CustomerRepo;

/**
 * @author scary
 *
 */
@Service
public class CustomerServiceImp implements CustomerService, ClientLogin {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CouponRepo couponRepo;
	@Autowired
	private IncomeService incomeService;
	private Customer customer;

	@Override
	public ClientLogin login(String name, String password, ClientType type) {
		return null;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public Customer purchaseCoupon(long couponId) throws CouponSystemException {
		try {
			if (!couponRepo.existsById(couponId)) {
				throw new CouponSystemException("coupon doesn't exists", null);
			}
			Coupon coupon = couponRepo.findById(couponId).get();
			if (coupon.getAmount() <= 0) {
				throw new CouponSystemException("out of stock", null);
			}
			if (coupon.getEndDate().getTime() <= coupon.getStartDate().getTime()) {
				throw new CouponSystemException("expired coupon", null);
			}
			couponRepo.save(coupon);
			Customer customer = customerRepo.findById(this.customer.getId()).get();
			customer.getCoupons().add(coupon);
			customerRepo.save(customer);
			coupon.setAmount(coupon.getAmount() - 1);
			Income income = new Income();
			income.setId(this.customer.getId());
			income.setAmount(income.getAmount() + 1);
			income.setDate((Date) Utilities.getCurrentDate());
			income.setDescription(IncomeType.CUSTOMER_PURCHASE);
			income.setName("customer: " + customer.getEmail());
			incomeService.storeIncome(income);
		} catch (Exception e) {
			throw new CouponSystemException("can't purchase this coupon", e);
		}
		return customer;
	}

	@Override
	public List<Coupon> getAllCustomerPurchases(long customer_id) throws Exception {
		Customer customer = customerRepo.getOne(customer_id);
		if (customer != null) {
			List<Coupon> coupons = customer.getCoupons();
			if (coupons != null) {
				return coupons;
			} else {
				throw new CouponSystemException("no purchased coupons", null);
			}

		} else {
			throw new CouponSystemException("customer not exists", null);
		}
	}

	@Override
	public List<Coupon> couponByType(Category Category) throws Exception {
		List<Coupon> allCustomerCoupons = getAllCustomerPurchases(this.customer.getId());
		List<Coupon> coupons = couponRepo.findByCategory(Category);
		try {
			for (Coupon coupon : allCustomerCoupons) {
				if (coupon.getCategory().equals(Category)) {
					coupons.add(coupon);
				}
			}
		} catch (Exception e) {
			throw new CouponSystemException("failed to get purchase (type)", null);
		}
		return coupons;
	}

	@Override
	public List<Coupon> couponByPrice(double price) throws Exception {
		List<Coupon> allCustomerCoupons = getAllCustomerPurchases(this.customer.getId());
		List<Coupon> coupons = couponRepo.findByPrice(price);
		try {
			for (Coupon coupon : allCustomerCoupons) {
				if (coupon.getPrice() <= price) {
					coupons.add(coupon);
				}
			}
		} catch (Exception e) {
			throw new CouponSystemException("failed to get purchased (price)", null);
		}
		return coupons;
	}

	@Override
	public List<Coupon> allCoupons() {
		return couponRepo.findAll();
	}

}
