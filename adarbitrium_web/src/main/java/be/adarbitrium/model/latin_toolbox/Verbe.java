package be.adarbitrium.model.latin_toolbox;

import java.util.ArrayList;

public class Verbe extends Mot {
	
	public enum Terminaisons {
		TERMINAISONS_A1(new String[]{"o", "s", "t", "mus", "tis", "nt"}),
		TERMINAISONS_A2(new String[]{"m", "s", "t", "mus", "tis", "nt"}),
		TERMINAISONS_P1(new String[]{"or", "ris", "tur", "mur", "mini", "ntur"}),
		TERMINAISONS_P2(new String[]{"r", "ris", "tur", "mur", "mini", "ntur"}),
		TERMINAISONS_PFT(new String[]{"i", "isti", "it", "imus", "istis", "erunt"}),
		TERMINAISONS_PART_PST(new String[]{"ns", "ns", "ns"}),
		TERMINAISONS_PART_PFT(new String[]{"us", "a", "um"}),
		TERMINAISONS_GERONDIF(new String[]{null, null, "ndum", "ndi", "ndo", "ndo"}),
		TERMINAISONS_ADJ_VB(new String[]{"ndus", "nda", "ndum"}),
		TERMINAISONS_IMP_PST(new String[]{"", "te"}),
		TERMINAISONS_IMP_FUT(new String[]{"to", "tote"});
		
        String[] terminaisons;

        Terminaisons(String[] term){
            this.terminaisons = term;
        }

        public String[] getTerminaisons(){
            return terminaisons;
        }
	}
	
	public enum Voyelles_thematiques {
		VT_PST_GC3(new String[]{"", "i", "i", "i", "i", "u"}),
		VT_PST_GC4_5(new String[]{"", "", "", "", "", "u"}),
		VT_FUT(new String[]{"", "i", "i", "i", "i", "u"});
		
		String[] vt;
		Voyelles_thematiques(String[] vt) {
			this.vt = vt;
		}
		
		public String[] getVt() {
			return vt;
		}
	}
	
    public enum Groupe {
    	GC1,
    	GC2,
    	GC3,
    	GC4,
    	GC5,
    	IRREGULIER
    }
    
    public enum Flexion_id {
    	IND_PST_A(new int[]{0, 1, 2, 3, 4, 5}),
    	IND_FUT_A(new int[]{6, 7, 8, 9, 10, 11}),
    	IND_IMPFT_A(new int[]{12, 13, 14, 15, 16, 17}),
    	IND_PFT_A(new int[]{18, 19, 20, 21, 22, 23}),
    	IND_PQPFT_A(new int[]{24, 25, 26, 27, 28, 29}),
    	IND_FUT_ANT_A(new int[]{30, 31, 32, 33, 34, 35}),    	
    	SUBJ_PST_A(new int[]{36, 37, 38, 39, 40, 41}),
    	SUBJ_IMPFT_A(new int[]{42, 43, 44, 45, 46, 47}),
    	SUBJ_PFT_A(new int[]{48, 49, 50, 51, 52, 53}),
    	SUB_PQPFT_A(new int[]{54, 55, 56, 57, 58, 59}),
    	
    	
    	;
    	
    	int[] id;
    	
    	Flexion_id(int id[]){
    		this.id = id;
    	}
    	
    	public int[] getId() {
    		return id;
    	}
    }
    
    private Groupe mGroupe;

    public Verbe(ArrayList<String> lemme, String traduction) {
        mLemme = lemme; // (ex : amare, amo, amas, amaui, amatum)
        mTraduction = traduction;
        mType = Mot_type.TYPE_VERBE;
        identifier();
        flechir();
    }
    
    
	@Override
	protected void identifier() {
		if(mLemme.get(0).endsWith("are")) {
			mGroupe = Groupe.GC1;
			mRadical.add(0, mLemme.get(0).substring(0, mLemme.get(0).length() - 2));
		}
		else if(mLemme.get(0).endsWith("ire")) {
			mGroupe = Groupe.GC4;
			mRadical.add(0, mLemme.get(0).substring(0, mLemme.get(0).length() - 2));
		}
		else if(mLemme.get(0).endsWith("ere")) {
			if(mLemme.get(1).endsWith("eo")) {
				mGroupe = Groupe.GC2;
				mRadical.add(0, mLemme.get(0).substring(0, mLemme.get(0).length() - 2));
			}
			else if(mLemme.get(1).endsWith("io")) {
				mGroupe = Groupe.GC5;
				mRadical.add(0, mLemme.get(1).substring(0, mLemme.get(1).length() - 1));
			}
			else {
				mGroupe = Groupe.GC3;
				mRadical.add(0, mLemme.get(1).substring(0, mLemme.get(1).length() - 1));
			}
		}
		else mGroupe = Groupe.IRREGULIER;
		mRadical.add(1, mLemme.get(3).substring(0, mLemme.get(3).length() - 1));
		mRadical.add(2, mLemme.get(4).substring(0, mLemme.get(4).length() - 2));
	}

