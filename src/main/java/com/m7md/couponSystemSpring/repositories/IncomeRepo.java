/**
 * 
 */
package com.m7md.couponSystemSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m7md.couponSystemSpring.beans.Income;

/**
 * @author scary
 *
 */
@Repository
public interface IncomeRepo extends JpaRepository<Income, Integer> {

	List<Income> findAllBycustomerId(long customerid);

	List<Income> findAllBycompanyId(long companyid);

}
