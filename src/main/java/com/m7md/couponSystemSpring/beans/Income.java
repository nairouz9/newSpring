/**
 * 
 */
package com.m7md.couponSystemSpring.beans;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.m7md.couponSystemSpring.enums.IncomeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author scary
 *
 */
@Entity
@Table(name = "Income")
@AllArgsConstructor
@NoArgsConstructor

public @Data class Income {

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public IncomeType getDescription() {
		return description;
	}
	public void setDescription(IncomeType description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@Basic(optional = false)
	private long id;
	@Column
	@Basic(optional = false)
	private String name;
	@Column
	@Basic(optional = false)
	private Date date;
	@Column
	@Basic(optional = false)
	private IncomeType description;
	@Column
	@Basic(optional = false)
	private double amount;
	@Column
	@Basic(optional = true)
	private long companyId;
	@Column
	@Basic(optional = true)
	private long customerId;
}
