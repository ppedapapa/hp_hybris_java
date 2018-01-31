package com.shaklee.entity;

import java.math.BigDecimal;

//@EqualsAndHashCode(callSuper=false)
//@Data
public class Volume extends AttributeEntity {
	public static enum VOLUME_TYPE {
		pv, pgv, ov, osl
	};

	private String name;
	private String month;
	private BigDecimal amt = BigDecimal.ZERO;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
}
