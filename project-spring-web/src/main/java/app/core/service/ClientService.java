package app.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.auth.User;
import app.core.exception.CouponsSystemException;
import app.core.repository.CompanyRepository;
import app.core.repository.CouponRepository;
import app.core.repository.CustomerRepository;

@Service
@Transactional
public abstract class ClientService {
	
	@Autowired
	protected CompanyRepository companyRepos;
	@Autowired
	protected CustomerRepository customerRepos;
	@Autowired
	protected CouponRepository couponRepos;
	
	public abstract String login(User user )throws CouponsSystemException ;
		
}
