package com.codeoncewidakash.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.codeoncewidakash.enums.StatusType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "tv_channel_tab")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class TVChannel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen1", sequenceName = "tv_channel_seq", initialValue = 100, allocationSize = 1)
	@Column(name = "channel_id")
	private Long id;

	@Column(name = "channel_name", length = 30)
	@EqualsAndHashCode.Include
	@NonNull
	private String name;
	
	@Column(name = "channel_lang", length = 30)
	@NonNull
	private String language;
	
	@Column(name = "channel_category", length = 30)
	@NonNull
	private String category;
	
	@Column(name = "channel_monthly_cost")
	@NonNull
	private Double monthlyCost;
	
	@Column(name = "created_on", updatable = false)
	@CreationTimestamp
	private LocalDate createdOn;
	
	@Column(name = "updated_on", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedOn;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusType statusType;
	
	@Builder.Default
	@ManyToMany(targetEntity = Subscriber.class, fetch = FetchType.LAZY, mappedBy = "channels")
	private Set<Subscriber> subscribers = new HashSet<>();

	@Override
	public String toString() {
		return "TVChannel [id=" + id + ", name=" + name + ", language=" + language + ", category=" + category
				+ ", monthlyCost=" + monthlyCost + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn
				+ ", statusType=" + statusType + "]";
	}
}
