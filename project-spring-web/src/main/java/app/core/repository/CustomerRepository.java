package app.core.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entites.Coupon;
import app.core.entites.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByEmailAndPassword(String email, String password);
	
	boolean existsByCoupons(Coupon coupon);

	boolean existsByEmail(String email);

	boolean existsByIdAndCouponsId(int customerId, int couponId);
}
