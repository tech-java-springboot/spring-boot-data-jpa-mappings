package com.codeoncewidakash.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "idcard_otm_ud")
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class IdCard implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen2", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen2", sequenceName = "idcard_seq", initialValue = 500, allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "card_number", length = 20)
	@NonNull
	private Long cardNumber;
	
	@Column(name = "card_type", length = 20)
	@NonNull
	private String cardType;
	
	@Column(name = "issue_date")
	@NonNull
	private LocalDate issueDate;
	
	@Column(name = "expiry_date")
	@NonNull
	private LocalDate expiryDate;
	
	@Override
	public String toString() {
		return "IdCard [id=" + id + ", cardNumber=" + cardNumber + ", cardType=" + cardType + ", issueDate=" + issueDate
				+ ", expiryDate=" + expiryDate + "]";
	}
}
