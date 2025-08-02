package com.codeoncewidakash.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.ListIndexBase;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPS_TAB")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EMP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empId;
	
	@Column(name = "EMP_NAME", length = 20)
	private String empName;
	
	@Column(name = "EMP_DESG", length = 20)
	private String empDesg;
	
	@ElementCollection
	@CollectionTable(
			name = "EMP_EMAIL_TAB",
			joinColumns = @JoinColumn(name="EMP_ID_FK", referencedColumnName = "EMP_ID")
			)
	@OrderColumn(name = "EMAIL_ORDER")
	@ListIndexBase(value = 1)
	@Column(name = "EMAIL")
	private List<String> emails;
	
	@ElementCollection
	@CollectionTable(
			name = "EMP_MOBILE_TAB", 
			joinColumns = @JoinColumn(name="EMP_ID_FK", referencedColumnName = "EMP_ID"))
	@Column(name = "MOB_NUM")
	private Set<Long> phones;
	
	@ElementCollection
	@CollectionTable(
			name = "EMP_BANK_ACCOUNT_TAB",
			joinColumns = @JoinColumn(name="EMP_ID_FK", referencedColumnName = "EMP_ID"))
	@MapKeyColumn(name = "BANK_NAME")
	@Column(name = "ACCOUNT_NUM")
	private Map<String, Long> empBankAccount;
}
	
