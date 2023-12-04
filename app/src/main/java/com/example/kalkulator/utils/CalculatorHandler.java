package com.example.kalkulator.utils;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CalculatorHandler
{
    public static final char CHAR_PLUS = '+';
    public static final char CHAR_MINUS = '—';
    public static final char CHAR_MULTIPLY = '⨯';
    public static final char CHAR_DIVIDE = '÷';
    public static final char CHAR_POWER = '^';
    public static final String INFINITY_SYMBOL = "\u221E"; // infinity symbol (dividing by zero)
    public static final String MINUS_NAN = "-NaN";
    public static final String NAN = "NaN";
    public static final int VALUE_TEXT_VIEW_MAX_SIZE = 10;
    public static final int OUTPUT_MAX_SIZE = 20;
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##########");
    public static final int VIBRATION_DURATION_MS = 8;
    public static final int VIBRATION_AMPLITUDE = 100;
    private static Vibrator vibrator;
    private static Context toastMessageContext;

    public static TextView valueTextView, operationTextView, prevValueTextView;
    public static Boolean newValueFlag;

    public static void setValueTextView(TextView valueTextView)
    {
        CalculatorHandler.valueTextView = valueTextView;
    }

    public static void setOperationTextView(TextView operationTextView)
    {
        CalculatorHandler.operationTextView = operationTextView;
    }

    public static void setPrevValueTextView(TextView prevValueTextView)
    {
        CalculatorHandler.prevValueTextView = prevValueTextView;
    }

    public static void setNewValueFlag(Boolean newValueFlag)
    {
        CalculatorHandler.newValueFlag = newValueFlag;
    }

    public static void setVibrator(Vibrator vibrator)
    {
        CalculatorHandler.vibrator = vibrator;
    }

    public static void makeStandardVibration()
    {
        vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_DURATION_MS, VIBRATION_AMPLITUDE));
    }

    public static void setContextForToastMessages(Context context)
    {
        CalculatorHandler.toastMessageContext = context;
    }

    public static boolean isOutputTooLong(String formattedOutput)
    {
        int digitsInOutput = 0;
        boolean outputTooLong = false;

        for (char c : formattedOutput.toCharArray())
        {
            digitsInOutput += Character.isDigit(c) ? 1 : 0;
        }

        System.out.println("digits in output: " + digitsInOutput);

        if (digitsInOutput > OUTPUT_MAX_SIZE)
        {
            Toast.makeText(toastMessageContext, "The output is too long", Toast.LENGTH_SHORT).show();
            outputTooLong = true;
        }

        return outputTooLong;
    }

    public static Context getToastMessageContext()
    {
        return toastMessageContext;
    }
}
