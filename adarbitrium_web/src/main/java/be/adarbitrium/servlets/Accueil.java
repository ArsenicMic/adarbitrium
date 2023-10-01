package be.adarbitrium.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.adarbitrium.model.testGraph;

@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Accueil() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ACCUEIL");
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			System.out.println("no Session");
			getServletContext().getRequestDispatcher("/login").forward(request, response);
		}
		else if (session.getAttribute("user") == null) {
			System.out.println("no user");
			getServletContext().getRequestDispatcher("/login").forward(request, response);
		}
		else {
			System.out.println("accueil-registered");
			String graphPath = getServletContext().getRealPath(".") + "/images/testChart.png";
			testGraph graph = new testGraph();
			graph.writeFile(graphPath);
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
