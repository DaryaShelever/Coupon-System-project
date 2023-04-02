package app.core.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "coupons")
@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	private List<Coupon> coupons;

	public Company(int id, String name, String email, String password, List<Coupon> coupons) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		setCoupons(coupons);
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
		for (Coupon coupon : coupons) {
			coupon.setCompany(this);
		}
	}

	public void addCoupon(Coupon coupon) {
		if (this.coupons == null) {
			this.coupons = new ArrayList<Coupon>();
		}
		coupon.setCompany(this);
		this.coupons.add(coupon);
	}

}
