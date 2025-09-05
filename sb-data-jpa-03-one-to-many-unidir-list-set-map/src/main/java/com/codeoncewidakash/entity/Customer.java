package com.codeoncewidakash.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ListIndexBase;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
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
@Table(name = "customer08_tab")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "gen1", sequenceName = "cust_seq_08", initialValue = 500, allocationSize = 1)
	@Column(name = "customer_id")
	private Long id;
	
	@NonNull
	@Column(name = "cust_name", length = 20)
	private String customerName;
	
	@NonNull
	@Column(name = "cust_email", length = 30)
	@EqualsAndHashCode.Include
	private String customerEmail;
	
	@NonNull
	@Column(name = "cust_addrs")
	private String customerAddrs;
	
	@NonNull
	@Column(name = "cust_mob")
	@EqualsAndHashCode.Include
	private Long customerMobileNum;
	
	@Column(name = "status")
	private Boolean isActive;
	
	@Column(name = "created_on", updatable = false)
	@CreationTimestamp
	private LocalDate createdOn;
	
	@Column(name = "updated_on", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedOn;
	
	@OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "customer_id_fk", referencedColumnName = "customer_id")
	@OrderColumn(name = "order_indx")
	@ListIndexBase(value = 1)
	@Builder.Default
	private List<Order> orders = new ArrayList<>();
	
	@OneToMany(targetEntity = WishlistItem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "customer_id_fk", referencedColumnName = "customer_id")
	@Builder.Default
	private Set<WishlistItem> wishlistItems = new HashSet<>();
	
	@OneToMany(targetEntity = CartItem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "customer_id_fk", referencedColumnName = "customer_id")
	@MapKeyColumn(name = "item_indx", length = 20)
	@Builder.Default
	private Map<String, CartItem> cartItems = new HashMap<>();
}
