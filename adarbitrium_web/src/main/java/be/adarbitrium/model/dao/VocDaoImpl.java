package be.adarbitrium.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.adarbitrium.model.User;
import be.adarbitrium.model.latin_toolbox.Adjectif;
import be.adarbitrium.model.latin_toolbox.Mot;
import be.adarbitrium.model.latin_toolbox.Mot.Mot_type;
import be.adarbitrium.model.latin_toolbox.Nom;

public class VocDaoImpl implements VocDao {
	private DaoFactory daoFactory;
	VocDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public boolean addVoc(Mot mot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateVoc(Mot mot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Mot fetchFromLemme(String[] lemme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<? extends Mot> getVoc(Mot_type type) {
		ArrayList<Mot> voc = new ArrayList<Mot>();
		String dbName = "";
		switch(type) {
		case TYPE_NOM:
			dbName = "adarbitrium_voc_noms";
			break;
		case TYPE_ADJECTIF:
			dbName = "adarbitrium_voc_adjectifs";
			break;
		case TYPE_VERBE:			
			dbName = "adarbitrium_voc_verbes";
		break;

		case TYPE_PRONOM:
			dbName = "adarbitrium_voc_pronoms";
			break;
		}
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(
            		"SELECT * FROM " + dbName +" ;");

            switch(type) {
    		case TYPE_NOM:
    			while (resultat.next()) {
                	ArrayList<String> lemme = new ArrayList<String>();
                	lemme.add(resultat.getString("nom_sg"));
                	lemme.add(resultat.getString("gen_sg"));
                	lemme.add(resultat.getString("genre"));
                    String trad = resultat.getString("traduction");
                    Nom nom = new Nom(lemme, trad);
                    voc.add(nom);
                }    			
    			break;
    			
    		case TYPE_ADJECTIF:
    			while (resultat.next()) {
                	ArrayList<String> lemme = new ArrayList<String>();
                	lemme.add(resultat.getString("nom_m"));
                	lemme.add(resultat.getString("nom_f"));
                	lemme.add(resultat.getString("nom_n"));
                    String trad = resultat.getString("traduction");
                    Adjectif adj = new Adjectif(lemme, trad);
                    System.out.println(adj.toString());
                    voc.add(adj);
                }    			
    			break;
     			
    		case TYPE_VERBE:			
    		break;

    		case TYPE_PRONOM:
    			break;
    		}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voc;


	}

	@Override
	public boolean delete(Mot mot) {
		// TODO Auto-generated method stub
		return false;
	}

}
