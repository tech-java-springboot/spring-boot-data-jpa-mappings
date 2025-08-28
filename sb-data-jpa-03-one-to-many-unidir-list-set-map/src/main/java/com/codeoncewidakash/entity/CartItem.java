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
@Table(name = "cartitem08_tab")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
public class CartItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen4", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen4", sequenceName = "cartitem_seq_08", allocationSize = 700, initialValue = 1)
	@Column(name = "cartitem_id")
	private Long id;
	
	@NonNull
	@Column(name = "product_name", length = 20)
	@EqualsAndHashCode.Include
	private String productName;
	
	@Column(name = "qty")
	private Integer quantity;
	
	@NonNull
	@Column(name = "unit_price")
	private Double unitPrice;
	
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private LocalDate createdOn;
	

}
