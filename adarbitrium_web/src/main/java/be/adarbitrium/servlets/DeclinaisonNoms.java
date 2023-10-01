package be.adarbitrium.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import be.adarbitrium.model.latin_quizz.Question;
import be.adarbitrium.model.latin_quizz.QuestionGenerator;
import be.adarbitrium.model.latin_toolbox.Mot;
import be.adarbitrium.model.latin_toolbox.Nom;
import be.adarbitrium.model.latin_toolbox.Vocabulaire;


@WebServlet("/DeclinaisonNoms")
public class DeclinaisonNoms extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeclinaisonNoms() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DECLINAISON-NOMS");
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
			System.out.println("declinaison-noms-registered");
			QuestionGenerator generator;
			if (session.getAttribute("questionGenerator") == null) {
				Vocabulaire vocabulaire = new Vocabulaire(Mot.Mot_type.TYPE_NOM, new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)));
				ArrayList<Nom> voc = (ArrayList<Nom>) vocabulaire.getVocListe();
				generator = new QuestionGenerator(QuestionGenerator.mQuestionType.TYPE_QUESTION_CAS_NOMBRE, voc);
				session.setAttribute("questionGenerator", generator);
			}
			else {
				generator = (QuestionGenerator) session.getAttribute("questionGenerator");
			}
			Question q = generator.generate();
			request.setAttribute("initialTime", 120);
			request.setAttribute("remainingTime", 120);
			request.setAttribute("currentScore", 0);
			request.setAttribute("question", q);
			this.getServletContext().getRequestDispatcher("/WEB-INF/declinaisonNoms.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		QuestionGenerator generator;
		int initialTime = 120;
		if (request.getParameter("tpsEx") != null) {
			initialTime = Integer.parseInt(request.getParameter("tpsEx")) * 60;
		}
		if (request.getParameter("hasCatChoice") != null) {
			String[] checkboxNamesList = {"1", "2", "3", "4", "5"};
			ArrayList<Integer> chosenCategories = new ArrayList<Integer>();
			for (String s : checkboxNamesList) {
				if (request.getParameter(s) != null) chosenCategories.add(Integer.parseInt(s));
			}
			if (chosenCategories.size() == 0) {
				for (int i = 1; i < 6; i++) {
					chosenCategories.add(i);
				}	
			}
			Vocabulaire vocabulaire = new Vocabulaire(Mot.Mot_type.TYPE_NOM, chosenCategories);
			ArrayList<Nom> voc = (ArrayList<Nom>) vocabulaire.getVocListe();
			generator = new QuestionGenerator(QuestionGenerator.mQuestionType.TYPE_QUESTION_CAS_NOMBRE, voc);			
			session.setAttribute("questionGenerator", generator);
			Question q = generator.generate();
			request.setAttribute("initialTime", initialTime);
			request.setAttribute("remainingTime", initialTime);
			request.setAttribute("currentScore", 0);
			String catJason = new Gson().toJson(chosenCategories);
			System.out.println(catJason);
			request.setAttribute("vocParams", catJason);
			request.setAttribute("question", q);
			this.getServletContext().getRequestDispatcher("/WEB-INF/declinaisonNoms.jsp").forward(request, response);
		}
		else if (request.getParameter("vocParams") != null) {
			System.out.println(request.getParameter("vocParams"));
			int[] chosenCategoriesA = new Gson().fromJson(request.getParameter("vocParams"),int[].class);
			ArrayList<Integer> chosenCategories = new ArrayList<Integer>();
			for (int i : chosenCategoriesA) {
				chosenCategories.add(i);
			}
			System.out.println(chosenCategories.toString());
			Vocabulaire vocabulaire = new Vocabulaire(Mot.Mot_type.TYPE_NOM, chosenCategories);
			ArrayList<Nom> voc = (ArrayList<Nom>) vocabulaire.getVocListe();
			generator = new QuestionGenerator(QuestionGenerator.mQuestionType.TYPE_QUESTION_CAS_NOMBRE, voc);			
			session.setAttribute("questionGenerator", generator);
			Question q = generator.generate();
			request.setAttribute("initialTime", initialTime);
			request.setAttribute("remainingTime", initialTime);
			request.setAttribute("currentScore", 0);
			request.setAttribute("vocParams", new Gson().toJson(chosenCategories));
			request.setAttribute("question", q);
			this.getServletContext().getRequestDispatcher("/WEB-INF/declinaisonNoms.jsp").forward(request, response);
		}

		else {
			if (session.getAttribute("questionGenerator") == null) {
				Vocabulaire vocabulaire = new Vocabulaire(Mot.Mot_type.TYPE_NOM, new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)));
				ArrayList<Nom> voc = (ArrayList<Nom>) vocabulaire.getVocListe();
				generator = new QuestionGenerator(QuestionGenerator.mQuestionType.TYPE_QUESTION_CAS_NOMBRE, voc);
				session.setAttribute("questionGenerator", generator);
			}
			else {
				generator = (QuestionGenerator) session.getAttribute("questionGenerator");
			}
			Question q = generator.generate();
			request.setAttribute("initialTime", (request.getParameter("initialTime") == null)? 120 : Integer.parseInt(request.getParameter("initialTime")));
			request.setAttribute("remainingTime", (request.getParameter("remaintingTime") == null)? 120 : Integer.parseInt(request.getParameter("remainingTime")));
			request.setAttribute("currentScore", (request.getParameter("currentScore") == null)? 0 : Integer.parseInt(request.getParameter("currentScore")));
			request.setAttribute("question", q);
			this.getServletContext().getRequestDispatcher("/WEB-INF/new-question-cas-nb.jsp").forward(request, response);
		}
	}

}
