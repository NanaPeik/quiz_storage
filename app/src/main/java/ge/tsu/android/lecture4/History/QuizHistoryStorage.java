package ge.tsu.android.lecture4.History;

import java.util.ArrayList;

import ge.tsu.android.lecture4.History.QuizHistory;

public class QuizHistoryStorage  {

    public static String HISTORY_STORAGE_KEY = "History_storage";

    private ArrayList<QuizHistory> dataOfResults = new ArrayList<>();

    public ArrayList<QuizHistory> getDataOfResults() { return dataOfResults; }
}
