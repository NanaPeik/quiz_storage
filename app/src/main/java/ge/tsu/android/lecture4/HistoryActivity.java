package ge.tsu.android.lecture4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ge.tsu.android.lecture4.data.QuizHistory;

public class HistoryActivity extends AppCompatActivity {

    private ListView mResults;
    private HistoryArrayAdapter historyArrayAdapter;
protected String date;
protected String point;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mResults=findViewById(R.id.history);
        historyArrayAdapter=new HistoryArrayAdapter(this,0,new ArrayList<QuizHistory>());
        mResults.setAdapter(historyArrayAdapter);
        Intent intent=getIntent();
        if(intent.hasExtra("Date_Point")){
            String[] date_point=intent.getStringExtra("Date_Point").split("$");
            date=date_point[0];
            point=date_point[1];
        }
    }

    class HistoryArrayAdapter extends ArrayAdapter<QuizHistory> {

        private Context mContext;
        private TextView mDate;
        private TextView mPoint;

        public HistoryArrayAdapter(@NonNull Context context, int resource, @NonNull List<QuizHistory> objects) {
            super(context, resource, objects);
            mContext=context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            QuizHistory current = getItem(position);
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_history_item, parent, false);
            mDate=view.findViewById(R.id.dateId);
            mDate.setText(date);
            mPoint=view.findViewById(R.id.pointId);
            mPoint.setText(point);

            return view;
        }
    }
}
