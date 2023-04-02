package app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import app.core.auth.JwtUtil;
import app.core.auth.User;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Component
@Order(1)
//@CrossOrigin(origins = { "http://localhost:3000",http://localhost:8080/" }, allowedHeaders = "*", allowCredentials = "true")
public class AuthenticationFilter2 implements Filter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.err.println("----------this is filter-----------");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

//		resp.setHeader("Access-Control-Expose-Headers", "*");
//		resp.setHeader("Access-Control-Allow-Origin", "*");
//		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//		resp.setHeader("Access-Control-Max-Age", "3600");
//		resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With, X-Codingpedia");
		// --BEGIN: добавлено для решения проблемы с CORS
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With, X-Codingpedia");
		resp.setHeader("Access-Control-Max-Age", "3600");
		resp.setHeader("Access-Control-Allow-Credentials", "true");

		if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
			resp.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		// END: добавлено для решения проблемы с CORS

		String requestUri = req.getRequestURI();
		System.out.println("request uri- " + requestUri);

		String requestMethod = req.getMethod();
		System.out.println("request method- " + requestMethod);

		if (requestMethod.equalsIgnoreCase("options")) {
			System.out.println("options");
			chain.doFilter(request, response);
			return;
		}

		if (requestUri.contains("/api/")) {
			String token = req.getHeader("Authorization");
			System.out.println(token);
			try {
//				resp.setHeader("Access-Control-Allow-Origin", "*");
//				resp.setHeader("Access-Control-Expose-Headers", "*");
				String tokenValue = token.substring(7);
				System.out.println(tokenValue);
				User user = this.jwtUtil.extractUser(tokenValue);
				System.out.println(user);
				req.setAttribute("user", user);

				chain.doFilter(request, response);

			} catch (Exception e) {
				System.out.println("invalid token: " + e.getMessage());
//				resp.setHeader("Access-Control-Allow-Origin", "*");
//				resp.setHeader("Access-Control-Expose-Headers", "*");
				if (e.getMessage().contains("expired")) {

					resp.sendError(HttpStatus.UNAUTHORIZED.value(), "The user is disconnected, Please Reconnect");
				} else {
					resp.sendError(HttpStatus.UNAUTHORIZED.value(), "you need to login - " + e.getMessage());

				}
			}
		} else {
//			resp.setHeader("Access-Control-Allow-Origin", "*");
//			resp.setHeader("Access-Control-Expose-Headers", "*");
			chain.doFilter(request, response);
			System.out.println("-------------------Good Filter--------------------");

		}
	}

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("title").version("version").description("description"))
				.addSecurityItem(new SecurityRequirement().addList("my security"))
				.components(new Components().addSecuritySchemes("my security",
						new SecurityScheme().name("my security").type(SecurityScheme.Type.HTTP).scheme("bearer")));
	}

}