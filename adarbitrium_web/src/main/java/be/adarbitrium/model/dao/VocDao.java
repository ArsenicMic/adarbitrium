package be.adarbitrium.model.dao;

import java.util.ArrayList;

import be.adarbitrium.model.latin_toolbox.Mot;

public interface VocDao {
	public boolean addVoc(Mot mot);
	public boolean updateVoc(Mot mot);
	public Mot fetchFromLemme(String[] lemme);
	public ArrayList<? extends Mot> getVoc(Mot.Mot_type type);
	public boolean delete(Mot mot);
}
