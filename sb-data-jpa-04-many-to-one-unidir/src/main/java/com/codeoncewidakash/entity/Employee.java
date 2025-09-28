package com.codeoncewidakash.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employee08_tab")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {
	@Id
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen1", sequenceName = "emp_seq_08", initialValue = 100, allocationSize = 1)
	@Column(name = "id")
	private Long empId;
	
	@Column(name = "name", length = 20)
	@NonNull
	private String empName;
	
	@Column(name = "desg", length = 20)
	@NonNull
	private String empDesg;
	
	@Column(name = "email", length = 60)
	@NonNull
	@EqualsAndHashCode.Include
	private String empEmail;
	
	@Column(name = "mobile_number")
	@NonNull
	@EqualsAndHashCode.Include
	private Long empMob;
	
	@Column(name = "status")
	private Boolean isActive;
	
	@Column(name = "created_on", updatable = false)
	@CreationTimestamp
	private LocalDate createdOn;
	
	@Column(name = "updated_on", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedOn;
	
	@ManyToOne(targetEntity = Department.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "dept_id_fk", referencedColumnName = "id")
	private Department department;
	
}