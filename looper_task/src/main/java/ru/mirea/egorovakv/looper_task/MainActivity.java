package ru.mirea.egorovakv.looper_task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import ru.mirea.egorovakv.looper_task.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler	=	new	Handler(Looper.getMainLooper())	{
            @Override
            public	void	handleMessage(Message msg)	{
                Log.d(MainActivity.class.getSimpleName(),	"Task	execute.	This	is	result:	"
                        +	msg.getData().getString("result"));
            }
        };
        MyLooper	myLooper	=	new	MyLooper(mainThreadHandler);
        myLooper.start();

        final Runnable runn1 = new Runnable() {
            public void run() {
                String work = binding.editTextWork.getText().toString();
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("KEY", work);
                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);
            }
        };

        
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public	void	onClick(View	v) {
                new Thread(new Runnable() {
                    public void run() {
                        int age = Integer.parseInt(binding.editTextAge.getText().toString());
                        binding.editTextWork.postDelayed(runn1, age*100);
                    }
                }).start();


            }
        });
    }
}