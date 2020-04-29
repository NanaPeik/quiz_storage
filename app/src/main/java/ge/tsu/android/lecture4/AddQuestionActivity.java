package ge.tsu.android.lecture4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ge.tsu.android.lecture4.data.QuizQuestion;
import ge.tsu.android.lecture4.data.QuizStorage;
import ge.tsu.android.lecture4.data.Storage;
import ge.tsu.android.lecture4.data.StorageImpl;

public class AddQuestionActivity extends AppCompatActivity {

  private EditText mQuestion;
  private EditText mAnswer;
  private EditText mAnswer2;
  private EditText mAnswer3;
  private EditText mAnswer4;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_question);
    mQuestion = findViewById(R.id.question);
    mAnswer = findViewById(R.id.answer);
    mAnswer2 = findViewById(R.id.answer2);
    mAnswer3 = findViewById(R.id.answer3);
    mAnswer4 = findViewById(R.id.answer4);
  }

  public void addQuestion(View view) {
    String question = mQuestion.getText().toString();

    QuizQuestion quizQuestion = new QuizQuestion();
    quizQuestion.setQuestion(question);
    quizQuestion.setAnswer(mAnswer.getText().toString());
    quizQuestion.setAnswer2(mAnswer2.getText().toString());
    quizQuestion.setAnswer3(mAnswer3.getText().toString());
    quizQuestion.setAnswer4(mAnswer4.getText().toString());

    Storage storage = new StorageImpl();
    Object storageAsObject = storage
            .getObject(this, QuizStorage.QUIZ_STORAGE_KEY, QuizStorage.class);

    QuizStorage quizStorage;
    if (storageAsObject != null) {
      quizStorage = (QuizStorage) storageAsObject;
    } else {
      quizStorage = new QuizStorage();
    }

    quizStorage.getQuestions().add(quizQuestion);
    storage.add(this, QuizStorage.QUIZ_STORAGE_KEY, quizStorage);

//    Intent intent = new Intent(this, QuizActivity.class);
//    intent.putExtra("answers",mAnswer+"$"+mAnswer2+"$"+mAnswer3+"$"+mAnswer4);
//    startActivity(intent);
    finish();
  }
}
