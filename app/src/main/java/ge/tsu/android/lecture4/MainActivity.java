package ge.tsu.android.lecture4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void addQuestion(View view) {
    Intent intent = new Intent(this, AddQuestionActivity.class);
    startActivity(intent);
  }

  public void takeQuiz(View view) {
    Intent intent = new Intent(this, QuizActivity.class);
    startActivity(intent);
  }

  public void showHistory(View view){
    Intent intent = new Intent(this,HistoryActivity.class);
    startActivity(intent);
  }

}