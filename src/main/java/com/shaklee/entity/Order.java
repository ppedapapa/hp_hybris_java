package com.shaklee.entity;

import java.math.BigDecimal;

//@EqualsAndHashCode(callSuper=false)
//@Data
public class Order extends AttributeEntity {
	private String orderNumber;
	private String source;
	private BigDecimal orderTotal;
	private BigDecimal pv;
	private String status;
	private String orderDate;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public BigDecimal getPv() {
		return pv;
	}

	public void setPv(BigDecimal pv) {
		this.pv = pv;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
}
