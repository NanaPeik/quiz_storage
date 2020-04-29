package ge.tsu.android.lecture4.data;

import java.util.ArrayList;

public class QuizStorage {

  public static String QUIZ_STORAGE_KEY = "quiz_storage";

  private ArrayList<QuizQuestion> questions = new ArrayList<>();

  public ArrayList<QuizQuestion> getQuestions() { return questions; }

//  public boolean deleteQuestion(QuizQuestion quizQuestion){
//    if(questions.contains(quizQuestion)){
//      questions.remove(quizQuestion);
//      return true;
//    }
//    return false;
//  }
//  public void setQuestions(ArrayList<QuizQuestion> questions) { this.questions = questions; }

}