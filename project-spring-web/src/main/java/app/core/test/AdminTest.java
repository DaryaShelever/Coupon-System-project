//package app.core.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import app.core.entites.Company;
//import app.core.entites.Customer;
//import app.core.loginManager.ClientType;
//import app.core.loginManager.LoginManager;
//import app.core.service.AdminService;
//
////@Component
//@Order(1)
//public class AdminTest implements CommandLineRunner {
//
//	@Autowired
//	private LoginManager loginManager;
//
//	@Override
//	public void run(String... args) throws Exception {
//		AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMIN);
//		if (adminService != null) {
//
//			System.out.println("~~~~~~~~~~~~~Hello Admin~~~~~~~~~~~~~");
//			System.out.println();
//
//			Company company1 = new Company();
//			company1.setName("Teva");
//			company1.setEmail("teva@mail");
//			company1.setPassword("12345");
//
//			Company company2 = new Company();
//			company2.setName("Intel");
//			company2.setEmail("intel@mail");
//			company2.setPassword("12345");
//
//			Company company3 = new Company();
//			company3.setName("Strauss");
//			company3.setEmail("strauss@mail");
//			company3.setPassword("12345");
//
//			System.out.println("~~~ Сreation of new companies ~~~");
//
//			adminService.addCompany(company1);
//			adminService.addCompany(company2);
//			adminService.addCompany(company3);
//
//			System.out.println("~ Сompanies were created ~");
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			
//			System.out.println("~~~ All companies ~~~");
//			System.out.println(adminService.getAllCompanies());
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			
//			
//			System.out.println("~~~ Get one company ~~~");
//			System.out.println(adminService.getOneCompany(company1.getId()));
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			System.out.println();
//			
//			
//			int idUpdateCompany = 2;
//
//			System.out.println("~~~ Update company ~~~");
//			Company companyForUpdate = adminService.getOneCompany(idUpdateCompany);
//			System.out.println("~Before update: \n" + companyForUpdate);
//			
//			companyForUpdate.setEmail("intel.israel@mail");
//			companyForUpdate.setPassword("123456789");
//			
//			adminService.updateCompany(companyForUpdate);
//			System.out.println("~After update: \n" + companyForUpdate);
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			System.out.println();
//			
//			int idDeleteCompany = 3;
//			System.out.println("~~~ Delete company ~~~");
//			Company companyForDelite = adminService.getOneCompany(idDeleteCompany);
//			adminService.deleteCompany(companyForDelite.getId());
//			System.out.println("~ Company '" + companyForDelite.getName() + "' were deleted ~");
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//
//			System.out.println("~~~ All companies after changes ~~~");
//			System.out.println(adminService.getAllCompanies());
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			
//			//
//			Customer customer1 = new Customer();
//			customer1.setFirstName("Or");
//			customer1.setLastName("Katan");
//			customer1.setEmail("or.katan@com");
//			customer1.setPassword("123456");
//
//			Customer customer2 = new Customer();
//			customer2.setFirstName("Rita");
//			customer2.setLastName("Bailis");
//			customer2.setEmail("rita.b@com");
//			customer2.setPassword("123456");
//
//			Customer customer3 = new Customer();
//			customer3.setFirstName("Alina");
//			customer3.setLastName("Kudazki");
//			customer3.setEmail("a.kudazki@com");
//			customer3.setPassword("123456");
//
//			System.out.println("~~~ Сreation of new custumers ~~~");
//			adminService.addCustomer(customer1);
//			adminService.addCustomer(customer2);
//			adminService.addCustomer(customer3);
//			System.out.println("~ Customers were created ~");
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			System.out.println();
//			
//			System.out.println("~~~  All customeres ~~~ ");
//			System.out.println(adminService.getAllCustomers());
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			System.out.println();
//			
//			int idCustomer = 2;
//			System.out.println("~~~ Update customer ~~~ ");
//			Customer customer = adminService.getOneCustomer(idCustomer);
//			System.out.println("~Before update: \n" + customer);
//			
//			customer.setFirstName("Margo");
//			customer.setLastName("Kagan");
//			customer.setEmail("m.kagan17@com");
//			customer.setPassword("0000");
//			
//			adminService.updateCustomer(customer);
//			System.out.println("~After update: \n" + customer);
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			System.out.println();
//			
//			int idDeleteCustomer = 1;
//			System.out.println("~~~ Delete customer ~~~");
//			Customer customerForDelite = adminService.getOneCustomer(idDeleteCustomer);
//			adminService.deleteCustomer(customerForDelite.getId());
//			System.out.println();
//			System.out.println("~ Customer with id - " + customerForDelite.getId() + " were deleted ~");
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			System.out.println();
//			
//			System.out.println("~~~ All customerss after changes ~~~");
//			System.out.println(adminService.getAllCustomers());
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			System.out.println();
//
//		}
//	}
//}
