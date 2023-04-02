package app.core.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import app.core.entites.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Optional<Company> findByEmailAndPassword(String email, String password);

	boolean existsByName(String companyName);

	boolean existsByEmail(String companyEmail);
	
}
