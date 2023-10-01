package be.adarbitrium.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import be.adarbitrium.model.User;
import be.adarbitrium.model.dao.DaoFactory;
import be.adarbitrium.model.latin_quizz.QuestionStat;
import be.adarbitrium.model.latin_toolbox.Mot;

/**
 * Servlet implementation class ExerciceResult
 */
@WebServlet("/ExerciceResult")
public class ExerciceResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExerciceResult() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// no get -> redirect to exercices
		System.out.println("RESULT");		
		response.sendRedirect(request.getContextPath() + "/exercices");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RESULT");
		int score = Integer.parseInt(request.getParameter("score"));
		int initialTime = Integer.parseInt(request.getParameter("initialTime"));
		String vocParams = request.getParameter("vocParams");
		System.out.println(vocParams);
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		int hiScore = user.getHiScores()[0];
		request.setAttribute("score", score);
		request.setAttribute("previousBest", hiScore);
		request.setAttribute("initialTime", initialTime);
		request.setAttribute("vocParams", vocParams);
		String statsJson = request.getParameter("stats");
		System.out.println(statsJson);
		Gson gson = new Gson();
		Object[][] statsObjects = gson.fromJson(statsJson, Object[][].class);
		ArrayList<QuestionStat> stats = new ArrayList<QuestionStat>();
		for (Object[] stat : statsObjects) {
			Mot.Mot_type type = Mot.Mot_type.valueOf((String)stat[0]);
			int motCat = Integer.parseInt((String)stat[1]);
			String terminaison = (String)stat[2];
			Double timeUsedDouble = (Double) stat[3];
			int timeUsed = timeUsedDouble.intValue();
			Double failedAttemptsDouble = (Double) stat[4];
			int failedAttempts = failedAttemptsDouble.intValue();
			QuestionStat qs = new QuestionStat(type, motCat, timeUsed, terminaison, failedAttempts, user.getUserId());
			stats.add(qs);
		}
		for (QuestionStat stat : stats) {
			System.out.println(stat.toString());
			stat.writeStatToDb();
			
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/exerciceResult.jsp").forward(request, response);
	}

}
