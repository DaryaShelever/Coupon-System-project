package app.core.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entites.Category;
import app.core.entites.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	boolean existsByCompanyIdAndTitle(int companyId, String couponTitle);

	List<Coupon> findAllByCategory(Category category);

	List<Coupon> findAllByPriceLessThan(double maxPrice);

	List<Coupon> findAllByCustomers(int customerId);

	List<Coupon> findAllByCompanyId(int companyId);
	
	void deleteByEndDateBefore(LocalDate now);

}
