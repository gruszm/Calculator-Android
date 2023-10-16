package com.example.kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private static final int VALUE_TEXT_VIEW_MAX_SIZE = 10;
    private static final String OPERATION_TEXT_VIEW_PREFIX = "Current operation: ";

    private TextView operationTextView, valueTextView;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnComma, btnAllClear, btnPlus, btnMinus, btnMultiply, btnDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operationTextView = findViewById(R.id.operation_text_view);
        valueTextView = findViewById(R.id.value_text_view);

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
        btnPlus.setOnClickListener(new OperationOnClickListener('+'));
        btnMinus.setOnClickListener(new OperationOnClickListener('—'));
        btnMultiply.setOnClickListener(new OperationOnClickListener('⨯'));
        btnDivide.setOnClickListener(new OperationOnClickListener('÷'));
    }

    public void commaOnClick(View v)
    {
        String text = valueTextView.getText().toString();

        if (!(text.contains(",")) && (text.length() > 0))
        {
            valueTextView.setText(valueTextView.getText() + ",");
        }
    }

    public void allClearOnClick(View v)
    {
        valueTextView.setText("0");
        updateOperationTextView('\0');
    }

    /**
     * Updates the operation text view.
     * @param operation Character of wanted operation; pass an empty string to clear the text view.
     */
    private void updateOperationTextView(char operation)
    {
        if (operation == '\0')
        {
            operationTextView.setText("");
        }
        else
        {
            operationTextView.setText(OPERATION_TEXT_VIEW_PREFIX + String.valueOf(operation));
        }
    }

    private class OperationOnClickListener implements View.OnClickListener
    {
        private char operation;

        public OperationOnClickListener(char operation)
        {
            this.operation = operation;
        }

        @Override
        public void onClick(View v)
        {
            updateOperationTextView(operation);
        }
    }

    private class DigitOnClickListener implements View.OnClickListener
    {
        private int digit;

        public DigitOnClickListener(int digit)
        {
            this.digit = digit;
        }

        @Override
        public void onClick(View v)
        {
            if (valueTextView.length() < VALUE_TEXT_VIEW_MAX_SIZE)
            {
                valueTextView.setText(valueTextView.getText() + String.valueOf(digit));
            }
        }
    }
}