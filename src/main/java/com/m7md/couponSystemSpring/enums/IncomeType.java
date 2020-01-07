/**
 * 
 */
package com.m7md.couponSystemSpring.enums;

/**
 * @author scary
 *
 */
public enum IncomeType {

	CUSTOMER_PURCHASE("customer purchase"), COMPANY_NEW_COUPON("new coupon"), COMPANY_UPDATE_COUPON("update coupon");
	private String description;

	private IncomeType(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
