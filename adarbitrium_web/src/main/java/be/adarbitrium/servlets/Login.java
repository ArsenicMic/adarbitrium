package be.adarbitrium.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.adarbitrium.model.User;
import be.adarbitrium.model.UserAuthToken;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LOGIN_GET");
		Cookie[] cookies = request.getCookies();
		Cookie selectorCookie = null;
		Cookie validatorCookie = null;
		String validator = null;
		String selector = null;
		boolean isLogged = false;
		
		String from = (String)request.getAttribute("javax.servlet.forward.request_uri");
		System.out.println(from);
		
		//url pour renvoyer l'utilisateur vers la page demandée après identification
		if (from == null) from = "";
		if (from.contains("/adarbitrium_web/"))from = from.replace("/adarbitrium_web/", "");
		else from = "";

		//vérification de l'existence de cookies de userAuthToken
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if (name.equals("selector")) {
					selectorCookie = cookie;
					selector = cookie.getValue();
				}
				else if (name.equals("validator")) {
					validatorCookie = cookie;
					validator = cookie.getValue();
				}
			}
			if (selector != null && validator != null) {
				UserAuthToken uat = new UserAuthToken(selector, validator);
				User user = new User(uat);
				if (user.isRegistered()) {
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					
					//destroy used token
					uat.destroy();
					
					//make a new one
					UserAuthToken authToken = new UserAuthToken(user);
					selectorCookie.setValue(authToken.getSelector());
					validatorCookie.setValue(authToken.getValidator());
					selectorCookie.setMaxAge(60*60*24*30);
					validatorCookie.setMaxAge(60*60*24*30);
					response.addCookie(selectorCookie);
					response.addCookie(validatorCookie);
					System.out.println("Cookies updated");

					isLogged = true;
				}
				
			}
		}
		
		//renvoi vers la page d'origine si authentification réussie, sinon page login
		if (isLogged) response.sendRedirect(request.getContextPath() + from);
		else {
			request.setAttribute("from", from);
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LOGIN_POST");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);
		System.out.println("Registering " + username);
		boolean isLogged = false;
		if (user.isRegistered()) {
			System.out.println("Registered!");

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			System.out.println("Attribute user = " + session.getAttribute("user").toString());

			if (request.getParameter("rememberMe") != null) {
				UserAuthToken authToken = new UserAuthToken(user);
				Cookie selectorCookie = new Cookie("selector", authToken.getSelector());
				Cookie validatorCookie = new Cookie("validator", authToken.getValidator());
				selectorCookie.setMaxAge(60*60*24*30);
				validatorCookie.setMaxAge(60*60*24*30);
				response.addCookie(selectorCookie);
				response.addCookie(validatorCookie);
				System.out.println("Cookies added");
			}
			
			isLogged = true;
		}
		//renvoi vers la page d'origine si authentification réussie, sinon page login
		if (isLogged) response.sendRedirect(request.getContextPath() + "/" + request.getParameter("from"));
		else this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

}
