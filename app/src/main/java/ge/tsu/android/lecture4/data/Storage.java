package ge.tsu.android.lecture4.data;

import android.content.Context;

public interface Storage {

  void add(Context context, String key, Object value);

  Object getObject(Context context, String key, Class klass);

  String getString(Context context, String key);
}
