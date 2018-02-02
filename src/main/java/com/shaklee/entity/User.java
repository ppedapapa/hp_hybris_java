package com.shaklee.entity;

import java.util.List;

//@EqualsAndHashCode(callSuper=false)
//@Data
public class User extends AttributeEntity {
	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String p1FirstName;
	private String p1LastName;
	private String title;
	private int rankId;
	private String joinDate;
	private String sponsorName;
	private String email;
	private String phone;
	private String billingAddress1;
	private String billingAddress2;
	private String billingCity;
	private String billingState;
	private String billingPostalCode;
	private String billingCounty;
	private String billingCountry;
	private String shippingAddress1;
	private String shippingAddress2;
	private String shippingCity;
	private String shippingState;
	private String shippingPostalCode;
	private String shippingCounty;
	private String shippingCountry;
	private String shippingPhoneNumber;
	private String serviceCharge;
	private String priceType;
	private String dateOfBirth;
	private String rewardsTitle;
	private String rewardsDate;

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getBillingAddress1() {
		return billingAddress1;
	}

	public void setBillingAddress1(String billingAddress1) {
		this.billingAddress1 = billingAddress1;
	}

	public String getBillingAddress2() {
		return billingAddress2;
	}

	public void setBillingAddress2(String billingAddress2) {
		this.billingAddress2 = billingAddress2;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingState() {
		return billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public String getBillingPostalCode() {
		return billingPostalCode;
	}

	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}

	public String getBillingCounty() {
		return billingCounty;
	}

	public void setBillingCounty(String billingCounty) {
		this.billingCounty = billingCounty;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	public String getShippingAddress1() {
		return shippingAddress1;
	}

	public void setShippingAddress1(String shippingAddress1) {
		this.shippingAddress1 = shippingAddress1;
	}

	public String getShippingAddress2() {
		return shippingAddress2;
	}

	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingState() {
		return shippingState;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public String getShippingPostalCode() {
		return shippingPostalCode;
	}

	public void setShippingPostalCode(String shippingPostalCode) {
		this.shippingPostalCode = shippingPostalCode;
	}

	public String getShippingCounty() {
		return shippingCounty;
	}

	public void setShippingCounty(String shippingCounty) {
		this.shippingCounty = shippingCounty;
	}

	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public String getShippingPhoneNumber() {
		return shippingPhoneNumber;
	}

	public void setShippingPhoneNumber(String shippingPhoneNumber) {
		this.shippingPhoneNumber = shippingPhoneNumber;
	}

	public String getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	private String fax;
	private String isGA;
	private String hasAutoship;
	private String isBL;
	private List<Volume> volumes;
	private List<Order> orders;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getP1FirstName() {
		return p1FirstName;
	}

	public void setP1FirstName(String p1FirstName) {
		this.p1FirstName = p1FirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getP1LastName() {
		return p1LastName;
	}

	public void setP1LastName(String p1LastName) {
		this.p1LastName = p1LastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRankId() {
		return rankId;
	}

	public void setRankId(int rankId) {
		this.rankId = rankId;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String isGA() {
		return isGA;
	}

	public void setIsGA(String isGA) {
		this.isGA = isGA;
	}

	public String getHasAutoship() {
		return hasAutoship;
	}

	public void setHasAutoship(String hasAutoship) {
		this.hasAutoship = hasAutoship;
	}

	public String isBL() {
		return isBL;
	}

	public void setIsBL(String isBL) {
		this.isBL = isBL;
	}

	public List<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getFullName() {
		if (firstName == null && lastName == null) {
			return null;
		} else {
			return (firstName == null ? "" : (firstName + " "))
					+ (lastName == null ? "" : lastName);
		}
	}

	public String getReverseFullName() {
		if (firstName == null && lastName == null) {
			return null;
		} else {
			return (lastName == null ? "" : (lastName + ", "))
					+ (firstName == null ? "" : firstName);
		}
	}

	public String getBillingAreaCode() {
		return extractAreaCode(phone);
	}

	public String getBillingPhone() {
		return extractLocalPhone(phone);
	}

	public String getFaxAreaCode() {
		return extractAreaCode(fax);
	}

	public String getFaxPhone() {
		return extractLocalPhone(fax);
	}

	private static String extractAreaCode(String phoneNumber) {
		String areaCode = "";
		phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
		if (phoneNumber != null && phoneNumber.length() >= 3) {
			areaCode = phoneNumber.substring(0, 3);
		}
		return areaCode;
	}

	private static String extractLocalPhone(String phoneNumber) {
		String localPhone = "";
		phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
		if (phoneNumber != null && phoneNumber.length() >= 4) {
			localPhone = phoneNumber.substring(3);
		}
		return localPhone;
	}

	public static User getUserPlaceholder(String userId) {
		User user = new User();
		user.setFirstName("Placeholder - " + userId);
		user.setLastName("");
		return user;
	}

	public String getRewardsTitle() {
		return rewardsTitle;
	}

	public void setRewardsTitle(String rewardsTitle) {
		this.rewardsTitle = rewardsTitle;
	}

	public String getRewardsDate() {
		return rewardsDate;
	}

	public void setRewardsDate(String rewardsDate) {
		this.rewardsDate = rewardsDate;
	}
	
	
	
}