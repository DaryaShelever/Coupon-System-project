package app.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.auth.JwtUtil;
import app.core.auth.User;
import app.core.entites.Company;
import app.core.entites.Customer;
import app.core.exception.CouponsSystemException;
import lombok.Getter;

@Service
@Transactional
//@Scope("singleton")
public class AdminService extends ClientService {

	@Getter
	@Value("${admin-email}")
	private String email;

	@Getter
	@Value("${admin-password}")
	private String password;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public String login(User user) throws CouponsSystemException {
		if (!(user.getEmail().equalsIgnoreCase(this.email)
				&& user.getPassword().equals(this.password))) {
			throw new CouponsSystemException("Email or Password is wrong!");
		}
		user.setId(-1);
		return this.jwtUtil.generateToken(user);
	}
	
//	public String login(User user) {
//		String adminEmail = "admin@admin.com";
//		String adminPassword = "admin";
//		return (email.equals(adminEmail) && password.equals(adminPassword));
//	}

	public void addCompany(Company company, int adminId) throws CouponsSystemException {
		if (companyRepos.existsByName(company.getName())) {
			throw new CouponsSystemException("Company name " + company.getName() + " is alrady exist");
		}
		if (companyRepos.existsByEmail(company.getEmail())) {
			throw new CouponsSystemException("Company email " + company.getEmail() + " is alrady exist");
		}
		companyRepos.save(company);
	}

	public void updateCompany(Company company) throws CouponsSystemException {
		if (companyRepos.existsById(company.getId())) {
			Company companyFromDb = companyRepos.findById(company.getId())
					.orElseThrow(() -> new CouponsSystemException("You can't update company with this Id"));
			companyFromDb.setEmail(company.getEmail());
			companyFromDb.setPassword(company.getPassword());
			companyRepos.saveAndFlush(companyFromDb);
		} else {
			throw new CouponsSystemException("You are trying to change data that cannot be changed");
		}
	}

	public void deleteCompany(int companyId) throws CouponsSystemException {
		if (companyRepos.existsById(companyId)) {
			companyRepos.deleteById(companyId);
		} else {
			throw new CouponsSystemException("You can't delete company");
		}
	}

	public List<Company> getAllCompanies()  {
		return companyRepos.findAll();
	}

	public Company getOneCompany(int companyId) throws CouponsSystemException {
		return companyRepos.findById(companyId)
				.orElseThrow(() -> new CouponsSystemException("You can't get this " + companyId + " id company"));
	}

	public void addCustomer(Customer customer) throws CouponsSystemException {
		if (customerRepos.existsByEmail(customer.getEmail())) {
			throw new CouponsSystemException("Customer with is email" + customer.getEmail() + " is alrady exist");
		}
		customer = customerRepos.save(customer);
	}

	public void updateCustomer(Customer customer) throws CouponsSystemException {
		if (customerRepos.existsByEmail(customer.getEmail())) {
			Customer customerFromDb = customerRepos.findById(customer.getId())
					.orElseThrow(() -> new CouponsSystemException("You can't update customer with this Id"));
			customerFromDb.setEmail(customer.getEmail());
			customerFromDb.setFirstName(customer.getFirstName());
			customerFromDb.setLastName(customer.getLastName());
			customerFromDb.setPassword(customer.getPassword());
			customerFromDb.setCoupons(customer.getCoupons());

			customerRepos.saveAndFlush(customerFromDb);
		}
	}

	public void deleteCustomer(int customerId) throws CouponsSystemException {
		if (customerRepos.existsById(customerId)) {
			customerRepos.deleteById(customerId);
		} else {
			throw new CouponsSystemException("You can't delete customer");
		}
	}

	public List<Customer> getAllCustomers() {
		return customerRepos.findAll();
	}

	public Customer getOneCustomer(int customerId) throws CouponsSystemException {
		return customerRepos.findById(customerId)
				.orElseThrow(() -> new CouponsSystemException("You can't get this " + customerId + " id customer"));
	}

	

}
