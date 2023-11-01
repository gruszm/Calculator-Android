package com.example.kalkulator;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.kalkulator.activities.MainActivity;

public class HomeActivity extends AppCompatActivity
{
    private Button btnSimpleCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnSimpleCalculator = findViewById(R.id.btn_simple_calculator);

        btnSimpleCalculator.setOnClickListener(this::btnSimpleCalculatorOnClick);
    }

    private void btnSimpleCalculatorOnClick(View v)
    {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}