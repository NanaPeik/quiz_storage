package ge.tsu.android.lecture4;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.UUID;

import ge.tsu.android.lecture4.Answer.QuizAnswer;
import ge.tsu.android.lecture4.Question.QuizQuestion;
import ge.tsu.android.lecture4.Question.QuizQuestions;
import ge.tsu.android.lecture4.data.Storage;
import ge.tsu.android.lecture4.data.StorageImpl;

public class AddQuestionActivity extends AppCompatActivity {

  private EditText mQuestion;
  private EditText mAnswer1;
  private EditText mAnswer2;
  private EditText mAnswer3;
  private EditText mAnswer4;
  private ArrayList<QuizAnswer> quizAnswers=new ArrayList<>();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_question);
    mQuestion = findViewById(R.id.question);
    mAnswer1 = findViewById(R.id.answer);
    mAnswer2 = findViewById(R.id.answer2);
    mAnswer3 = findViewById(R.id.answer3);
    mAnswer4 = findViewById(R.id.answer4);
  }

  public void addQuestion(View view) {
    String question = mQuestion.getText().toString();//gdggd?

    QuizQuestion quizQuestion = new QuizQuestion();
    quizQuestion.setQuestion(question);

    ArrayList<QuizAnswer> answersList=new ArrayList<>();

    QuizAnswer quizAnswer=new QuizAnswer(mAnswer1.getText().toString(),true);
    answersList.add(quizAnswer);
    QuizAnswer quizAnswer1=new QuizAnswer(mAnswer2.getText().toString(),false);
    answersList.add(quizAnswer1);
    QuizAnswer quizAnswer2=new QuizAnswer(mAnswer3.getText().toString(),false);
    answersList.add(quizAnswer2);
    QuizAnswer quizAnswer3=new QuizAnswer(mAnswer4.getText().toString(),false);
    answersList.add(quizAnswer3);

    quizQuestion.setAnswers(answersList);//ans1
    quizQuestion.setId(UUID.randomUUID().toString());         //id

    Storage storage = new StorageImpl();
    Object storageAsObject = storage
            .getObject(this, QuizQuestions.QUIZ_STORAGE_KEY, QuizQuestions.class);

    QuizQuestions quizQuestions;
    if (storageAsObject != null) {
      quizQuestions = (QuizQuestions) storageAsObject;
    } else {
      quizQuestions = new QuizQuestions();
    }

    quizQuestions.getQuestions().add(quizQuestion);
    storage.add(this, QuizQuestions.QUIZ_STORAGE_KEY, quizQuestions);

    finish();
  }
}
