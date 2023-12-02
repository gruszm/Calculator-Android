package com.example.kalkulator.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.kalkulator.R;
import com.example.kalkulator.utils.CalculatorHandler;

import static com.example.kalkulator.utils.CalculatorHandler.makeStandardVibration;

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

        CalculatorHandler.setVibrator((Vibrator) getSystemService(Context.VIBRATOR_SERVICE));
    }

    private void btnAdvancedCalculatorOnClick(View view)
    {
        makeStandardVibration();

        Intent intent = new Intent(HomeActivity.this, AdvancedCalculatorActivity.class);
        startActivity(intent);
    }

    private void btnSimpleCalculatorOnClick(View v)
    {
        makeStandardVibration();

        Intent intent = new Intent(HomeActivity.this, SimpleCalculatorActivity.class);
        startActivity(intent);
    }
}