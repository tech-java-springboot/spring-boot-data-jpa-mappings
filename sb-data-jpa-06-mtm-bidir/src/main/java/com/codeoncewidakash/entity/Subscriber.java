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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "subscriber_tab")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Subscriber implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen2", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen2", sequenceName = "subscriber_seq", initialValue = 500, allocationSize = 1)
	@Column(name = "subscriber_id")
	private Long id;
	
	@Column(name = "first_name", length = 30)
	@NonNull
	private String firstName;

	@Column(name = "last_name", length = 30)
	@NonNull
	private String lastName;
	
	@Column(name = "email", length = 60)
	@NonNull
	@EqualsAndHashCode.Include
	private String email;
	
	@Column(name = "phone_number")
	@NonNull
	@EqualsAndHashCode.Include
	private Long phoneNumber;
	
	@Column(name = "city", length = 30)
	@NonNull
	private String city;
	
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
	@ManyToMany(targetEntity = TVChannel.class, fetch = FetchType.LAZY)
	@JoinTable(	name = "subscriber_channel_tab", 
				joinColumns = @JoinColumn(name = "subscriber_id_fk", referencedColumnName = "subscriber_id"),	//owning side
				inverseJoinColumns = @JoinColumn(name = "channel_id_fk", referencedColumnName = "channel_id"))	//inverse side
	private Set<TVChannel> channels = new HashSet<>();

	@Override
	public String toString() {
		return "Subscriber [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", city=" + city + ", createdOn=" + createdOn + ", updatedOn="
				+ updatedOn + ", statusType=" + statusType + "]";
	}
}
