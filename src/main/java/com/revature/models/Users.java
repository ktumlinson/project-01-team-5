package com.revature.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.revature.models.UserRoles.UserRole;

@Entity
@Table(name="ers_users")
public class Users {

	@Id @Column(name="ers_users_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToMany(mappedBy="ers_users")
	private int id;
	
	@Column(name="ers_username")
	private String username;
	
	@Column(name="ers_password")
	private String password;
	
	@Column(name="user_first_name")
	private String firstname;
	
	@Column(name="user_last_name")
	private String lastname;
	
	@Column(name="user_email")
	private String email;
	

	
	@ManyToOne(targetEntity=UserRole.class, optional=false)
	@Enumerated(EnumType.STRING)
	private UserRole role;


	@Column(name="user_information") @Basic(optional=true)
	private String userInfo;
	
	

}
	
	
	
	
	
	
	

