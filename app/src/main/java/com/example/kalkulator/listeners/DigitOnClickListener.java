package com.example.kalkulator.listeners;

import android.view.View;

import static com.example.kalkulator.utils.CalculatorHandler.*;

public class DigitOnClickListener implements View.OnClickListener
{
    private int digit;

    public DigitOnClickListener(int digit)
    {
        this.digit = digit;
    }

    @Override
    public void onClick(View v)
    {
        long numberOfDigits = valueTextView.getText().chars().filter(Character::isDigit).count();

        if (valueTextView.getText().equals("0") || newValueFlag)
        {
            valueTextView.setText(String.valueOf(digit));
            newValueFlag = false;
        }
        else if (numberOfDigits < VALUE_TEXT_VIEW_MAX_SIZE)
        {
            valueTextView.setText(valueTextView.getText() + String.valueOf(digit));
        }

        makeStandardVibration();
    }
}