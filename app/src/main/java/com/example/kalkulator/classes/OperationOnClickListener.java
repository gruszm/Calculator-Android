package com.example.kalkulator.classes;

import android.view.View;

import static com.example.kalkulator.activities.MainActivity.*;

public class OperationOnClickListener implements View.OnClickListener
{
    private final char operation;

    public OperationOnClickListener(char operation)
    {
        this.operation = operation;
    }

    @Override
    public void onClick(View v)
    {
        double result;
        double prevValue;
        double currValue = Double.parseDouble(valueTextView.getText().toString().replace(',', '.'));

        if (operationTextView.getText().toString().isEmpty())
        {
            previousValueTextView.setText(valueTextView.getText());
        }
        else if (previousValueTextView.getText().toString().isEmpty())
        {
            result = calculate(0, currValue);

            previousValueTextView.setText(DECIMAL_FORMAT.format(result).replace('.', ','));
        }
        else
        {
            prevValue = Double.parseDouble(previousValueTextView.getText().toString().replace(',', '.'));

            result = calculate(prevValue, currValue);

            previousValueTextView.setText(DECIMAL_FORMAT.format(result).replace('.', ','));
        }

        operationTextView.setText(String.valueOf(operation));
        newValueFlag = true;

        makeStandardVibration();
    }

    public static double calculate(double prevValue, double currValue) throws NumberFormatException
    {
        double result = 0.0D;
        char previousOperation = operationTextView.getText().charAt(0);

        // Use the previous operation, no the one, which is set on click of an operation button
        switch (previousOperation)
        {
            case CHAR_PLUS:
                result = prevValue + currValue;
                break;
            case CHAR_MINUS:
                result = prevValue - currValue;
                break;
            case CHAR_MULTIPLY:
                result = prevValue * currValue;
                break;
            case CHAR_DIVIDE:
                result = prevValue / currValue;
                break;
        }

        return result;
    }
}