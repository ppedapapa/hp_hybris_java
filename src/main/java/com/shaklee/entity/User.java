package com.shaklee.entity;

import java.sql.Date;
import java.util.List;

//@EqualsAndHashCode(callSuper=false)
//@Data
public class User extends AttributeEntity {
	private String shakleeId;
	private String contactId;
	private String firstName;
	private String lastName;
	private String email;
	private String uplineShakleeId;
	private String sponsorShakleeId;
	private Date created;

	public String getShakleeId() {
		return shakleeId;
	}

	public void setShakleeId(String shakleeId) {
		this.shakleeId = shakleeId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUplineShakleeId() {
		return uplineShakleeId;
	}

	public void setUplineShakleeId(String uplineShakleeId) {
		this.uplineShakleeId = uplineShakleeId;
	}

	public String getSponsorShakleeId() {
		return sponsorShakleeId;
	}

	public void setSponsorShakleeId(String sponsorShakleeId) {
		this.sponsorShakleeId = sponsorShakleeId;
	}

	public Date getCreatedDate() {
		return created;
	}

	public void setCreatedDate(Date createdDate) {
		this.created = createdDate;
	}

}