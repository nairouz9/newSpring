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
import com.m7md.couponSystemSpring.beans.Company;
import com.m7md.couponSystemSpring.beans.Coupon;
import com.m7md.couponSystemSpring.beans.Income;
import com.m7md.couponSystemSpring.config.Utilities;
import com.m7md.couponSystemSpring.enums.IncomeType;
import com.m7md.couponSystemSpring.login.ClientLogin;
import com.m7md.couponSystemSpring.repositories.CompanyRepo;
import com.m7md.couponSystemSpring.repositories.CouponRepo;
import com.m7md.couponSystemSpring.repositories.IncomeRepo;

/**
 * @author scary
 *
 */
@Service
public class CompanyServiceImp implements CompanyService, ClientLogin {

	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private CouponRepo couponRepo;
	@Autowired
	private IncomeRepo incomeRepo;

	private Company company;

	@Override
	public ClientLogin login(String name, String password, ClientType type) {
		return null;
	}

	public boolean performLogin(String name, String password) {
		Company company = companyRepo.findByEmailAndPassword(name, password);
		if (company == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Coupon createCoupon(Coupon coupon) throws Exception {
		if (checkIfTitleAlreadyExists(coupon.getTitle()) == false) {
			couponRepo.save(coupon);
			Company company = companyRepo.findById(this.company.getId()).get();
			company.getCoupons().add(coupon);
			companyRepo.save(company);
			Income income = new Income();
			income.setId(this.company.getId());
			income.setDate((Date) Utilities.getCurrentDate());
			income.setName("Company: " + company.getName());
			income.setDescription(IncomeType.COMPANY_NEW_COUPON);
			income.setAmount(50);
			incomeRepo.save(income);
		} else {
			throw new CouponSystemException("already purchased", null);
		}
		return coupon;
	}

	@Override
	public boolean checkIfTitleAlreadyExists(String title) {
		if (couponRepo.findByTitle(title) != null) {
			return true;
		}
		return false;
	}

	@Override
	public void updateCoupon(Coupon coupon, Date endDate, double price) {
		coupon.setEndDate(endDate);
		coupon.setPrice(price);
		couponRepo.save(coupon);
		Income income = new Income();
		income.setId(this.company.getId());
		income.setAmount(50);
		income.setDescription(IncomeType.COMPANY_UPDATE_COUPON);
		income.setDate((Date) Utilities.getCurrentDate());
		income.setName("Company:" + company.getName());
		incomeRepo.save(income);
	}

	@Override
	public Company getCompany(long id) {

		return companyRepo.findById(id).get();
	}

	@Override
	public void deleteCoupon(long couponId) throws Exception {
		if (couponRepo.existsById((long) couponId) == false) {
			throw new CouponSystemException("no coupon found", null);
		} else {
			List<Coupon> companyCoupons = couponRepo.findAllById(this.company.getId());
			this.company.setCoupons(companyCoupons);
			companyRepo.save(this.company);
			couponRepo.deleteById((long) couponId);
		}
	}

	@Override
	public void setCompany(Company company) {
		this.company = company;

	}

	@Override
	public List<Coupon> getAllCompanyCoupons(long company_id) throws Exception {
		Company company = companyRepo.findById(company_id).get();
		if (company != null) {
			List<Coupon> coupons = company.getCoupons();
			if (coupons != null) {
				return coupons;
			} else {
				throw new CouponSystemException("no coupons for this company", null);
			}
		} else {
			throw new CouponSystemException("no company", null);
		}

	}

	@Override
	public List<Coupon> couponByPrice(double price) throws Exception {
		List<Coupon> allCompanyCoupons = getAllCompanyCoupons(this.company.getId());
		List<Coupon> coupons = couponRepo.findAllByPrice(price);
		try {
			for (Coupon coupon : allCompanyCoupons) {
				if (coupon.getPrice() <= price) {
					coupons.add(coupon);
				}
			}
		} catch (Exception e) {
			throw new CouponSystemException("something went wrong", e);
		}
		return coupons;
	}

	@Override
	public List<Coupon> couponByType(Category category) throws Exception {
		List<Coupon> allCompanyCoupons = getAllCompanyCoupons(this.company.getId());
		List<Coupon> coupons = couponRepo.findByCategory(category);
		try {
			for (Coupon coupon : allCompanyCoupons) {
				if (coupon.getCategory().equals(coupons)) {
					coupons.add(coupon);
				}
			}
		} catch (Exception e) {
			throw new CouponSystemException("something went wrong getting coupons by category", e);
		}
		return coupons;
	}

	@Override
	public List<Coupon> couponByDate(Date endDate) throws Exception {
		List<Coupon> allCompanyCoupons = getAllCompanyCoupons(this.company.getId());
		List<Coupon> coupons = couponRepo.findByEndDate(endDate);
		try {
			for (Coupon coupon : allCompanyCoupons) {
				if (coupon.getEndDate().equals(endDate) || coupon.getEndDate().before(endDate)) {
					coupons.add(coupon);
				}
			}
		} catch (Exception e) {
			throw new CouponSystemException("something went wrong getting coupons by date", e);
		}
		return coupons;
	}

}
