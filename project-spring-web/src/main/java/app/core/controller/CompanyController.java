package app.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import app.core.entites.Category;
import app.core.entites.Company;
import app.core.entites.Coupon;
import app.core.exception.CouponsSystemException;
import app.core.service.CompanyService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/Company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping(path = "/Coupon/add", headers = { HttpHeaders.AUTHORIZATION })
	public void addCoupon(@RequestBody Coupon coupon , HttpServletRequest request) {
		try {
			companyService.addCoupon(coupon);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping(path = "/Coupon/update", headers = { HttpHeaders.AUTHORIZATION })
	public void updateCoupon(@RequestBody Coupon coupon) {
		try {
			companyService.updateCoupon(coupon);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping(path = "/Coupon/delete/{couponId}", headers = { HttpHeaders.AUTHORIZATION })
	public void deleteCoupon(@PathVariable int couponId) {
		try {
			companyService.deleteCoupon(couponId);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/Coupon/getOne/{couponId}", headers = { HttpHeaders.AUTHORIZATION })
	public Coupon getOneCoupon(@PathVariable int couponId) {
		try {
			return companyService.getOneCoupon(couponId);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/Coupon/getAll", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getAllCoupons() {
		try {
			return companyService.getAllCoupons();
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/Coupon/getByCategory", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getCompaniesCouponsByCategory(@RequestParam Category category) {
		try {
			return companyService.getCompaniesCouponsByCategory(category);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/Coupon/getUpToMaxPrice", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getCompaniesCouponsUpToMaxPrice(@RequestParam double maxPrice) {
		try {
			return companyService.getCompaniesCouponsUpToMaxPrice(maxPrice);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/Details", headers = { HttpHeaders.AUTHORIZATION })
	public Company getCompanyDetails() {
		try {
			return companyService.getCompanyDetails();
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
