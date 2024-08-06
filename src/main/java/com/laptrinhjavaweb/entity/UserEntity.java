package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "userAccount")
@Data
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	@Column
	private String email;
	@Column
	private String fullName;
	@Column
	private String address;
	@Column
	private Date dateOfBirth;
	@Column
	private String phone;
	@Column
	private String password;
	@Column
	private int status;
	@Column
	private String resetPasswordToken;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "userRole", joinColumns = @JoinColumn(name = "idUser"), 
								  inverseJoinColumns = @JoinColumn(name = "idRole"))
	private List<RoleEntity> roles = new ArrayList<>();
	
	@OneToMany(mappedBy = "idUser") 
	private List<BankAccountEntity> BankAccounts = new ArrayList<>();
	
	@OneToMany(mappedBy = "idUser")
	private List<TicketEntity> tickets = new ArrayList<TicketEntity>();
	
	
}