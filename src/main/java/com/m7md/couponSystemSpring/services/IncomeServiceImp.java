/**
 * 
 */
package com.m7md.couponSystemSpring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m7md.couponSystemSpring.beans.Income;
import com.m7md.couponSystemSpring.repositories.IncomeRepo;

/**
 * @author scary
 *
 */
@Service
public class IncomeServiceImp implements IncomeService {

	@Autowired
	private IncomeRepo incomeRepository;

	@Override
	public void storeIncome(Income income) {
		incomeRepository.save(income);
	}

	@Override
	public List<Income> viewAllIncome() {
		return incomeRepository.findAll();
	}

	@Override
	public List<Income> viewIncomeByCustomer(long customerId) {
		return incomeRepository.findAllBycustomerId(customerId);
	}

	@Override
	public List<Income> viewIncomeByCompany(long companyID) {
		return incomeRepository.findAllBycompanyId(companyID);
	}

}
