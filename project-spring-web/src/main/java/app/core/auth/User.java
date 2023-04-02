package app.core.auth;

import javax.persistence.Id;
import app.core.loginManager.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
//	@Enumerated(EnumType.STRING)
	
	private ClientType clientType;
	
	

}
