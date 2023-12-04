package com.example.kalkulator.listeners;

import android.view.View;
import android.widget.Toast;
import com.example.kalkulator.utils.CalculatorHandler;

import static com.example.kalkulator.utils.CalculatorHandler.*;

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
        String formattedOutput;
        double result;
        double prevValue;
        double currValue = Double.parseDouble(valueTextView.getText().toString().replace(',', '.'));
        boolean outputTooLong;
        boolean operationChange = true;

        if (operationTextView.getText().toString().isEmpty())
        {
            prevValueTextView.setText(valueTextView.getText());
        }
        else
        {
            prevValue = Double.parseDouble(prevValueTextView.getText().toString().replace(',', '.'));

            result = calculate(prevValue, currValue);

            formattedOutput = String.format("%.5f", result).replace('.', ',');
            outputTooLong = CalculatorHandler.isOutputTooLong(formattedOutput);

            if (formattedOutput.equals(INFINITY_SYMBOL) || formattedOutput.equals(MINUS_NAN) || formattedOutput.equals(NAN))
            {
                Toast.makeText(getToastMessageContext(), "Cannot divide by 0", Toast.LENGTH_SHORT).show();
                operationChange = false;
            }
            else if (!outputTooLong)
            {
                prevValueTextView.setText(formattedOutput);
            }
        }

        if (operationChange)
        {
            operationTextView.setText(String.valueOf(operation));
            newValueFlag = true;
        }

        makeStandardVibration();
    }

    public static double calculate(double prevValue, double currValue) throws NumberFormatException
    {
        double result = 0.0D;
        char previousOperation = operationTextView.getText().charAt(0);

        // Use the previous operation, not the one, which is set on click of an operation button
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
            case CHAR_POWER:
                result = Math.pow(prevValue, currValue);
                break;
        }

        return result;
    }
}