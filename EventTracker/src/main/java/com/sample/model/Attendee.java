package com.sample.model;

import javax.validation.constraints.Size;

import com.sample.view.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


public class Attendee {
  
	@NotEmpty @Email
	private String email;
	
	@Size(min=2, max=30)
	private String name;
	
	@Phone @NotEmpty
	private String phone;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
