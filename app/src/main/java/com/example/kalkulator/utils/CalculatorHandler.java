package com.example.kalkulator.utils;

import android.widget.TextView;

public class CalculatorHandler
{
    public static TextView valueTextView, operationTextView, prevValueTextView;
    public static Boolean newValueFlag;

    public static void setValueTextView(TextView valueTextViewArg)
    {
        valueTextView = valueTextViewArg;
    }

    public static void setOperationTextView(TextView operationTextViewArg)
    {
        operationTextView = operationTextViewArg;
    }

    public static void setPrevValueTextView(TextView prevValueTextViewArg)
    {
        prevValueTextView = prevValueTextViewArg;
    }

    public static void setNewValueFlag(Boolean newValueFlagArg)
    {
        newValueFlag = newValueFlagArg;
    }
}
