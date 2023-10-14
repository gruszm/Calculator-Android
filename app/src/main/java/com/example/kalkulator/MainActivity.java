package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private TextView textView;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btn0.setOnClickListener(new MyOnClickListener(0));
        btn1.setOnClickListener(new MyOnClickListener(1));
        btn2.setOnClickListener(new MyOnClickListener(2));
        btn3.setOnClickListener(new MyOnClickListener(3));
        btn4.setOnClickListener(new MyOnClickListener(4));
        btn5.setOnClickListener(new MyOnClickListener(5));
        btn6.setOnClickListener(new MyOnClickListener(6));
        btn7.setOnClickListener(new MyOnClickListener(7));
        btn8.setOnClickListener(new MyOnClickListener(8));
        btn9.setOnClickListener(new MyOnClickListener(9));
    }

    private class MyOnClickListener implements View.OnClickListener
    {
        private int digit;

        public MyOnClickListener(int digit)
        {
            this.digit = digit;
        }

        @Override
        public void onClick(View v)
        {
            textView.append(String.valueOf(digit));
        }
    }
}