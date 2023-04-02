package app.core.job;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import app.core.repository.CouponRepository;

@Component
@Transactional
public class DailyJob {

	@Autowired
	private CouponRepository couponRepository;

	@Scheduled(timeUnit = TimeUnit.DAYS, fixedRate = 1)
	public void run() {
		couponRepository.deleteByEndDateBefore(LocalDate.now());
		System.out.println("+++Jod+++");
	}

}
