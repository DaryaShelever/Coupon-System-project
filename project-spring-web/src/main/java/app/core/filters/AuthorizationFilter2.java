package app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import app.core.auth.User;
import app.core.loginManager.ClientType;

@Component
//@CrossOrigin(origins = "http://localhost:3000")
@Order(2)
public class AuthorizationFilter2 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("=== Authorization filter started");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Expose-Headers", "*");
		httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		httpResponse.setHeader("Access-Control-Max-Age", "3600");
		httpResponse.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Authorization, X-Requested-With, X-Codingpedia");

		// to handle pre-flight requests in case of cross-origin situations
		if (httpRequest.getMethod().equalsIgnoreCase("options")) {
			System.out.println("=== PREFLIGHT (Authorization filter)");
			chain.doFilter(request, response);
		} else {
			String requestUri = httpRequest.getRequestURI();
			User user = (User) httpRequest.getAttribute("user");
//			httpResponse.setHeader("Access-Control-Allow-Origin", "*");
			System.out.println("=== Authorization filter - request uri: " + requestUri);

			if (requestUri.contains("/api/admin") && user.getClientType() != ClientType.ADMIN) {
				httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // for CORS
				httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"ADMIN API\"");
				httpResponse.setHeader("Access-Control-Expose-Headers", "*");
				httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Only Admin can access this zone!");
			} else if (requestUri.contains("/api/company") && user.getClientType() != ClientType.COMPANY) {
				httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // for CORS
				httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"COMPANY API\"");
				httpResponse.setHeader("Access-Control-Expose-Headers", "*");
				httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Only Company can access this zone!");
			} else if (requestUri.contains("/api/customer") && user.getClientType() != ClientType.CUSTOMER) {
				httpResponse.setHeader("Access-Control-Allow-Origin", "*"); // for CORS
				httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"CUSTOMER API\"");
				httpResponse.setHeader("Access-Control-Expose-Headers", "*");
				httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Only Customer can access this zone!");
			} else {
				chain.doFilter(request, response);
				httpResponse.setHeader("Access-Control-Allow-Origin", "*");
			}
			System.out.println("=== Authorization filter is done");
		}
	}
}