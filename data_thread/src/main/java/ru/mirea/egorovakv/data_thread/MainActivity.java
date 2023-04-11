package ru.mirea.egorovakv.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import ru.mirea.egorovakv.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.tvInfo.setText("runn3");
            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.tvInfo.postDelayed(runn3, 2000);
                    binding.tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        binding.textV.setText("Последовательность запуска процессов: \nrunn1, runn3, runn2. " +
                        "\n\trunOnUiThread: \nесли текущий поток - UiThread, " +
                        "\nто действия в Runnable выполняются мгновенно. " +
                        "\nИначе, действия отправляются в очередь Ui потоку и выполняются в нем. " +
                        "\n\tpost: \nотправляет Runnable на немедленную обработку в поток, \nк которому он прикреплен. " +
                "\n\tpostDelayed: \nпозволяет указать задержку выполнения Runnable.");
    }
}