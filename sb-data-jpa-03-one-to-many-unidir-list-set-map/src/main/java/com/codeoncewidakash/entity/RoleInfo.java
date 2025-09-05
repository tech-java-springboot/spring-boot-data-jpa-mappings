package com.codeoncewidakash.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "role_info08_tab")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class RoleInfo {
	@Id
	@GeneratedValue(generator = "gen5", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen5", sequenceName = "role_seq_08", initialValue = 300, allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "role", length = 20)
	@NonNull
	private String role;
	
	@Column(name = "description", length = 30)
	@NonNull
	private String desc;
	
	@Column(name = "created_on", updatable = false)
	@CreationTimestamp
	private LocalDate createdOn;
	
	@Column(name = "updated_on", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedOn;
}
