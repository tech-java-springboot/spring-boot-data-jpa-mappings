package com.codeoncewidakash.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "userinfo08_tab")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class UserInfo {
	@Id
	@GeneratedValue(generator = "gen6", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen6", sequenceName = "userinfo_seq_08", initialValue = 200, allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name", length = 20)
	@NonNull
	private String firstName;
	
	@Column(name = "last_name", length = 20)
	@NonNull
	private String lastName;
	
	@Column(name = "user_name", length = 30)
	@NonNull
	@EqualsAndHashCode.Include
	private String username;
	
	@Column(name = "password", length = 255)
	@NonNull
	private String password;
	
	@Column(name = "mobile_num")
	@EqualsAndHashCode.Include
	@NonNull
	private Long mobileNum;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles08_tab", joinColumns = @JoinColumn(name = "user_id_fk", referencedColumnName = "id"))
	@Column(name = "roles")
	@Builder.Default
	private Set<RoleInfo> roles = new HashSet<>();
	
	@Column(name = "status")
	private Boolean isActive;
	
	@Column(name = "created_on", updatable = false)
	@CreationTimestamp
	private LocalDate createdOn;
	
	@Column(name = "updated_on", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedOn;
}
