package com.example.kalkulator.utils;

import android.widget.TextView;

public class CalculatorHandler
{
    public static TextView valueTextView, operationTextView, prevValueTextView;
    public static Boolean newValueFlag;

    public static void setTextViews(TextView valueTextViewArg, TextView operationTextViewArg, TextView prevValueTextViewArg)
    {
        valueTextView = valueTextViewArg;
        operationTextView = operationTextViewArg;
        prevValueTextView = prevValueTextViewArg;
    }

    public static void setNewValueFlag(Boolean newValueFlagArg)
    {
        newValueFlag = newValueFlagArg;
    }
}
