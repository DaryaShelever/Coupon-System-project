//package app.core.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import app.core.entites.Category;
//import app.core.entites.Customer;
//import app.core.loginManager.ClientType;
//import app.core.loginManager.LoginManager;
//import app.core.service.CustomerService;
//
////@Component
//@Order(3)
//public class CustomerTest implements CommandLineRunner {
//
//	@Autowired
//	private LoginManager loginManager;
//
//	@Override
//	public void run(String... args) throws Exception {
//		CustomerService customerService = (CustomerService) loginManager.login("a.kudazki@com", "123456",
//				ClientType.CUSTOMER);
//		if (customerService != null) {
//			Customer customer = customerService.getCustomerDetails();
//			System.out.println("~~~~~~~~~~~~~Hello " + customer.getFirstName() + "~~~~~~~~~~~~~");
//			System.out.println();
//
//			System.out.println("~~~ Purchase coupons ~~~");
//
//			try {
//				customerService.purchaseCoupon(1);
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//
//			customerService.purchaseCoupon(2);
//			customerService.purchaseCoupon(4);
//			System.out.println("~ Purchase complete ~");
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//
//			System.out.println("~~~ All Coupons ~~~");
//			System.out.println(customerService.getCustomerCoupons());
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//
//			Category category = Category.ELECTRICITY;
//			System.out.println("~~~ Get one coupon by Category " + category + " ~~~");
//			System.out.println(customerService.getCustomerCopuponsByCategory(category));
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//
//			Double maxPrice = 1800.0;
//			System.out.println("~~~ Get one coupon up to Max price ~~~");
//			System.out.println(customerService.getCustomerCopuponsUpToMaxPrice(maxPrice));
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//		}
//	}
//}
