package be.adarbitrium.model.latin_quizz;

import java.util.ArrayList;

import be.adarbitrium.model.latin_toolbox.Mot;

public class Question {
    private final String[] mEnonce;
    private final ArrayList<Integer> mAnswer;
    private final boolean mHasVocative;
    private final Mot.Mot_type mMotType;
    private final int mMotCat;
    private final String mTerminaison;

    public Question(String[] enonce, ArrayList<Integer> answer, boolean hasVocative, int motCat, String terminaison, Mot.Mot_type motType) {
        mEnonce = enonce;
        mAnswer = answer;
        mHasVocative = hasVocative;
        mMotCat = motCat;
        mTerminaison = terminaison;
        mMotType = motType;
    }

    public String[] getEnonce() {
        return mEnonce;
    }

    /**
	 * @return the terminaison
	 */
	public String getTerminaison() {
		return mTerminaison;
	}

	public ArrayList<Integer> getAnswer() {
        return mAnswer;
    }

    public boolean hasVocative(){
        return mHasVocative;
    }
    
    public int getMotCat() {
    	return mMotCat;
    }
    
    @Override
    public String toString(){
    	String qStr = "bouh";
    	for (int i = 0; i < 4; i++)	qStr+=mEnonce[i];
    	return qStr;
    }

	public Mot.Mot_type getMotType() {
		return mMotType;
	}
}
