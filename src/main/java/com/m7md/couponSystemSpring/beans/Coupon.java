/**
 * 
 */
package com.m7md.couponSystemSpring.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.couponSystem.beans.Category;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


/**
 * @author scary
 *
 */
@Entity
@Table(name = "coupons")

public @Data class Coupon {

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private long id;
	@Column(name = "category_id")
	private int companyId;
	@Column
	@Enumerated(EnumType.STRING)
	private Category category;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date startDate;
	@Column
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date endDate;
	@Column
	private int amount;
	@Column
	private double price;
	@Column
	private String image;

	public Coupon(int id, int company, Category category, String title, String description, Date startDate,
			Date endDate, int amount, double price, String image) {
		super();
		this.id = id;
		this.companyId = company;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon(int id) {
		super();
		this.id = id;
	}

	public Coupon() {
		super();
	}

	public Coupon(String title) {
		super();
		this.title = title;
	}

}
