package com.codeoncewidakash.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.Type;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person_otm_ud")
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen1", sequenceName = "person_seq", initialValue = 100, allocationSize = 1)
	@Column(name = "id")
	private Long personId;
	
	@Column(name = "first_name", length = 20)
	@NonNull
	private String personFirstName;
	
	@Column(name = "last_name", length = 20)
	@NonNull
	private String personLastName;
	
	@Column(name = "age")
	@NonNull
	private Long personAge;
	
	@Column(name = "address", length = 30)
	@NonNull
	private String presonAddrs;
	
	@OneToMany(targetEntity = IdCard.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "person_fk", referencedColumnName = "id")
	@NonNull
	private Set<IdCard> idCards;

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", personFirstName=" + personFirstName + ", personLastName="
				+ personLastName + ", personAge=" + personAge + ", presonAddrs=" + presonAddrs + "]";
	}
}
