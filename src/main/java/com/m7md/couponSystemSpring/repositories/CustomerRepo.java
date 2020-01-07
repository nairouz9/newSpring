/**
 * 
 */
package com.m7md.couponSystemSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m7md.couponSystemSpring.beans.Customer;

/**
 * @author scary
 *
 */
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	/**
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	Customer findByEmailAndPassword(String user, String password);

	/**
	 * @param customerEmail
	 * @return
	 */
	Customer findByEmail(String customerEmail);

}
