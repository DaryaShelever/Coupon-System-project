package app.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entites.Category;
import app.core.entites.Coupon;
import app.core.entites.Customer;
import app.core.exception.CouponsSystemException;
import app.core.service.CustomerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/Customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/Coupon/purchase", headers = { HttpHeaders.AUTHORIZATION })
	public void purchaseCoupon(int couponId) {
		try {
			customerService.purchaseCoupon(couponId);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(path = "/Coupons", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getCustomerCoupons() {
		try {
			return customerService.getCustomerCoupons();
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(path = "/Coupons/ByCategory", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getCustomerCopuponsByCategory(@RequestParam Category category) {
		try {
			return customerService.getCustomerCopuponsByCategory(category);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(path = "/Coupons/UpToMaxPrice", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getCustomerCopuponsUpToMaxPrice(@RequestParam double maxPrice) {
		try {
			return customerService.getCustomerCopuponsUpToMaxPrice(maxPrice);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(path = "/Details", headers = { HttpHeaders.AUTHORIZATION })
	public Customer getCustomerDetails() {
		try {
			return customerService.getCustomerDetails();
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
