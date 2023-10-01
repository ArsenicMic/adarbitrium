package be.adarbitrium.model.latin_toolbox;

import java.util.ArrayList;

public class Nom extends Mot {

    public enum Terminaisons {
        PREMIERE_DECLINAISON(new String[]{"a", "a", "am", "ae", "ae", "a", "ae", "ae", "as", "arum", "is", "is"}),
        DEUXIEME_DECLINAISON_US(new String[]{"us", "e", "um", "i", "o", "o", "i", "i", "os", "orum", "is", "is"}),
        DEUXIEME_DECLINAISON_ER(new String[]{"er", "er", "um", "i", "o", "o", "i", "i", "os", "orum", "is", "is"}),
        DEUXIEME_DECLINAISON_UM(new String[]{"um", "um", "um", "i", "o", "o", "a", "a", "a", "orum", "is", "is"}),
        TROISIEME_DECLINAISON_N_PAR(new String[]{"", "", "", "is", "i", "i", "ia", "ia", "ia", "ium", "ibus", "ibus"}),
        TROISIEME_DECLINAISON_N_IMPAR(new String[]{"", "", "", "is", "i", "e", "a", "a", "a", "um", "ibus", "ibus"}),
        TROISIEME_DECLINAISON_MF_PAR(new String[]{"", "", "em", "is", "i", "e", "es", "es", "es", "ium", "ibus", "ibus"}),
        TROISIEME_DECLINAISON_MF_IMPAR(new String[]{"", "", "em", "is", "i", "e", "es", "es", "es", "um", "ibus", "ibus"}),
        QUATRIEME_DECLINAISON_MF(new String[]{"us", "us", "um", "us", "ui", "u", "us", "us", "us", "uum", "ibus", "ibus"}),
        QUATRIEME_DECLINAISON_N(new String[]{"u", "u", "u", "us", "ui", "u", "ua", "ua", "ua", "uum", "ibus", "ibus"}),
        CINQUIEME_DECLINAISON(new String[]{"es", "es", "em", "ei", "ei", "e", "es", "es", "es", "erum", "ebus", "ebus"});

        String[] terminaisons;

        Terminaisons(String[] term){
            this.terminaisons = term;
        }

        public String[] getTerminaisons(){
            return terminaisons;
        }
    }


    private Terminaisons mTableDeclinaison;
    private final String[] mDeclinaisons = new String[12];

    public Nom(ArrayList<String> lemme, String traduction) {
        mLemme = lemme;
        mTraduction = traduction;
        mType = Mot_type.TYPE_NOM;
        identifier();
        flechir();
    }

    @Override
    protected void identifier(){
        if (mLemme.get(1).endsWith("ae")) {
            mRadical.add(0, mLemme.get(0).substring(0, mLemme.get(0).length() - 1));
            mRadical.add(1,mLemme.get(1).substring(0, mLemme.get(1).length() - 2));
            mTableDeclinaison = Terminaisons.PREMIERE_DECLINAISON;
            mCategorie = 1;
        }
        else if (mLemme.get(1).endsWith("i") && mLemme.get(0).endsWith("us")) {
            mRadical.add(0,  mLemme.get(0).substring(0, mLemme.get(0).length() - 2));
            mRadical.add(1,mLemme.get(1).substring(0, mLemme.get(1).length() - 1));
            mTableDeclinaison = Terminaisons.DEUXIEME_DECLINAISON_US;
            mCategorie = 2;
        }
        else if (mLemme.get(1).endsWith("i") && mLemme.get(0).endsWith("um")) {
            mRadical.add(0, mLemme.get(0).substring(0, mLemme.get(0).length() - 2));
            mRadical.add(1,mLemme.get(1).substring(0, mLemme.get(1).length() - 1));
            mTableDeclinaison = Terminaisons.DEUXIEME_DECLINAISON_UM;
            mCategorie = 2;
        }
        else if (mLemme.get(1).endsWith("i") && mLemme.get(0).endsWith("er")) {
            mRadical.add(0, mLemme.get(0).substring(0, mLemme.get(0).length() - 2));
            mRadical.add(1,mLemme.get(1).substring(0, mLemme.get(1).length() - 1));
            mTableDeclinaison = Terminaisons.DEUXIEME_DECLINAISON_ER;
            mCategorie = 2;
        }
        else if (mLemme.get(1).endsWith("is")) {
            mRadical.add(0, mLemme.get(0));
            mRadical.add(1,mLemme.get(1).substring(0, mLemme.get(1).length() - 2));
            mCategorie = 3;
            boolean isPari = isParisyllabique();
            if (mLemme.get(2).equals("n")){
                if (isPari) mTableDeclinaison = Terminaisons.TROISIEME_DECLINAISON_N_PAR;
                else mTableDeclinaison = Terminaisons.TROISIEME_DECLINAISON_N_IMPAR;
            }
            else {
                if (isPari) mTableDeclinaison = Terminaisons.TROISIEME_DECLINAISON_MF_PAR;
                else mTableDeclinaison = Terminaisons.TROISIEME_DECLINAISON_MF_IMPAR;
            }
        }
        else if (mLemme.get(1).endsWith("us") && mLemme.get(0).endsWith("us")) {
            mRadical.add(0, mLemme.get(0).substring(0, mLemme.get(0).length() - 2));
            mRadical.add(1,mLemme.get(1).substring(0, mLemme.get(1).length() - 2));
            mTableDeclinaison = Terminaisons.QUATRIEME_DECLINAISON_MF;
            mCategorie = 4;
        }
        else if (mLemme.get(1).endsWith("us") && mLemme.get(0).endsWith("u")) {
            mRadical.add(0, mLemme.get(0).substring(0, mLemme.get(0).length() - 1));
            mRadical.add(1,mLemme.get(1).substring(0, mLemme.get(1).length() - 2));
            mTableDeclinaison = Terminaisons.QUATRIEME_DECLINAISON_N;
            mCategorie = 4;
        }
        else if (mLemme.get(1).endsWith("ei") && mLemme.get(0).endsWith("es")) {
            mRadical.add(0, mLemme.get(0).substring(0, mLemme.get(0).length() - 2));
            mRadical.add(1,mLemme.get(1).substring(0, mLemme.get(1).length() - 2));
            mTableDeclinaison = Terminaisons.CINQUIEME_DECLINAISON;
            mCategorie = 5;
        }
        else System.out.println("déclinaison non reconnue...");
    }
    
    @Override
    protected void flechir(){
        for (int i = 0; i < 12; i++){
            if (i<2) mDeclinaisons[i] = mRadical.get(0) + mTableDeclinaison.getTerminaisons()[i];
            else mDeclinaisons[i] = mRadical.get(1) + mTableDeclinaison.getTerminaisons()[i];
            if (mLemme.get(2).equals("n")){
                mDeclinaisons[2] = mDeclinaisons[0];
                mDeclinaisons[8] = mDeclinaisons[6];
            }
        }
    }

    public ArrayList<String> getLemme() {
        return mLemme;
    }

    public String[] getDeclinaisons() {
        return mDeclinaisons;
    }
    
    public String[] getTerminaisons() {
    	return mTableDeclinaison.getTerminaisons();
    }
}





