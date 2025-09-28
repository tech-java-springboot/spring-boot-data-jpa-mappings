package com.codeoncewidakash.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Entity
@Table(name = "claim_bidir_tab")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Claim implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen1", sequenceName = "claim_seq_bi_09", initialValue = 100, allocationSize = 1)
	@Column(name = "claim_id")
	private Long id;

	@NonNull
	@Column(name = "claim_number", length = 255)
	@EqualsAndHashCode.Include
	private String claimNumber;
	
	@NonNull
	@Column(name = "claim_type", length = 30)
	private String claimType;
	
	@NonNull
	@Column(name = "claim_amount")
	private Double claimAmount;
	
	@NonNull
	@Column(name = "claim_status", length = 25)
	private String status;
	
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private LocalDate createdOn;
	
	@UpdateTimestamp
	@Column(name = "updated_on", insertable = false)
	private LocalDate updatedOn;
	
	@ManyToOne(targetEntity = Policy.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "policy_id_fk", referencedColumnName = "policy_id")
	@JsonIgnore	// need not to expose the Parent reference in Child JSON.
	private Policy policy;

	@Override
	public String toString() {
		return "Claim [id=" + id + ", claimNumber=" + claimNumber + ", claimType=" + claimType + ", claimAmount="
				+ claimAmount + ", status=" + status + "]";
	}
}
