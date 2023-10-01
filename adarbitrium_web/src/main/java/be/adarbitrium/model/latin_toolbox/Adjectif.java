package be.adarbitrium.model.latin_toolbox;

import java.util.ArrayList;

public class Adjectif extends Mot {

    public enum Tableaux {
        DECLINAISON_CLASSE_1(new String[][]{
                new String[]{"us", "e", "um", "i", "o", "o", "i", "i", "os", "orum", "is", "is"},
                new String[]{"a", "a", "am", "ae", "ae", "a", "ae", "ae", "as", "arum", "is", "is"},
                new String[]{"um", "um", "um", "i", "o", "o", "a", "a", "a", "orum", "is", "is"}}),
        DECLINAISON_CLASSE_1_ER(new String[][]{
                new String[]{"", "", "um", "i", "o", "o", "i", "i", "os", "orum", "is", "is"},
                new String[]{"a", "a", "am", "ae", "ae", "a", "ae", "ae", "as", "arum", "is", "is"},
                new String[]{"um", "um", "um", "i", "o", "o", "a", "a", "a", "orum", "is", "is"}}),
        DECLINAISON_CLASSE_2(new String[][]{
                new String[]{"", "", "em", "is", "i", "i", "es", "es", "es", "ium", "ibus", "ibus"},
                new String[]{"", "", "em", "is", "i", "i", "es", "es", "es", "ium", "ibus", "ibus"},
                new String[]{"", "", "", "is", "i", "i", "ia", "ia", "ia", "ium", "ibus", "ibus"}});

        String[][] tableau;

        Tableaux(String[][] decl){
            this.tableau = decl;
        }

        public String[][] getDeclinaison(){
            return tableau;
        }
    }

    private Tableaux mTableau;
    private String[][] mDeclinaisons = new String[3][12];

    public Adjectif(ArrayList<String> lemme, String traduction) {
        mLemme = lemme;
        mTraduction = traduction;
        mType=Mot_type.TYPE_ADJECTIF;
        identifier();
        flechir();
    }

    @Override
    protected void identifier(){
        if (mLemme.get(0).endsWith("us")){
            mRadical.add(0, mLemme.get(0).substring(0, mLemme.get(0).length() - 2));
            mRadical.add(1, mLemme.get(0).substring(0, mLemme.get(0).length() - 2));
            mTableau = Tableaux.DECLINAISON_CLASSE_1;
            mCategorie = 1;
        }
        else if (mLemme.get(0).endsWith("er")){
            mRadical.add(0, mLemme.get(0));
            mRadical.add(1, mLemme.get(1).substring(0, mLemme.get(1).length() - 1));
            mTableau = Tableaux.DECLINAISON_CLASSE_1_ER;
            mCategorie = 1;
        }
        else{
            mRadical.add(0, mLemme.get(0));
            //adjectifs en "ns, ntis"
            if (mLemme.get(0).endsWith("ens")) mRadical.add(1, mLemme.get(0).substring(0, mLemme.get(0).length() - 1) + "t");
            //adjectifs en "is, is"
            else mRadical.add(1, mLemme.get(0).substring(0, mLemme.get(0).length() - 2));
            mRadical.add(2, mLemme.get(2));
            mTableau = Tableaux.DECLINAISON_CLASSE_2;
            mCategorie = 2;
        }
    }

    @Override
    protected void flechir(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 12; j++) {
                if (i == 2 && mTableau == Tableaux.DECLINAISON_CLASSE_2 && j < 3)
                    mDeclinaisons[i][j] = mRadical.get(2);
                else if (mTableau == Tableaux.DECLINAISON_CLASSE_2 && j < 2)
                    mDeclinaisons[i][j] = mRadical.get(0) + mTableau.getDeclinaison()[i][j];
                else mDeclinaisons[i][j] = mRadical.get(1) + mTableau.getDeclinaison()[i][j];
            }
        }
    }

    public ArrayList<String> getLemme() {
        return mLemme;
    }

    public String[][] getDeclinaisons() {
        return mDeclinaisons;
    }
}
