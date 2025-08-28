package com.codeoncewidakash.entity;

import java.io.Serializable;

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
@Table(name = "wishlist08_tab")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
public class WishlistItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen3", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen3", sequenceName = "whislist_seq_08", initialValue = 500, allocationSize = 1)
	@Column(name = "wishlist_id")
	private Long id;
	
	@NonNull
	@Column(name = "producet_code", length = 20)
	@EqualsAndHashCode.Include
	private String productCode;
	
	@NonNull
	@Column(name = "product_name", length = 20)
	private String productName;
}
