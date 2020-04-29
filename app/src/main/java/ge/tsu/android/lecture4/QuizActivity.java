package ge.tsu.android.lecture4;

import android.content.Context;
import android.content.Intent;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ge.tsu.android.lecture4.data.QuizQuestion;
import ge.tsu.android.lecture4.data.QuizStorage;
import ge.tsu.android.lecture4.data.Storage;
import ge.tsu.android.lecture4.data.StorageImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

  private ListView mQuestions;
  private QuestionArrayAdapter questionArrayAdapter;
  private String[] mAnswers;
  private ArrayList<QuizQuestion> quizQuestions;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz);
    mQuestions = findViewById(R.id.questions);
    questionArrayAdapter = new QuestionArrayAdapter(this, 0, new ArrayList<QuizQuestion>());
    mQuestions.setAdapter(questionArrayAdapter);

    Storage storage = new StorageImpl();
    Object quizStorageAsObject = storage
      .getObject(this, QuizStorage.QUIZ_STORAGE_KEY, QuizStorage.class);

    if (quizStorageAsObject != null) {
      QuizStorage quizStorage = (QuizStorage) quizStorageAsObject;
      quizQuestions = quizStorage.getQuestions();
      questionArrayAdapter.addAll(quizQuestions);
      mAnswers = new String[quizStorage.getQuestions().size()];
    }

    findViewById(R.id.complete_quiz).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        completeQuiz();
      }
    });
  }

  public void completeQuiz() {
    int score = 0;
    for (int i = 0; i < quizQuestions.size(); i++) {
      if (quizQuestions.get(i).getAnswer().equals(mAnswers[i])) {
        score++;
      }
    }
    Toast.makeText(this, String.format("Score is %d", score), Toast.LENGTH_LONG).show();
    Date date=new Date();
    Intent intent=new Intent(this,HistoryActivity.class);
    intent.putExtra("Date_Point",date.getTime()+"$"+score);
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
//    public void  deleteQuestion(View view){
//      QuizQuestion quizQuestionForDelete =new QuizQuestion(Question.toString(),answer1.toString(),
//              answer2.toString(),answer3.toString(),answer4.toString());
//      quizQuestions.remove(this);
//    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      QuizQuestion current = getItem(position);
      LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View view = inflater.inflate(R.layout.view_question_item, parent, false);
//      final TextView textView = view.findViewById(R.id.question);
      Question=view.findViewById(R.id.question);

      Question.setText(current.getQuestion());
//      TextView answer = view.findViewById(R.id.answer);
      answer1=view.findViewById(R.id.answer);
      answer2=view.findViewById(R.id.answer2);
      answer3=view.findViewById(R.id.answer3);
      answer4=view.findViewById(R.id.answer4);
      deleteButton=view.findViewById(R.id.deletequestion);

      deleteButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          QuizQuestion quizQuestionForDelete =new QuizQuestion(Question.toString(),answer1.toString(),
                  answer2.toString(),answer3.toString(),answer4.toString());
          quizQuestions.remove(quizQuestionForDelete);
        }
      });

      answer1.setText(current.getAnswer());
      answer2.setText(current.getAnswer2());
      answer3.setText(current.getAnswer3());
      answer4.setText(current.getAnswer4());

      //კოდის ეს ნაწილი მნიშვნელოვანია, გამომდინარე იქიდან რომ , თუ ListVIew-ს რომელიმე Item არ ჩანს ეკრანზე,
      // ის იშლება, და თავიდან გამოჩენის შემთხვევაში იქმნება თავიდან, ამიტომ უნდა ჩავსვათ ის მნიშვნელობა რაც იყო მანამდე
      if (mAnswers[position] != null) {
        answer1.setText(mAnswers[position]);
      }
      answer1.addTextChangedListener(new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//          Log.i("Text Change", s.toString());
          mAnswers[position] = s.toString();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
      });

      return view;
    }
  }
//
//  private void DeleteQuestionFromStorage() {
//    QuizQuestion quizQuestionDelete=new QuizQuestion(te);
//  }
}