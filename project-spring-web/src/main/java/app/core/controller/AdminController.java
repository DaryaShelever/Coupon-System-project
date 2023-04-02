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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.auth.User;
import app.core.entites.Company;
import app.core.entites.Customer;
import app.core.exception.CouponsSystemException;
import app.core.service.AdminService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/Admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping(path = "/Company/add", headers = { HttpHeaders.AUTHORIZATION })
	public void addCompany(@RequestBody Company company, HttpServletRequest request) {
		try {
			User user = (User) request.getAttribute("user");
			adminService.addCompany(company, user.getId());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping(path = "/Company/update", headers = { HttpHeaders.AUTHORIZATION })
	public void updateCompany(@RequestBody Company company) {
		try {
			adminService.updateCompany(company);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping(path = "/Company/delete/{companyId}", headers = { HttpHeaders.AUTHORIZATION })
	public void deleteCompany(@PathVariable int companyId) {
		try {
			adminService.deleteCompany(companyId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(path = "/Company/getAll", headers = { HttpHeaders.AUTHORIZATION })
	public List<Company> getAllCompanies() {
		return adminService.getAllCompanies();
	}

	@GetMapping(path = "/Company/getOne/{companyId}", headers = { HttpHeaders.AUTHORIZATION })
//	public Company getOneCompany(@RequestParam(required = true) int companyId) {
	public Company getOneCompany(@PathVariable int companyId) {
		try {
			return adminService.getOneCompany(companyId);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping(path = "/Customer/add", headers = { HttpHeaders.AUTHORIZATION })
	public void addCustomer(@RequestBody Customer customer) {
		try {
			adminService.addCustomer(customer);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping(path = "/Customer/update", headers = { HttpHeaders.AUTHORIZATION })
	public void updateCustomer(@RequestBody Customer customer) {
		try {
			adminService.updateCustomer(customer);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping(path = "/Customer/delete/{customerId}", headers = { HttpHeaders.AUTHORIZATION })
	public void deleteCustomer(@PathVariable int customerId) {
		try {
			adminService.deleteCustomer(customerId);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(path = "/Customer/getAll", headers = { HttpHeaders.AUTHORIZATION })
	public List<Customer> getAllCustomers() {
		return adminService.getAllCustomers();
	}

	@GetMapping(path = "/Customer/getOne/{customerId}", headers = { HttpHeaders.AUTHORIZATION })
	public Customer getOneCustomer(@PathVariable int customerId) {
		try {
			return adminService.getOneCustomer(customerId);
		} catch (CouponsSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
