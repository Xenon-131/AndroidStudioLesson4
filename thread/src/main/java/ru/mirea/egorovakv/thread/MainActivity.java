package ru.mirea.egorovakv.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ru.mirea.egorovakv.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText editTextLesson = binding.editTextNumberLesson;
        EditText editTextDay = binding.editTextNumberDay;
        TextView textV = binding.textView;

        editTextLesson.setText("58");
        editTextDay.setText("6");

        final Runnable runn1 = new Runnable() {
            public void run() {
                int averageValue;
                int lessonCount = Integer.parseInt(editTextLesson.getText().toString());
                int daysCount = Integer.parseInt(editTextDay.getText().toString());
                if(daysCount!=0){
                    averageValue = lessonCount / 4 / daysCount;
                }
                else{
                    averageValue = lessonCount / 4 / 6;
                }
                textV.setText(String.format("Cреднее количество пар в день за период одного\n месяца = %d", averageValue));
            }
        };

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            runOnUiThread(runn1);
                        }
                        finally {
                        }
                    }
                });
                t.start();
            }
        });



    }
}