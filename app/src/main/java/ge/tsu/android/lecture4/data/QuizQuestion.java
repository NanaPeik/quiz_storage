package ge.tsu.android.lecture4.data;

public class QuizQuestion {

  private String question;
  private String answer;
  private String answer2;
  private String answer3;
  private String answer4;

  public QuizQuestion(String question, String answer, String answer2, String answer3, String answer4) {
    this.question = question;
    this.answer = answer;
    this.answer2 = answer2;
    this.answer3 = answer3;
    this.answer4 = answer4;
  }

  public QuizQuestion() {
  }

  public String getQuestion() { return question; }

  public void setQuestion(String question) { this.question = question; }

  public String getAnswer() { return answer; }

  public void setAnswer(String answer) { this.answer = answer; }

  public String getAnswer2() { return answer2; }

  public void setAnswer2(String answer2) { this.answer2 = answer2; }

  public String getAnswer3() { return answer3; }

  public void setAnswer3(String answer3) { this.answer3 = answer3; }

  public String getAnswer4() { return answer4; }

  public void setAnswer4(String answer4) { this.answer4 = answer4; }
}
