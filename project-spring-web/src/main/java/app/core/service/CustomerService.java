package app.core.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.auth.JwtUtil;
import app.core.auth.User;
import app.core.entites.Category;
import app.core.entites.Coupon;
import app.core.entites.Customer;
import app.core.exception.CouponsSystemException;

@Service
@Transactional
public class CustomerService extends ClientService {

	private int customerId;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public String login(User user) throws CouponsSystemException{
		if (!customerRepos.existsByEmail(user.getEmail())) {
			throw new CouponsSystemException("loging failed - Email or Password is wrong!");
		}
		Optional<Customer> customer = customerRepos.findByEmailAndPassword(user.getEmail(),
				user.getPassword());
		user.setId(customer.get().getId());
		return jwtUtil.generateToken(user);
	}
	
//	public boolean login(String email, String password) {
//		Optional<Customer> customer = customerRepos.findByEmailAndPassword(email, password);
//		if (customer.isPresent()) {
//			this.customerId = customer.get().getId();
//			return true;
//		}
//		return false;
//	}

	public void purchaseCoupon(int couponId) throws CouponsSystemException {
		Customer customer = this.customerRepos.findById(this.customerId)
				.orElseThrow(() -> new CouponsSystemException("customer not found"));
		Coupon couponFromDb = this.couponRepos.findById(couponId)
				.orElseThrow(() -> new CouponsSystemException("Coupon not found, id = " + couponId));
		if (couponFromDb.getAmount() <= 0) {
			throw new CouponsSystemException("coupon with id- " + couponId + " is out of stock");
		}
		if (couponFromDb.getEndDate().isBefore(LocalDate.now())) {
			throw new CouponsSystemException("coupon with id- " + couponId + " has expired");
		}
		if (this.customerRepos.existsByIdAndCouponsId(customerId, couponId)) {
			throw new CouponsSystemException("customer already has this coupon with id- " + couponId);
		}
		couponFromDb.setAmount(couponFromDb.getAmount() - 1);
		customer.addCoupon(couponFromDb);
		couponRepos.saveAndFlush(couponFromDb);
	}

	public List<Coupon> getCustomerCoupons() throws CouponsSystemException {
		return couponRepos.findAll();
	}

	public List<Coupon> getCustomerCopuponsByCategory(Category category) throws CouponsSystemException {
		return couponRepos.findAllByCategory(category);
	}

	public List<Coupon> getCustomerCopuponsUpToMaxPrice(double maxPrice) throws CouponsSystemException {
		return couponRepos.findAllByPriceLessThan(maxPrice);
	}

	public Customer getCustomerDetails() throws CouponsSystemException {
		return customerRepos.findById(this.customerId).orElseThrow(
				() -> new CouponsSystemException("You can't get this " + this.customerId + " id customer"));
	}
}
