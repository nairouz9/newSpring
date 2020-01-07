/**
 * 
 */
package com.m7md.couponSystemSpring.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.couponSystem.beans.Category;
import com.m7md.couponSystemSpring.beans.Coupon;

/**
 * @author scary
 *
 */
@Repository
public interface CouponRepo extends JpaRepository<Coupon, Long> {

	/**
	 * @param title
	 * @return
	 */
	Object findByTitle(String title);

	/**
	 * @param id
	 * @return
	 */
	List<Coupon> findAllById(long id);

	/**
	 * @param price
	 * @return
	 */
	List<Coupon> findAllByPrice(double price);

	/**
	 * @param category
	 * @return
	 */
	List<Coupon> findByCategory(Category category);

	/**
	 * @param price
	 * @return
	 */
	List<Coupon> findByPrice(double price);

	/**
	 * @param date
	 * @return
	 */
	List<Coupon> findByEndDate(Date date);

}
