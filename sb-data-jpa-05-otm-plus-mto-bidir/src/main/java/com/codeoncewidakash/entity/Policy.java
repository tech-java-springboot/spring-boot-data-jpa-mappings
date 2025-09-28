package com.codeoncewidakash.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

@Entity
@Table(name = "policy_bidir_tab")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Policy implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen2", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen2", sequenceName = "policy_bidir_seq", initialValue = 500, allocationSize = 1)
	@Column(name = "policy_id")
	private Long id;
	
	@NonNull
	@Column(name = "policy_number", length = 255)
	private String policyNumber;
	
	@NonNull
	@Column(name = "policy_type", length = 30)
	private String policyType;

	@NonNull
	@Column(name = "policy_holder_name", length = 30)
	private String policyHolderName;
	
	@OneToMany(targetEntity = Claim.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "policy")
	//@JoinColumn(name = "policy_id_fk", referencedColumnName = "policy_id")
	@Builder.Default
	private List<Claim> claims = new ArrayList<>();

	@Override
	public String toString() {
		return "Policy [id=" + id + ", policyNumber=" + policyNumber + ", policyType=" + policyType
				+ ", policyHolderName=" + policyHolderName + "]";
	}
}
