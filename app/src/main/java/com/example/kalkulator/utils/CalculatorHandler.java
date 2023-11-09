package com.example.kalkulator.utils;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalculatorHandler
{
    public static final char CHAR_PLUS = '+';
    public static final char CHAR_MINUS = '—';
    public static final char CHAR_MULTIPLY = '⨯';
    public static final char CHAR_DIVIDE = '÷';
    public static final int VALUE_TEXT_VIEW_MAX_SIZE = 10;
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##########");
    public static final int VIBRATION_DURATION_MS = 8;
    public static final int VIBRATION_AMPLITUDE = 100;
    private static Vibrator vibrator;

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
}
