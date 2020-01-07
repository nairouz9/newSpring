/**
 * 
 */
package com.m7md.couponSystemSpring.services;

import java.sql.Date;
import java.util.List;

import com.couponSystem.beans.Category;
import com.m7md.couponSystemSpring.beans.Company;
import com.m7md.couponSystemSpring.beans.Coupon;

/**
 * @author scary
 *
 */
public interface CompanyService {

	Coupon createCoupon(Coupon coupon) throws Exception;

	boolean checkIfTitleAlreadyExists(String title);

	void updateCoupon(Coupon coupon, Date endDate, double price);

	Company getCompany(long id);

	void deleteCoupon(long couponId) throws Exception;

	void setCompany(Company company);

	List<Coupon> getAllCompanyCoupons(long company_id) throws Exception;

	List<Coupon> couponByPrice(double price) throws Exception;

	List<Coupon> couponByType(Category category) throws Exception;

	List<Coupon> couponByDate(Date endDate) throws Exception;
}
