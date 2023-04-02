package app.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.auth.JwtUtil;
import app.core.auth.User;
import app.core.entites.Category;
import app.core.entites.Company;
import app.core.entites.Coupon;
import app.core.exception.CouponsSystemException;

@Service
@Transactional
public class CompanyService extends ClientService {

	private int companyId;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public String login(User user) throws CouponsSystemException {
		if (!companyRepos.existsByEmail(user.getEmail())) {
			throw new CouponsSystemException("loging failed - Email or Password is wrong! ");
		}
		Optional<Company> company = companyRepos.findByEmailAndPassword(user.getEmail(),
				user.getPassword());
		user.setId(company.get().getId());
		return jwtUtil.generateToken(user);
	}
	
//	public boolean login(String email, String password) {
//		Optional<Company> opt = this.companyRepos.findByEmailAndPassword(email, password);
//		if (opt.isPresent()) {
//			this.companyId = opt.get().getId();
//			return true;
//		}
//		return false;
//	}

	public void addCoupon(Coupon coupon) throws CouponsSystemException {
		if (couponRepos.existsByCompanyIdAndTitle(companyId, coupon.getTitle())) {
			throw new CouponsSystemException("You can't create coupon with this name");
		
		} else {
			companyRepos.findById(this.companyId).get().addCoupon(coupon);
		}
	}

	public void updateCoupon(Coupon coupon) throws CouponsSystemException {
		if (couponRepos.existsById(coupon.getId())) {
			Coupon updateCoupon = couponRepos.findById(coupon.getId())
					.orElseThrow(() -> new CouponsSystemException("You can't update coupon with this Id"));

			updateCoupon.setCategory(coupon.getCategory());
			updateCoupon.setTitle(coupon.getTitle());
			updateCoupon.setDescription(coupon.getDescription());
			updateCoupon.setStartDate(coupon.getStartDate());
			updateCoupon.setEndDate(coupon.getEndDate());
			updateCoupon.setAmount(coupon.getAmount());
			updateCoupon.setPrice(coupon.getPrice());
			updateCoupon.setImage(coupon.getImage());

			couponRepos.saveAndFlush(updateCoupon);
		} else {
			throw new CouponsSystemException("You are trying to change data that cannot be changed");

		}

	}

	public void deleteCoupon(int couponId) throws CouponsSystemException {
		if (couponRepos.existsById(couponId)) {
			couponRepos.deleteById(couponId);
		} else {
			throw new CouponsSystemException("You can't delete coupon ");
		}
	}

	public Coupon getOneCoupon(int couponId) throws CouponsSystemException {
		return couponRepos.findById(couponId)
				.orElseThrow(() -> new CouponsSystemException("You can't get this " + couponId + " id coupon"));
	}

	public List<Coupon> getAllCoupons() throws CouponsSystemException {
		return couponRepos.findAllByCompanyId(this.companyId);

	}

	public List<Coupon> getCompaniesCouponsByCategory(Category category) throws CouponsSystemException {
		return couponRepos.findAllByCategory(category);
	}

	public List<Coupon> getCompaniesCouponsUpToMaxPrice(double maxPrice) throws CouponsSystemException {
		return couponRepos.findAllByPriceLessThan(maxPrice);
	}

	public Company getCompanyDetails() throws CouponsSystemException {
		return companyRepos.findById(this.companyId)
				.orElseThrow(() -> new CouponsSystemException("You can't get this " + this.companyId + " id company"));
	}


	

	

}
