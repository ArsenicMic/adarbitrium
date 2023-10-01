package be.adarbitrium.model.latin_quizz;

import java.util.ArrayList;
import java.util.Random;

import be.adarbitrium.model.latin_toolbox.Adjectif;
import be.adarbitrium.model.latin_toolbox.Mot;
import be.adarbitrium.model.latin_toolbox.Nom;
import be.adarbitrium.model.latin_toolbox.Pronom;

public class QuestionGenerator {

    public enum mQuestionType{
        TYPE_QUESTION_DECLINAISON,
        TYPE_QUESTION_CAS_NOMBRE,
        TYPE_QUESTION_CAS_GENRE_NOMBRE,
        TYPE_QUESTION_CONJUGAISON,
    }
    private final mQuestionType mType;
    private final ArrayList<? extends Mot> mVocabulaire;
    private final ArrayList<String> mFormesDemandees;

    public QuestionGenerator(mQuestionType type, ArrayList<? extends Mot> vocabulaire) {
        mType = type;
        mVocabulaire = vocabulaire;
        mFormesDemandees = new ArrayList<>();
    }

    public Question generate(){
        Question question = null;
        String forme = null;
        String[] enonce;
        ArrayList<Integer> answer = new ArrayList<>();
        String terminaison;
        switch (mType){
            case TYPE_QUESTION_DECLINAISON:
                break;
            case TYPE_QUESTION_CAS_NOMBRE:
                Nom nom;
                String[] declinaisons;
                int counter = 0;
                do{
                	counter+=1;
                	if (counter > 50) {
                		mFormesDemandees.clear();
                		counter = 1;
                	}
                    nom = (Nom)mVocabulaire.get(new Random().nextInt(mVocabulaire.size()));
                    declinaisons = nom.getDeclinaisons();
                    int rnd = new Random().nextInt(12);
                    forme = declinaisons[rnd];
                    terminaison = nom.getTerminaisons()[rnd];
                } while (mFormesDemandees.contains(forme));
                mFormesDemandees.add(forme);
                for (int i = 0; i < 12; i++){
                    if (forme.equals(declinaisons[i])) answer.add(1);
                    else answer.add(0);
                }
                enonce = new String[]{"Quelle sont toutes les possibilités d'analyse pour la forme :",
                        forme,
                        "du nom :",
                        nom.getLemme().get(0) + ", " + nom.getLemme().get(1) + ", " + nom.getLemme().get(2)};
                int motCat = nom.getCategorie();
                question = new Question(enonce, answer, true, motCat, terminaison, Mot.Mot_type.TYPE_NOM);
                break;
            case TYPE_QUESTION_CAS_GENRE_NOMBRE:
                String[][] declinaisonsCGN = new String[3][12];
                Mot mot;
                Pronom pronom = null;
                Adjectif adjectif = null;
                do{
                    mot = mVocabulaire.get(new Random().nextInt(mVocabulaire.size()));
                    if (mot.getType() == Mot.Mot_type.TYPE_PRONOM){
                        pronom = (Pronom) mot;
                        declinaisonsCGN = pronom.getDeclinaisons();
                    }
                    else if (mot.getType() == Mot.Mot_type.TYPE_ADJECTIF){
                        adjectif = (Adjectif) mot;
                        declinaisonsCGN = adjectif.getDeclinaisons();
                    }
                    forme = declinaisonsCGN[new Random().nextInt(3)][new Random().nextInt(12)];
                } while (forme == null || mFormesDemandees.contains(forme));
                mFormesDemandees.add(forme);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 12; j++) {
                        if (forme.equals(declinaisonsCGN[i][j])) answer.add(1);
                        else answer.add(0);
                    }
                }
                System.out.println(answer.toString());
                if (mot.getType() == Mot.Mot_type.TYPE_PRONOM) {
                    enonce = new String[]{"Quelle sont toutes les possibilités d'analyse pour la forme :",
                            forme,
                            "du pronom :",
                            pronom.getLemme().get(0) + ", " + pronom.getLemme().get(1) + ", " + pronom.getLemme().get(2)};
                    question = new Question(enonce, answer, false, pronom.getCategorie(), null, mot.getType());
                }
                else if (mot.getType() == Mot.Mot_type.TYPE_ADJECTIF) {
                    enonce = new String[]{"Quelle sont toutes les possibilités d'analyse pour la forme :",
                            forme,
                            "de l'adjectif :",
                            adjectif.getLemme().get(0) + ", " + adjectif.getLemme().get(1) + ", " + adjectif.getLemme().get(2)};
                    question = new Question(enonce, answer, true, adjectif.getCategorie(), null, mot.getType());
                }               break;
            case TYPE_QUESTION_CONJUGAISON:
                break;
        }
        return question;
    }

}
