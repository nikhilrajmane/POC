package com.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Table(name = "ms_user")
@Getter
@Setter
@ToString
public class User extends BaseEntity implements Serializable {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ms_id")
	private Long id;
	@Column(name = "ms_name")
	private String name;
	@Column(name = "ms_surName")
	private String surname;
	@Column(name = "ms_pinCode")
	private String pincode;
	@Column(name = "ms_email", unique = true)
	private String email;
	@Column(name = "ms_mobile", unique = true)
	private String mobile;
	@Column(name = "ms_password")
	private String password;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "joiningDate")
	private Date joiningDate;

	@Column(name = "isActive")
	private Boolean isActive = false;

	public User() {
	}

	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;

	}

	public User(String name, String email, String mobile, String password) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;

	}

	public User(Long id, String name, String email, String mobile, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;

	}

}
