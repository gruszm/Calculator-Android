package com.example.kalkulator.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.kalkulator.R;
import com.example.kalkulator.listeners.OperationOnClickListener;

import static com.example.kalkulator.utils.CalculatorHandler.*;

public class AdvancedCalculatorActivity extends SimpleCalculatorActivity
{
    private Button btnXToTheYPower, btnXSquared, btnSin, btnCos, btnTan,
                   btnLn, btnSqrt, btnLog, btnPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        savedInstanceState = (savedInstanceState == null) ? (new Bundle()) : savedInstanceState;
        savedInstanceState.putBoolean("advCalcCreation", true);
        super.onCreate(savedInstanceState);

        btnXToTheYPower = findViewById(R.id.btn_x_to_the_y_power);
        btnXSquared = findViewById(R.id.btn_x_squared);
        btnSin = findViewById(R.id.btn_sin);
        btnCos = findViewById(R.id.btn_cos);
        btnTan = findViewById(R.id.btn_tan);
        btnLn = findViewById(R.id.btn_ln);
        btnSqrt = findViewById(R.id.btn_sqrt);
        btnLog = findViewById(R.id.btn_log);
        btnPercent = findViewById(R.id.btn_percent);

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

            String formattedOutput = DECIMAL_FORMAT.format(result).replace('.', ',');

            if (formattedOutput.equals(INFINITY_SYMBOL) || formattedOutput.equals(MINUS_NAN) || formattedOutput.equals(NAN))
            {
                if (operationTextView.getText().charAt(0) == CHAR_POWER)
                {
                    Toast.makeText(this, "The output is too long", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Cannot divide by 0", Toast.LENGTH_SHORT).show();
                }
            }
            else if (!isOutputTooLong(formattedOutput))
            {
                prevValueTextView.setText("");
                operationTextView.setText("");
                valueTextView.setText(formattedOutput);

                newValueFlag = true;
            }
        }

        makeStandardVibration();
    }

    private void logOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        Double currValue = Double.parseDouble(valueText.replace(',', '.'));

        if (currValue > 0.0D)
        {
            currValue = Math.log10(currValue);

            valueTextView.setText(DECIMAL_FORMAT.format(currValue).replace('.', ','));
        }
        else
        {
            Toast.makeText(this, "The number must not be negative", Toast.LENGTH_SHORT).show();
        }

        makeStandardVibration();
    }

    private void sqrtOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        Double currValue = Double.parseDouble(valueText.replace(',', '.'));

        if (currValue >= 0.0D)
        {
            currValue = Math.sqrt(currValue);

            valueTextView.setText(DECIMAL_FORMAT.format(currValue).replace('.', ','));
        }
        else
        {
            Toast.makeText(this, "The number must not be negative", Toast.LENGTH_SHORT).show();
        }

        makeStandardVibration();
    }

    private void lnOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        Double currValue = Double.parseDouble(valueText.replace(',', '.'));

        if (currValue > 0.0D)
        {
            currValue = Math.log(currValue);

            valueTextView.setText(DECIMAL_FORMAT.format(currValue).replace('.', ','));
        }
        else
        {
            Toast.makeText(this, "The number must not be negative", Toast.LENGTH_SHORT).show();
        }

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
        double delta = 1E-3;

        // find k
        int k = (int) Math.round((currValue - Math.PI / 2) / Math.PI);

        // calculate the difference
        double diff = Math.abs(currValue - (Math.PI / 2 + k * Math.PI));

        // check if the input is very close to PI/2 + kPI
        if (diff < delta)
        {
            Toast.makeText(this, "The input is very close or equal to PI/2 + kPI, so the result does not exist", Toast.LENGTH_LONG).show();
        }
        else
        {
            currValue = Math.tan(currValue);

            valueTextView.setText(DECIMAL_FORMAT.format(currValue).replace('.', ','));
        }

        makeStandardVibration();
    }

    private void xSquaredOnClick(View view)
    {
        String valueText = valueTextView.getText().toString();

        Double currValue = Double.parseDouble(valueText.replace(',', '.'));

        currValue = Math.pow(currValue, 2.0);

        String formattedOutput = DECIMAL_FORMAT.format(currValue).replace('.', ',');

        if (!isOutputTooLong(formattedOutput))
        {
            valueTextView.setText(formattedOutput);
        }

        makeStandardVibration();
    }
}