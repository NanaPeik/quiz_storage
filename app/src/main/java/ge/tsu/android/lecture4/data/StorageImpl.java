package ge.tsu.android.lecture4.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

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
}
