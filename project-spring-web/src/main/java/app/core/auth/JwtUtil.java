package app.core.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import app.core.loginManager.ClientType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

@Component
public class JwtUtil extends JwtUtilAbstract<User, Integer> {

	@Override
	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
//		user.setPassword(null);
//		claims.put("user", user);
		claims.put("id", user.getId());
		claims.put("email", user.getEmail());
		claims.put("clientType", user.getClientType());
		String token = this.createToken(claims, user.getId());
		return token;
	}

	@Override
	public User extractUser(String token) throws JwtException {
		Claims claims = this.extractAllClaims(token);
		int id = Integer.parseInt(claims.getSubject());
		String email = claims.get("email", String.class);
		ClientType clientType= ClientType.valueOf(claims.get("clientType", String.class));
		User user= new User(id, email, null,clientType);
		return user;
	}


}
