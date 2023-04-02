//package app.core.filters;
//
//import java.io.IOException;
//import java.util.StringTokenizer;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//import app.core.auth.JwtUtil;
//import app.core.auth.User;
//@CrossOrigin
//public class AuthenticationFilter implements Filter {
//
//	private JwtUtil jwtUtil;
//
//	public AuthenticationFilter(JwtUtil jwtUtil) {
//		super();
//		this.jwtUtil = jwtUtil;
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		System.out.println("\n--- authentication filter started");
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		String requestMethod = httpRequest.getMethod();
//		HttpServletResponse resp = (HttpServletResponse) response;
//		resp.setHeader("Access-Control-Expose-Headers", "*");
//		resp.setHeader("Access-Control-Allow-Origin", "*");
//		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//		resp.setHeader("Access-Control-Max-Age", "3600");
//		resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With, X-Codingpedia");
//
//		// to handle pre-flight requests in case of cross-origin situations
//		if (requestMethod.equalsIgnoreCase("options")) {
//			System.out.println("--- PREFLIGHT (authentication filter)");
//			chain.doFilter(request, response);
//		} else {
//			try {
//				// check for a valid token
//				String authorization = httpRequest.getHeader("Authorization");
//				StringTokenizer tokenizer = new StringTokenizer(authorization);
//				System.out.println("tokenizer " + tokenizer);
//				String scheme = tokenizer.nextToken(); // Bearer
//				System.out.println("scheme " + scheme);
//
//				String token = tokenizer.nextToken(); // the JWT: aaa.bbb.ccc
//				System.out.println("token " + token);
//
//				System.out.println("---------- " + scheme);
//				
//				User user = this.jwtUtil.extractUser(token);
//				
//				System.out.println("--- " + user);
//				httpRequest.setAttribute("user", user);
//				// 2. pass the request on
//				chain.doFilter(request, response);
//			} catch (Exception e) {
//				// 2. OR block the request
//				System.out.println("--- invalid token: " + e);
////				HttpServletResponse resp = (HttpServletResponse) response;
////				resp.setHeader("Access-Control-Allow-Origin", "*"); // for CORS
//
//				// The HTTP WWW-Authenticate response header defines the HTTP authentication
//				// methods ("challenges") that might be used to gain access to a specific
//				// resource.
////				resp.setHeader("WWW-Authenticate", "Bearer realm=\"General API\"");
//
//				// The Access-Control-Expose-Headers response header allows a server to indicate
//				// which
//				// response headers should be made available to scripts running in the browser,
//				// in
//				// response to a cross-origin request.
////				resp.setHeader("Access-Control-Expose-Headers", "*");
//
//				resp.sendError(HttpStatus.UNAUTHORIZED.value(), "You need to login");
//			}
//		}
//
//		// 3. some more actions if needed
//		System.out.println("--- authentication filter is done");
//	}
//}