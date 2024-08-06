package com.laptrinhjavaweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "bankAccount")
@Data
public class BankAccountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBankAccount;
	@Column
	private String accountNumber;
	@Column
	private String accountName;
	@Column
	private String bankName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idUser")
	private UserEntity idUser;
}
