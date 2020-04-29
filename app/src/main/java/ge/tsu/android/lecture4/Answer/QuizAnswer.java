package ge.tsu.android.lecture4.Answer;

import ge.tsu.android.lecture4.Question.QuizQuestion;

public class QuizAnswer {
private String answer;
private boolean correct;

    public QuizAnswer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public QuizAnswer() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
