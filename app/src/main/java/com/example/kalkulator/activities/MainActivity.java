package com.example.kalkulator.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kalkulator.R;
import com.example.kalkulator.classes.DigitOnClickListener;
import com.example.kalkulator.classes.OperationOnClickListener;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
{
    public static final char CHAR_PLUS = '+';
    public static final char CHAR_MINUS = '—';
    public static final char CHAR_MULTIPLY = '⨯';
    public static final char CHAR_DIVIDE = '÷';
    public static final int VALUE_TEXT_VIEW_MAX_SIZE = 10;
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##########");
    public static final int VIBRATION_DURATION_MS = 8;
    public static final int VIBRATION_AMPLITUDE = 100;
    public static Vibrator vibrator;

    public static TextView operationTextView, valueTextView, previousValueTextView;
    public static boolean newValueFlag;

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnComma, btnAllClear, btnPlus, btnMinus, btnMultiply, btnDivide, btnEquals;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        operationTextView = findViewById(R.id.operation_text_view);
        valueTextView = findViewById(R.id.value_text_view);
        previousValueTextView = findViewById(R.id.previous_value_text_view);
        newValueFlag = false;

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

        btnComma = findViewById(R.id.btn_comma);
        btnAllClear = findViewById(R.id.btn_all_clear);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnMultiply = findViewById(R.id.btn_multiply);
        btnDivide = findViewById(R.id.btn_divide);
        btnEquals = findViewById(R.id.btn_equals);

        btn0.setOnClickListener(new DigitOnClickListener(0));
        btn1.setOnClickListener(new DigitOnClickListener(1));
        btn2.setOnClickListener(new DigitOnClickListener(2));
        btn3.setOnClickListener(new DigitOnClickListener(3));
        btn4.setOnClickListener(new DigitOnClickListener(4));
        btn5.setOnClickListener(new DigitOnClickListener(5));
        btn6.setOnClickListener(new DigitOnClickListener(6));
        btn7.setOnClickListener(new DigitOnClickListener(7));
        btn8.setOnClickListener(new DigitOnClickListener(8));
        btn9.setOnClickListener(new DigitOnClickListener(9));

        btnComma.setOnClickListener(this::commaOnClick);
        btnAllClear.setOnClickListener(this::allClearOnClick);
        btnPlus.setOnClickListener(new OperationOnClickListener(CHAR_PLUS));
        btnMinus.setOnClickListener(new OperationOnClickListener(CHAR_MINUS));
        btnMultiply.setOnClickListener(new OperationOnClickListener(CHAR_MULTIPLY));
        btnDivide.setOnClickListener(new OperationOnClickListener(CHAR_DIVIDE));
        btnEquals.setOnClickListener(this::equalsOnClick);
    }

    public void allClearOnClick(View v)
    {
        valueTextView.setText("0");
        previousValueTextView.setText("");
        operationTextView.setText("");

        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION_MS, VIBRATION_AMPLITUDE));
    }

    public void equalsOnClick(View v)
    {
        if (!previousValueTextView.getText().toString().isEmpty())
        {
            double prevValue = Double.parseDouble(previousValueTextView.getText().toString().replace(',', '.'));
            double currValue = Double.parseDouble(valueTextView.getText().toString().replace(',', '.'));

            double result = OperationOnClickListener.calculate(prevValue, currValue);

            previousValueTextView.setText("");
            operationTextView.setText("");
            valueTextView.setText(DECIMAL_FORMAT.format(result).replace('.', ','));
            newValueFlag = true;
        }

        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION_MS, VIBRATION_AMPLITUDE));
    }

    public void commaOnClick(View v)
    {
        String text = valueTextView.getText().toString();
        long numberOfDigits = text.chars().filter(Character::isDigit).count();

        if ((!text.contains(",")) && (numberOfDigits > 0) && (numberOfDigits < VALUE_TEXT_VIEW_MAX_SIZE))
        {
            valueTextView.setText(valueTextView.getText() + ",");
        }

        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION_MS, VIBRATION_AMPLITUDE));
    }
}