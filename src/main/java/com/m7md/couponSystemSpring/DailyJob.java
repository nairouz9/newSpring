/**
 * 
 */
package com.m7md.couponSystemSpring;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.m7md.couponSystemSpring.repositories.CompanyRepo;
import com.m7md.couponSystemSpring.repositories.CouponRepo;
import com.m7md.couponSystemSpring.repositories.CustomerRepo;

/**
 * @author scary
 *
 */
@Component
public class DailyJob {

	@Autowired
	CustomerRepo customerRepository;

	@Autowired
	CompanyRepo companyRepository;

	@Autowired
	CouponRepo couponRepository;

	private boolean running = true;

	public void removeExpiredCoupons(Date date) {
		couponRepository.deleteAll(couponRepository.findByEndDate(date));
	}

	public void startThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (running) {
					removeExpiredCoupons(new Date(System.currentTimeMillis()));
					try {
						Thread.sleep(1000 * 60 * 60 * 24);
					} catch (InterruptedException e) {
						System.out.println("Error " + e.getMessage());
					}
				}
			}
		}).start();
	}

	public void stopThread() {
		this.running = false;
	}

}
