package ge.tsu.android.lecture4.Question;

import java.util.ArrayList;

import ge.tsu.android.lecture4.Answer.QuizAnswer;

public class QuizQuestion {

  private String question;
  private ArrayList<QuizAnswer> answers;
  private String id;

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getQuestion() { return question; }

  public void setQuestion(String question) { this.question = question; }

  public ArrayList<QuizAnswer> getAnswers() {
    return answers;
  }

  public void setAnswers(ArrayList<QuizAnswer> answers) {
    this.answers = answers;
  }
}