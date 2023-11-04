package com.example.kalkulator.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.kalkulator.R;

public class HomeActivity extends AppCompatActivity
{
    private Button btnSimpleCalculator, btnAdvancedCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnSimpleCalculator = findViewById(R.id.btn_simple_calculator);
        btnAdvancedCalculator = findViewById(R.id.btn_advanced_calculator);

        btnSimpleCalculator.setOnClickListener(this::btnSimpleCalculatorOnClick);
        btnAdvancedCalculator.setOnClickListener(this::btnAdvancedCalculatorOnClick);
    }

    private void btnAdvancedCalculatorOnClick(View view)
    {
        Intent intent = new Intent(HomeActivity.this, AdvancedCalculatorActivity.class);
        startActivity(intent);
    }

    private void btnSimpleCalculatorOnClick(View v)
    {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}