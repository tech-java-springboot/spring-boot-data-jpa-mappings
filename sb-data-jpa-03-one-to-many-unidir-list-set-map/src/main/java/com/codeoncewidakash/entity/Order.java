package com.codeoncewidakash.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "order08_tab")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen2", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen2", sequenceName = "order_seq_08", initialValue = 300, allocationSize = 1)
	@Column(name = "order_id")
	private Long id;
	
	@NonNull
	@Column(name = "order_number")
	@EqualsAndHashCode.Include
	private String orderNumber;
	
	@NonNull
	@Column(name = "product_name")
	private String productName;
	
	@NonNull
	@Column(name = "price")
	private Double price;
	
	@NonNull
	@Column(name = "order_status")
	private String orderStatus;
	
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private LocalDate orderedOn;
}
