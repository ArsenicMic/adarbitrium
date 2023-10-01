package be.adarbitrium.model.latin_toolbox;

import java.util.ArrayList;
import java.util.Iterator;

import be.adarbitrium.model.dao.DaoFactory;
import be.adarbitrium.model.dao.VocDao;

public class Vocabulaire {
	private ArrayList<? extends Mot> mVocListe;
	private ArrayList<Integer> mOptions;
	
	public Vocabulaire(Mot.Mot_type type, ArrayList<Integer> options) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		VocDao vocDao = daoFactory.getVocDao();
		mVocListe = vocDao.getVoc(type);
		mOptions = options;
		for (Iterator<Mot> iterator = (Iterator<Mot>) mVocListe.iterator(); iterator.hasNext();) {
			Mot mot = iterator.next();
			if (!options.contains(mot.getCategorie())){
				iterator.remove();
			}
		}
	}
	
	public ArrayList<? extends Mot> getVocListe(){
		return mVocListe;
	}
}
