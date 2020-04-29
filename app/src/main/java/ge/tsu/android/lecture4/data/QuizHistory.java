package ge.tsu.android.lecture4.data;

import java.util.Date;

public class QuizHistory {
    private Date date;
    private int point;

    public QuizHistory(Date date, int point) {
        this.date = date;
        this.point = point;
    }

    public QuizHistory() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
