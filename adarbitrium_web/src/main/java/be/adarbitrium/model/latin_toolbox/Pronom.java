package be.adarbitrium.model.latin_toolbox;

import java.util.ArrayList;

public class Pronom extends Mot {

    public enum Tableaux {
        DECLINAISON_IS_EA_ID(new String[][]{
                new String[]{"is", null, "eum", "eius", "ei", "eo", "ei", null, "eos", "eorum", "eis", "eis"},
                new String[]{"ea", null, "eam", "eius", "ei", "ea", "eae", null, "eas", "earum", "eis", "eis"},
                new String[]{"id", null, "id", "eius", "ei", "eo", "ea", null, "ea", "eorum", "eis", "eis"}}),
        DECLINAISON_IPSE_IPSA_IPSUM(new String[][]{
                new String[]{"ipse", null, "ipsum", "ipius", "ipsi", "ipso", "ipsi", null, "ipsos", "ipsorum", "ipsis", "ipsis"},
                new String[]{"ipsa", null, "ipsam", "ipius", "ipsi", "ipsa", "ipsae", null, "ipsas", "ipsarum", "ipsis", "ipsis"},
                new String[]{"ipsum", null, "ipsum", "ipius", "ipsi", "ipso", "ipsa", null, "ipsa", "ipsorum", "ipsis", "ipsis"}}),
        DECLINAISON_IDEM_EADEM_IDEM(new String[][]{
                new String[]{"idem", null, "eundem", "eiusdem", "eidem", "eodem", "eidem", null, "eosdem", "eorundem", "eisdem", "eisdem"},
                new String[]{"eadem", null, "eandem", "eiusdem", "eidem", "eadem", "eaedem", null, "easdem", "earundem", "eisdem", "eisdem"},
                new String[]{"idem", null, "idem", "eiusdem", "eidem", "eodem", "eadem", null, "eadem", "eorundem", "eisdem", "eisdem"}}),
        DECLINAISON_HIC_HAEC_HOC(new String[][]{
                new String[]{"hic", null, "hunc", "huius", "huic", "hoc", "hi", null, "hos", "horum", "his", "his"},
                new String[]{"haec", null, "hanc", "huius", "huic", "hac", "hae", null, "has", "harum", "his", "his"},
                new String[]{"hoc", null, "hoc", "huius", "huic", "hoc", "haec", null, "haec", "horum", "his", "his"}}),
        DECLINAISON_ISTE_ISTA_ISTUD(new String[][]{
                new String[]{"iste", null, "istum", "istius", "isti", "isto", "isti", null, "istos", "istorum", "istis", "istis"},
                new String[]{"ista", null, "istam", "istius", "isti", "ista", "istae", null, "istas", "istarum", "istis", "istis"},
                new String[]{"istud", null, "istud", "istius", "isti", "isto", "ista", null, "ista", "istorum", "istis", "istis"}}),
        DECLINAISON_ILLE_ILLA_ILLUD(new String[][]{
                new String[]{"ille", null, "illum", "illius", "illi", "illo", "illi", null, "illos", "illorum", "illis", "illis"},
                new String[]{"illa", null, "illam", "illius", "illi", "illa", "illae", null, "illas", "illarum", "illis", "illis"},
                new String[]{"illud", null, "illud", "illius", "illi", "illo", "illa", null, "illa", "illorum", "illis", "illis"}}),
        DECLINAISON_QUI_QUAE_QUOD(new String[][]{
                new String[]{"qui", null, "quem", "cuius", "cui", "quo", "qui", null, "quos", "quorum", "quibus", "quibus"},
                new String[]{"quae", null, "quam", "cuius", "cui", "qua", "quae", null, "quas", "quarum", "quibus", "quibus"},
                new String[]{"quod", null, "quod", "cuius", "cui", "quo", "quae", null, "quae", "quorum", "quibus", "quibus"}});

        String[][] tableau;

        Tableaux(String[][] decl){
            this.tableau = decl;
        }

        public String[][] getDeclinaison(){
            return tableau;
        }
    }

    private Tableaux mTableau;
    private String[][] mDeclinaisons;

    public Pronom(ArrayList<String> lemme, String traduction) {
        mLemme = lemme;
        mTraduction = traduction;
        mType=Mot_type.TYPE_PRONOM;
        identifier();
        flechir();
    }

    @Override
    protected void identifier(){
        switch (mLemme.get(0)){
            case "is":
                mTableau = Tableaux.DECLINAISON_IS_EA_ID;
                break;
            case "ipse":
                mTableau = Tableaux.DECLINAISON_IPSE_IPSA_IPSUM;
                break;
            case "idem":
                mTableau = Tableaux.DECLINAISON_IDEM_EADEM_IDEM;
                break;
            case "hic":
                mTableau = Tableaux.DECLINAISON_HIC_HAEC_HOC;
                break;
            case "iste":
                mTableau = Tableaux.DECLINAISON_ISTE_ISTA_ISTUD;
                break;
            case "ille":
                mTableau = Tableaux.DECLINAISON_ILLE_ILLA_ILLUD;
                break;
            case "qui":
                mTableau = Tableaux.DECLINAISON_QUI_QUAE_QUOD;
                break;
        }
    }

    @Override
    protected void flechir(){
        mDeclinaisons = mTableau.getDeclinaison();
    }

    public ArrayList<String> getLemme() {
        return mLemme;
    }

    public String[][] getDeclinaisons() {
        return mDeclinaisons;
    }
}





