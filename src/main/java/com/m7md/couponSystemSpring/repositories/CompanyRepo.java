/**
 * 
 */
package com.m7md.couponSystemSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m7md.couponSystemSpring.beans.Company;
import com.m7md.couponSystemSpring.beans.Coupon;

/**
 * @author scary
 *
 */
@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

	Company findByEmail(String email);

	void deleteById(long companyId);

	Company findByEmailAndPassword(String Email, String password);

	/**
	 * @param company_id
	 * @return
	 */
	List<Coupon> Coupons(long id);

}
