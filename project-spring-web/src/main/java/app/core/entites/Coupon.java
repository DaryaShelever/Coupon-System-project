package app.core.entites;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "company", "customers" })
@Entity
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Company company;

	@Enumerated(EnumType.STRING)
	private Category category;

	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private double price;
	private String image;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "customers_vs_coupon", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private List<Customer> customers;
}
