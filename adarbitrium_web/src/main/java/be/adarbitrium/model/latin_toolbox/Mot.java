package be.adarbitrium.model.latin_toolbox;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Mot {

    public enum Mot_type {
        TYPE_NOM,
        TYPE_PRONOM,
        TYPE_ADJECTIF,
        TYPE_VERBE
    }

    protected ArrayList<String> mLemme; //noms -> Nom sg, gén sg, genre
    protected String mTraduction;
    protected Mot_type mType;
    protected final ArrayList<String> mRadical = new ArrayList<>();
    protected final ArrayList<String> mFlexion = new ArrayList<>();
    protected int mCategorie;

	protected abstract void identifier();

    protected abstract void flechir();

    protected boolean isParisyllabique(){
        int rad = 0;
        char[] lemme0ToChar = mLemme.get(0).toCharArray();
        char[] lemme1ToChar = mLemme.get(1).toCharArray();
        for (int i = 0; i<lemme0ToChar.length; i++){
            if (lemme0ToChar[i] == lemme1ToChar[i]) rad +=1;
            else break;
        }
        char[][] changement = new char[][]{Arrays.copyOfRange(lemme0ToChar, rad, lemme0ToChar.length), Arrays.copyOfRange(lemme1ToChar, rad, lemme1ToChar.length)};
        int nbVoyNom = 0;
        int nbVoyGen = 0;
        for (int i = 0; i < changement[0].length; i++){
            if (isVowel(changement[0][i])) nbVoyNom += 1;
        }
        for (int i = 0; i < changement[1].length; i++){
            if (isVowel(changement[1][i])) nbVoyGen += 1;
        }
        return nbVoyNom == nbVoyGen;
    }

    protected boolean isVowel(char ch){
        char[] voyelles = new char[]{'a', 'e', 'i', 'o', 'u', 'y'};
        for (char v : voyelles){
            if (v == ch) return true;
        }
        return false;
    }


    public ArrayList<String> getFlexion() {
        return mFlexion;
    }

    public Mot_type getType() {
        return mType;
    }

    public ArrayList<String> getLemme() {
        return mLemme;
    }

    public String getTraduction() {
        return mTraduction;
    }

    public int getCategorie() {
		return mCategorie;
	}
}
