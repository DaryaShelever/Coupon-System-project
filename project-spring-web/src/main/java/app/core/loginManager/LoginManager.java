package app.core.loginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.auth.User;
import app.core.exception.CouponsSystemException;
import app.core.service.AdminService;
import app.core.service.CompanyService;
import app.core.service.CustomerService;

@Service
public class LoginManager {
	
//	@Autowired
//	private JwtUtil jwtUtil;
//	@Autowired
//	private ApplicationContext ctx;
	@Autowired
	private AdminService adminService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CustomerService customerService;

	public String login(User user) throws CouponsSystemException {
		switch (user.getClientType()) {
		case ADMIN:
			String tokenAdmin = adminService.login(user);
			return tokenAdmin;

		case COMPANY:
			String tokenCompany = companyService.login(user);
			return tokenCompany;

		case CUSTOMER:
			String tokenCustomer = customerService.login(user);
			return tokenCustomer;

		default:
			throw new CouponsSystemException("Invalid Client Type");
		}

//	public String login(String email, String password, ClientType clientType) throws CouponsSystemException {
//		switch (clientType) {
//		case ADMIN: {
//			if (adminService.login(email, password)) {
//				User user= new User();
//				user.setClientType(ClientType.ADMIN);
//				user.setEmail("admin@admin.com");
//				user.setPassword("admin");
//				return this.jwtUtil.generateToken(user) ;
//			}else {
//				System.out.println("The password or email is wrong as Admin");
//			}
//		}
//		case COMPANY: {
//			CompanyService companyService = ctx.getBean(CompanyService.class);
//			if (companyService.login(email, password)) {
//				User user= new User();
//				user.setClientType(ClientType.COMPANY);
//				user.setEmail(email);
//				user.setPassword(password);
//				return this.jwtUtil.generateToken(user);
//			}else {
//				System.out.println("The password or email is wrong as Company");
//			}
//		}
//
//		case CUSTOMER: {
//			CustomerService customerService = ctx.getBean(CustomerService.class);
//			if (customerService.login(email, password)) {
//				User user= new User();
//				user.setClientType(ClientType.CUSTOMER);
//				user.setEmail(email);
//				user.setPassword(password);
//				return this.jwtUtil.generateToken(user);			}else {
//				System.out.println("The password or email is wrong as Customer");
//			}
//		}
//		default:
//			System.out.println("Try again");
//		}
//		return null;
//	}

	}}
