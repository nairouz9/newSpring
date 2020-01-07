/**
 * 
 */
package com.m7md.couponSystemSpring.services;

import java.util.List;

import com.m7md.couponSystemSpring.beans.Income;

/**
 * @author scary
 *
 */
public interface IncomeService {
	/**
	 * 
	 * @param income
	 */
	void storeIncome(Income income);

	/**
	 * 
	 * @return list of income
	 */
	List<Income> viewAllIncome();

	/**
	 * 
	 * @param customerId
	 * @return list of income by customer id
	 */
	List<Income> viewIncomeByCustomer(long customerId);

	/**
	 * 
	 * @param companyID
	 * @return
	 */
	List<Income> viewIncomeByCompany(long companyID);

}
