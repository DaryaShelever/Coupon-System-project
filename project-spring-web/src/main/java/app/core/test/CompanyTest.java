//package app.core.test;
//
//import java.time.LocalDate;
//import java.time.Month;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import app.core.entites.Category;
//import app.core.entites.Company;
//import app.core.entites.Coupon;
//import app.core.loginManager.ClientType;
//import app.core.loginManager.LoginManager;
//import app.core.service.CompanyService;
//
////@Component
//@Order(2)
//public class CompanyTest implements CommandLineRunner {
//
//	@Autowired
//	private LoginManager loginManager;
//
//	@Override
//	public void run(String... args) throws Exception {
//		CompanyService companyService = (CompanyService) loginManager.login("teva@mail", "12345", ClientType.COMPANY);
//		if (companyService != null) {
//			Company company = companyService.getCompanyDetails();
//			System.out.println("~~~~~~~~~~~~~Hello " + company.getName() + "~~~~~~~~~~~~~");
//			System.out.println();
//
//			Coupon coupon1 = new Coupon();
//			coupon1.setCompany(company);
//			coupon1.setAmount(5);
//			coupon1.setCategory(Category.ELECTRICITY);
//			coupon1.setTitle("Computer");
//			coupon1.setDescription(
//					"A computer is a machine that can be programmed to carry out sequences of arithmetic or logical operations (computation) automatically.");
//			coupon1.setPrice(1500);
//			coupon1.setImage("https://th.bing.com/th/id/OIP.YChgcII6oJ4dR7XiBo5w4gHaEK?pid=ImgDet&rs=1");
//			coupon1.setStartDate(LocalDate.of(2022, Month.NOVEMBER, 5));
//
//			coupon1.setEndDate(LocalDate.of(2023, Month.JANUARY, 1));
//
//			Coupon coupon2 = new Coupon();
//			coupon2.setCompany(company);
//			coupon2.setTitle("Telephone");
//			coupon2.setAmount(200);
//			coupon2.setCategory(Category.ELECTRICITY);
//			coupon2.setDescription(
//					"A telephone is a telecommunications device that permits two or more users to conduct a conversation when they are too far apart to be easily heard directly.");
//			coupon2.setPrice(1000);
//			coupon2.setImage("Nokia");
//			coupon2.setStartDate(LocalDate.of(2023, Month.JANUARY, 1));
//			coupon2.setEndDate(LocalDate.of(2023, Month.FEBRUARY, 13));
//
//			Coupon coupon3 = new Coupon();
//			coupon3.setCompany(company);
//			coupon3.setAmount(8);
//			coupon3.setCategory(Category.FOOD);
//			coupon3.setTitle("Bread");
//			coupon3.setDescription(
//					"Bread is a staple food prepared from a dough of flour (usually wheat) and water, usually by baking.");
//			coupon3.setPrice(5);
//			coupon3.setImage("https://th.bing.com/th/id/OIP.1kBL2gXF_NVflMYGJobd7QHaHO?pid=ImgDet&rs=1");
//			coupon3.setStartDate(LocalDate.of(2023, Month.JANUARY, 1));
//			coupon3.setEndDate(LocalDate.of(2023, Month.MARCH, 21));
//
//			Coupon coupon4 = new Coupon();
//			coupon4.setCompany(company);
//			coupon4.setAmount(8);
//			coupon4.setCategory(Category.RESTORANT);
//			coupon4.setTitle("Chair");
//			coupon4.setDescription("Fancy Chair");
//			coupon4.setPrice(999);
//			coupon4.setImage(
//					"https://th.bing.com/th/id/R.1d6bf6ee108f1f53ce5fe07d601f09d0?rik=N5q6v3poCOzHGA&pid=ImgRaw&r=0");
//			coupon4.setStartDate(LocalDate.of(2023, Month.DECEMBER, 20));
//			coupon4.setEndDate(LocalDate.of(2024, Month.JANUARY, 23));
//
//			companyService.addCoupon(coupon1);
//			companyService.addCoupon(coupon2);
//			companyService.addCoupon(coupon3);
//			companyService.addCoupon(coupon4);
//
//			System.out.println("~ Coupons were created ~");
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//
//			System.out.println("~~~ All coupons ~~~");
//			System.out.println(companyService.getAllCoupons());
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//
//			System.out.println("~~~ Get one coupon ~~~");
//			System.out.println(companyService.getOneCoupon(coupon1.getId()));
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//
//			Category category = Category.ELECTRICITY;
//			System.out.println("~~~ Get one coupon by Category " + category + " ~~~");
//			System.out.println(companyService.getCompaniesCouponsByCategory(category));
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//
//			Double maxPrice = 1800.0;
//			System.out.println("~~~ Get one coupon up to Max price ~~~");
//			System.out.println(companyService.getCompaniesCouponsUpToMaxPrice(maxPrice));
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//
//			int idUpdateCoupon = 2;
//			System.out.println("~~~ Update Coupon ~~~");
//			Coupon couponForUpdate = companyService.getOneCoupon(idUpdateCoupon);
//			System.out.println("~Before update: ");
//			System.out.println(couponForUpdate);
//
//			couponForUpdate.setAmount(20);
//			couponForUpdate.setCategory(Category.ELECTRICITY);
//			couponForUpdate.setTitle("Mobile phone");
//			couponForUpdate.setDescription("A mobile phone[a] is a portable telephone that can make and receive calls");
//			couponForUpdate.setPrice(2000);
//			couponForUpdate.setImage("nokiaaaa");
//			couponForUpdate.setStartDate(LocalDate.of(2023, Month.JANUARY, 28));
//			couponForUpdate.setEndDate(LocalDate.of(2023, Month.FEBRUARY, 26));
//
//			companyService.updateCoupon(couponForUpdate);
//			System.out.println("~After update: ");
//			System.out.println(couponForUpdate);
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//			System.out.println();
//
//			int idDeleteCoupon = 3;
//			System.out.println("~~~ Delete Coupon ~~~");
//			Coupon couponForDelite = companyService.getOneCoupon(idDeleteCoupon);
//			companyService.deleteCoupon(couponForDelite.getId());
//
//			System.out.println("~ Coupon '" + couponForDelite.getTitle() + "' were deleted ~");
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//
//			System.out.println("~~~ All Coupons after changes ~~~");
//			System.out.println(companyService.getAllCoupons());
//			System.out.println();
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			System.out.println();
//		}
//	}
//}
