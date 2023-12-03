package com.example.kalkulator.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kalkulator.R;
import com.example.kalkulator.listeners.DigitOnClickListener;
import com.example.kalkulator.listeners.OperationOnClickListener;
import com.example.kalkulator.utils.CalculatorHandler;

import static com.example.kalkulator.utils.CalculatorHandler.*;

public class AdvancedCalculatorActivity extends AppCompatActivity
{

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnComma, btnAllClear, btnPlus, btnMinus, btnMultiply, btnDivide, btnEquals,
            btnPlusMinus, btnClearOrClearAll, btnXToTheYPower, btnXSquared,
            btnSin, btnCos, btnTan, btnLn, btnSqrt, btnLog, btnPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);

        CalculatorHandler.setVibrator((Vibrator) getSystemService(Context.VIBRATOR_SERVICE));
        CalculatorHandler.setValueTextView(findViewById(R.id.value_text_view));
        CalculatorHandler.setOperationTextView(findViewById(R.id.operation_text_view));
        CalculatorHandler.setPrevValueTextView(findViewById(R.id.previous_value_text_view));
        CalculatorHandler.setNewValueFlag(false);

        if (savedInstanceState != null)
        {
            valueTextView.setText(savedInstanceState.getString("valueText"));
            operationTextView.setText(savedInstanceState.getString("opText"));
            prevValueTextView.setText(savedInstanceState.getString("prevValueText"));
            newValueFlag = savedInstanceState.getBoolean("newValueFlag");
        }

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
        btnPlusMinus = findViewById(R.id.btn_plus_minus);
        btnClearOrClearAll = findViewById(R.id.btn_clear_or_clear_all);
        btnXToTheYPower = findViewById(R.id.btn_x_to_the_y_power);
        btnXSquared = findViewById(R.id.btn_x_squared);
        btnSin = findViewById(R.id.btn_sin);
        btnCos = findViewById(R.id.btn_cos);
        btnTan = findViewById(R.id.btn_tan);
        btnLn = findViewById(R.id.btn_ln);
        btnSqrt = findViewById(R.id.btn_sqrt);
        btnLog = findViewById(R.id.btn_log);
        btnPercent = findViewById(R.id.btn_percent);

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
        btnPlusMinus.setOnClickListener(this::plusMinusOnClick);
        btnClearOrClearAll.setOnClickListener(this::clearOrClearAllOnClick);
        btnXToTheYPower.setOnClickListener(new OperationOnClickListener(CHAR_POWER));
        btnXSquared.setOnClickListener(this::xSquaredOnClick);
        btnSin.setOnClickListener(this::sinOnClick);
        btnCos.setOnClickListener(this::cosOnClick);
        btnTan.setOnClickListener(this::tanOnClick);
        btnLn.setOnClickListener(this::lnOnClick);
        btnSqrt.setOnClickListener(this::sqrtOnClick);
        btnLog.setOnClickListener(this::logOnClick);
        btnPercent.setOnClickListener(this::percentOnClick);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("valueText", valueTextView.getText().toString());
        outState.putString("opText", operationTextView.getText().toString());
        outState.putString("prevValueText", prevValueTextView.getText().toString());
        outState.putBoolean("newValueFlag", newValueFlag);
    }

    public void percentOnClick(View v)
    {
        if (!prevValueTextView.getText().toString().isEmpty())
        {
            double prevValue = Double.parseDouble(prevValueTextView.getText().toString().replace(',', '.'));
            double currValue = Double.parseDouble(valueTextView.getText().toString().replace(',', '.'));
            char previousOperation = operationTextView.getText().charAt(0);

            currValue = ((previousOperation == CHAR_PLUS) || (previousOperation == CHAR_MINUS)) ? (currValue / 100.0F * prevValue) : currValue;
            currValue = ((previousOperation == CHAR_MULTIPLY) || (previousOperation == CHAR_DIVIDE) || (previousOperation == CHAR_POWER)) ? (currValue / 100.0F) : currValue;

            double result = OperationOnClickListener.calculate(prevValue, currValue);

            prevValueTextView.setText("");
            operationTextView.setText("");
            valueTextView.setText(DECIMAL_FORMAT.format(result).replace('.', ','));

            newValueFlag = true;
        }

        makeStandardVibration();
    }

    private void logOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        Double currValue = Double.parseDouble(valueText.replace(',', '.'));

        currValue = Math.log10(currValue);

        valueTextView.setText(DECIMAL_FORMAT.format(currValue).replace('.', ','));

        makeStandardVibration();
    }

    private void sqrtOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        Double currValue = Double.parseDouble(valueText.replace(',', '.'));

        currValue = Math.sqrt(currValue);

        valueTextView.setText(DECIMAL_FORMAT.format(currValue).replace('.', ','));

        makeStandardVibration();
    }

    private void lnOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        Double currValue = Double.parseDouble(valueText.replace(',', '.'));

        currValue = Math.log(currValue);

        valueTextView.setText(DECIMAL_FORMAT.format(currValue).replace('.', ','));

        makeStandardVibration();
    }

    private void sinOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        Double currValue = Double.parseDouble(valueText.replace(',', '.'));

        currValue = Math.sin(currValue);

        valueTextView.setText(DECIMAL_FORMAT.format(currValue).replace('.', ','));

        makeStandardVibration();
    }

    private void cosOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        Double currValue = Double.parseDouble(valueText.replace(',', '.'));

        currValue = Math.cos(currValue);

        valueTextView.setText(DECIMAL_FORMAT.format(currValue).replace('.', ','));

        makeStandardVibration();
    }

    private void tanOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        Double currValue = Double.parseDouble(valueText.replace(',', '.'));

        currValue = Math.tan(currValue);

        valueTextView.setText(DECIMAL_FORMAT.format(currValue).replace('.', ','));

        makeStandardVibration();
    }

    private void xSquaredOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        Double currValue = Double.parseDouble(valueText.replace(',', '.'));

        currValue = Math.pow(currValue, 2.0);

        valueTextView.setText(DECIMAL_FORMAT.format(currValue).replace('.', ','));

        makeStandardVibration();
    }

    private void clearOrClearAllOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        if (valueText.equals("0"))
        {
            operationTextView.setText("");
            prevValueTextView.setText("");
        }
        else
        {
            valueTextView.setText("0");
        }

        makeStandardVibration();
    }

    public void plusMinusOnClick(View v)
    {
        String valueText = valueTextView.getText().toString();

        if (valueText.charAt(0) == '-')
        {
            valueText = valueText.substring(1);
        }
        else if (!valueText.equals("0"))
        {
            valueText = "-" + valueText;
        }

        valueTextView.setText(valueText);

        makeStandardVibration();
    }

    public void allClearOnClick(View v)
    {
        valueTextView.setText("0");
        prevValueTextView.setText("");
        operationTextView.setText("");

        makeStandardVibration();
    }

    public void equalsOnClick(View v)
    {
        if (!prevValueTextView.getText().toString().isEmpty())
        {
            double prevValue = Double.parseDouble(prevValueTextView.getText().toString().replace(',', '.'));
            double currValue = Double.parseDouble(valueTextView.getText().toString().replace(',', '.'));

            double result = OperationOnClickListener.calculate(prevValue, currValue);

            prevValueTextView.setText("");
            operationTextView.setText("");
            valueTextView.setText(DECIMAL_FORMAT.format(result).replace('.', ','));

            newValueFlag = true;
        }

        makeStandardVibration();
    }

    public void commaOnClick(View v)
    {
        String text = valueTextView.getText().toString();
        long numberOfDigits = text.chars().filter(Character::isDigit).count();

        if ((!text.contains(",")) && (numberOfDigits > 0) && (numberOfDigits < VALUE_TEXT_VIEW_MAX_SIZE))
        {
            valueTextView.setText(valueTextView.getText() + ",");
        }

        makeStandardVibration();
    }
}