package ru.mirea.egorovakv.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ru.mirea.egorovakv.musicplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textV = binding.textView;
        textV.setText("Playing now: Eminem - Without Me");

        Toast toast = new Toast(this);
        binding.buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.setText("You pressed PLAY button.");
                toast.show();
            }
        });

        binding.buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.setText("You pressed PAUSE button.");
                toast.show();
            }
        });

        binding.buttonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.setText("You pressed PREVIOUS button.");
                toast.show();
            }
        });

        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.setText("You pressed NEXT button.");
                toast.show();
            }
        });
    }
}