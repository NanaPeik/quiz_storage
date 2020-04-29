package ge.tsu.android.lecture4.data;

import java.util.ArrayList;

public class QuizHistoryStorage  {

    public static String HISTORY_STORAGE_KEY = "History_storage";

    private ArrayList<QuizHistory> dataOfResults = new ArrayList<>();

    public ArrayList<QuizHistory> getDataOfResults() { return dataOfResults; }
}
