package ge.tsu.android.lecture4.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;

import ge.tsu.android.lecture4.Question.QuizQuestion;
import ge.tsu.android.lecture4.Question.QuizQuestions;

public class StorageImpl implements Storage {

  @Override
  public void add(Context context, String key, Object value) {
    SharedPreferences sharedPreferences = getInstance(context);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(key, new Gson().toJson(value));
    editor.commit();
  }

  @Override
  public Object getObject(Context context, String key, Class klass) {
    SharedPreferences sharedPreferences = getInstance(context);
    String data = sharedPreferences.getString(key, null);
    return data == null ? null : new Gson().fromJson(data, klass);
  }

  @Override
  public String getString(Context context, String key) {
    SharedPreferences sharedPreferences = getInstance(context);
    return sharedPreferences.getString(key, null);
  }

  private SharedPreferences getInstance(Context context) {
    return context.getSharedPreferences("this_is_file_name", Context.MODE_PRIVATE);
  }
 @Override
  public void deleteQuestion(Context context, String keyofQuestion){
    Object object=getObject(context,"quiz_storage", QuizQuestions.class);
    QuizQuestions quizQuestions=(QuizQuestions)object;
    ArrayList<QuizQuestion> questionArrayList=quizQuestions.getQuestions();
    Iterator<QuizQuestion> questionIterator=questionArrayList.iterator();
    while (questionIterator.hasNext()){
      QuizQuestion quizQuestion=questionIterator.next();
      if(quizQuestion.getId()!=null&&quizQuestion.getId().equals(keyofQuestion)){
        questionIterator.remove();
        break;
      }
    }
    quizQuestions.setQuestions(questionArrayList);
    add(context,"quiz_storage",quizQuestions);
  }
}
