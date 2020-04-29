package ge.tsu.android.lecture4.Question;

import java.util.ArrayList;

import ge.tsu.android.lecture4.Question.QuizQuestion;

public class QuizQuestions {

  public static String QUIZ_STORAGE_KEY = "quiz_storage";

  private ArrayList<QuizQuestion> questions = new ArrayList<>();

  public ArrayList<QuizQuestion> getQuestions() { return questions; }

  public void setQuestions(ArrayList<QuizQuestion> questions) {
    this.questions = questions;
  }
}