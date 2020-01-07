/**
 * 
 */
package com.m7md.couponSystemSpring.services;

import java.util.List;

import com.couponSystem.beans.Category;
import com.couponSystem.classes.CouponSystemException;
import com.m7md.couponSystemSpring.beans.Coupon;
import com.m7md.couponSystemSpring.beans.Customer;

/**
 * @author scary
 *
 */
public interface CustomerService {

	void setCustomer(Customer customer);

	Customer purchaseCoupon(long couponId) throws CouponSystemException;

	List<Coupon> getAllCustomerPurchases(long customer_id) throws Exception;

	List<Coupon> couponByType(Category Category) throws Exception;

	List<Coupon> couponByPrice(double price) throws Exception;

	List<Coupon> allCoupons();

}
