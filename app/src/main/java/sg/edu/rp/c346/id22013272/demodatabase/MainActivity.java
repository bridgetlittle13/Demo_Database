package sg.edu.rp.c346.id22013272.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayAdapter<Task> aaTask;
    ArrayList<Task> alTask;
    EditText edDes;
    EditText edDate;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnInsert=findViewById(R.id.btnInsert);
        btnGetTasks=findViewById(R.id.btnGetTasks);
        tvResults=findViewById(R.id.tvResults);
        lv=findViewById(R.id.lv);
        edDes=findViewById(R.id.EditTextDescription);
        edDate=findViewById(R.id.EditTextDate);
        alTask= new ArrayList<>();
        aaTask= new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,alTask);
        lv.setAdapter(aaTask);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                String description = edDes.getText().toString();
                String date = edDate.getText().toString();
                db.insertTask(description, date);

            }
        });
        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                //Retrieve all tasks from database table
                ArrayList<Task> data1 = db.getTasks();
                alTask.addAll(data1);
                aaTask.notifyDataSetChanged();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);
            }
        });
    }
}