	@Override
	protected void flechir() {
		// Indicatif Présent Actif (Flexion_Id 0-5)
		for (int i = 0; i < 6;  i++) {
			switch (mGroupe) {
				case GC1:
					if (i==0) mFlexion.add(mRadical.get(0).substring(0, mRadical.get(0).length() - 1) + Terminaisons.TERMINAISONS_A1.getTerminaisons()[i]);
					else mFlexion.add(mRadical.get(0) + Terminaisons.TERMINAISONS_A1.getTerminaisons()[i]);
					break;
				case GC2:
					mFlexion.add(mRadical.get(0) + Terminaisons.TERMINAISONS_A1.getTerminaisons()[i]);
					break;
				case GC3:
					mFlexion.add(mRadical.get(0) + Voyelles_thematiques.VT_PST_GC3.getVt()[i] + Terminaisons.TERMINAISONS_A1.getTerminaisons()[i]);
					break;
				case GC4:
					mFlexion.add(mRadical.get(0) + Voyelles_thematiques.VT_PST_GC3.getVt()[i] + Terminaisons.TERMINAISONS_A1.getTerminaisons()[i]);
					break;
				case GC5:
					mFlexion.add(mRadical.get(0) + Voyelles_thematiques.VT_PST_GC3.getVt()[i] + Terminaisons.TERMINAISONS_A1.getTerminaisons()[i]);
					break;
				case IRREGULIER:
					break;
			}
		}		
		// Indicatif Imparfait Actif (Flexion_Id 6-11)
		for (int i = 0; i < 6;  i++) {
			switch (mGroupe) {
				case GC1:
					mFlexion.add(mRadical.get(0) + "ba"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					break;
				case GC2:
					mFlexion.add(mRadical.get(0) + "ba"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					break;
				case GC3:
					mFlexion.add(mRadical.get(0) + "eba"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					break;
				case GC4:
					mFlexion.add(mRadical.get(0) + "eba"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					break;
				case GC5:
					mFlexion.add(mRadical.get(0) + "eba"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					break;
				case IRREGULIER:
					break;
			}
		}		
		// Indicatif Futur Actif (Flexion_Id 12-17)
		for (int i = 0; i < 6;  i++) {
			switch (mGroupe) {
				case GC1:
					mFlexion.add(mRadical.get(0) + "b" + Voyelles_thematiques.VT_FUT.getVt()[i] + Terminaisons.TERMINAISONS_A1.getTerminaisons()[i]);
					break;
				case GC2:
					mFlexion.add(mRadical.get(0) + "b" + Voyelles_thematiques.VT_FUT.getVt()[i] + Terminaisons.TERMINAISONS_A1.getTerminaisons()[i]);
					break;
				case GC3:
					if (i==0) mFlexion.add(mRadical.get(0) + "a"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					else mFlexion.add(mRadical.get(0) + "e"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					break;
				case GC4:
					if (i==0) mFlexion.add(mRadical.get(0) + "a"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					else mFlexion.add(mRadical.get(0) + "e"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					break;
				case GC5:
					if (i==0) mFlexion.add(mRadical.get(0) + "a"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					else mFlexion.add(mRadical.get(0) + "e"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					break;
				case IRREGULIER:
					break;
			}
		}
		// Indicatif Parfait Actif (Flexion_Id 18-23)
		for (int i = 0; i < 6;  i++) {
			mFlexion.add(mRadical.get(1) + Terminaisons.TERMINAISONS_PFT.getTerminaisons()[i]);
		}
		// Indicatif Plus-que-parfait Actif (Flexion_Id 24-29)
		for (int i = 0; i < 6;  i++) {
			mFlexion.add(mRadical.get(1) + "era" + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
		}
		// Indicatif Futur Antérieur Actif (Flexion_Id 30-35)
		for (int i = 0; i < 6;  i++) {
			if (i==0) mFlexion.add(mRadical.get(1) + "er"  + Terminaisons.TERMINAISONS_A1.getTerminaisons()[i]);
			else mFlexion.add(mRadical.get(1) + "eri"  + Terminaisons.TERMINAISONS_A1.getTerminaisons()[i]);
		}
		// Subjonctif Présent Actif (Flexion_Id 36-41)
		for (int i = 0; i < 6;  i++) {
			switch (mGroupe) {
				case GC1:
					mFlexion.add(mRadical.get(0).substring(0, mRadical.get(0).length()-1) + "e"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					break;
				default:
					mFlexion.add(mRadical.get(0) + "a"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
					break;
			}
		}
		// Subjonctif Imparfait Actif (Flexion_Id 42-47)
		for (int i = 0; i < 6;  i++) {
			mFlexion.add(mLemme.get(0) + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
		}
		// Subjonctif Parfait Actif (Flexion_Id 48-53)
		for (int i = 0; i < 6;  i++) {
			mFlexion.add(mRadical.get(1) + "eri"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
		}
		// Subjonctif Plus-que-parfait Actif (Flexion_Id 54-59)
		for (int i = 0; i < 6;  i++) {
			mFlexion.add(mRadical.get(1) + "isse"  + Terminaisons.TERMINAISONS_A2.getTerminaisons()[i]);
		}
	}

}
