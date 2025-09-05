package com.codeoncewidakash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="department08_tab")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Department {
	
	@Id
	@GeneratedValue(generator = "gen2", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen2", sequenceName = "dept_seq_08", initialValue = 500, allocationSize = 1)
	@Column(name = "id")
	private Long deptId;
	
	@NonNull
	@Column(name = "name", length = 20)
	@EqualsAndHashCode.Include
	private String deptName;
	
	@Column(name = "status")
	private Boolean isActive;
}
