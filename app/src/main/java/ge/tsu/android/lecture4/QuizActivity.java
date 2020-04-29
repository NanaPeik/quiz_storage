package ge.tsu.android.lecture4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ge.tsu.android.lecture4.Answer.QuizAnswer;
import ge.tsu.android.lecture4.History.QuizHistory;
import ge.tsu.android.lecture4.History.QuizHistoryStorage;
import ge.tsu.android.lecture4.Question.QuizQuestion;
import ge.tsu.android.lecture4.Question.QuizQuestions;
import ge.tsu.android.lecture4.data.Storage;
import ge.tsu.android.lecture4.data.StorageImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

  private ListView mQuestions;
  private QuestionArrayAdapter questionArrayAdapter;
  private ArrayList<QuizQuestion> quizQuestions;
  int score=0;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz);
    mQuestions = findViewById(R.id.questions);
    questionArrayAdapter = new QuestionArrayAdapter(this, 0, new ArrayList<QuizQuestion>());
    mQuestions.setAdapter(questionArrayAdapter);

    Storage storage = new StorageImpl();
    Object quizStorageAsObject = storage
      .getObject(this, QuizQuestions.QUIZ_STORAGE_KEY, QuizQuestions.class);

    if (quizStorageAsObject != null) {
      QuizQuestions quizQuestions = (QuizQuestions) quizStorageAsObject;
      this.quizQuestions = quizQuestions.getQuestions();
      questionArrayAdapter.addAll(this.quizQuestions);
    }




    findViewById(R.id.complete_quiz).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
      Toast.makeText(QuizActivity.this,String.format("your Point : %d",score),Toast.LENGTH_LONG).show();

      String time = String.valueOf(Calendar.getInstance().getTime());
      Intent intent=new Intent(QuizActivity.this,HistoryActivity.class);
//      String string=time+"$"+String.valueOf(score);
//      intent.putExtra("Date_Point",string);
//      startActivity(intent);
        Storage storage1=new StorageImpl();
        Object object=storage1.getObject(QuizActivity.this, QuizHistoryStorage.HISTORY_STORAGE_KEY,QuizHistoryStorage.class);

        QuizHistoryStorage quizHistory;
        if(object!=null){
          quizHistory=(QuizHistoryStorage) object;
        }
        else {
          quizHistory=new QuizHistoryStorage();
        }
        QuizHistory quizHistory1=new QuizHistory();
        quizHistory1.setPoint(String.valueOf(score));
        quizHistory1.setDate(time);
        quizHistory.getDataOfResults().add(quizHistory1);
        storage1.add(QuizActivity.this,QuizHistoryStorage.HISTORY_STORAGE_KEY,quizHistory);

      score=0;
      finish();
      }
    });
  }

  public void sumOfPoints(View view){
    TextView textView=(TextView)view;
    for (int i = 0; i < quizQuestions.size(); i++) {
          if (quizQuestions.get(i).getAnswers().get(0).getAnswer().equals(textView.getText().toString())) {
            score++;
          }
        }
  }
  class QuestionArrayAdapter extends ArrayAdapter<QuizQuestion> {

    private Context mContext;
    private TextView Question;
    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView answer4;
    private TextView deleteButton;

    public QuestionArrayAdapter(@NonNull Context context,
      int resource,
      @NonNull List<QuizQuestion> objects) {
      super(context, resource, objects);
      mContext = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      final QuizQuestion current = getItem(position);
      LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View view = inflater.inflate(R.layout.view_question_item, parent, false);
      Question=view.findViewById(R.id.question);

      Question.setText(current.getQuestion());
      answer1=view.findViewById(R.id.answer);
      answer2=view.findViewById(R.id.answer2);
      answer3=view.findViewById(R.id.answer3);
      answer4=view.findViewById(R.id.answer4);


        ArrayList<QuizAnswer> answerOfCurentQuestion =  current.getAnswers();
          answer1.setText(answerOfCurentQuestion.get(0).getAnswer());
          answer2.setText(answerOfCurentQuestion.get(1).getAnswer());
          answer3.setText(answerOfCurentQuestion.get(2).getAnswer());
          answer4.setText(answerOfCurentQuestion.get(3).getAnswer());



      view.findViewById(R.id.deletequestion).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          Storage storage=new StorageImpl();
          storage.deleteQuestion(mContext,current.getId());
          finish();
        }
      });

      return view;
    }
  }
}