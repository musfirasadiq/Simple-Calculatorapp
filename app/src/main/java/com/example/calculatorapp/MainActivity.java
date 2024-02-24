package com.example.calculatorapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import android.graphics.PorterDuff;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private TextView textViewInputNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInputNumbers = findViewById(R.id.textView_input_numbers);

        setOnClickListeners();
        setOnTouchListener();
    }

    private void setOnClickListeners() {
        findViewById(R.id.button_zero).setOnClickListener(this);
        findViewById(R.id.button_one).setOnClickListener(this);
        findViewById(R.id.button_two).setOnClickListener(this);
        findViewById(R.id.button_three).setOnClickListener(this);
        findViewById(R.id.button_four).setOnClickListener(this);
        findViewById(R.id.button_five).setOnClickListener(this);
        findViewById(R.id.button_six).setOnClickListener(this);
        findViewById(R.id.button_seven).setOnClickListener(this);
        findViewById(R.id.button_eight).setOnClickListener(this);
        findViewById(R.id.button_nine).setOnClickListener(this);

        findViewById(R.id.button_clear).setOnClickListener(this);
        findViewById(R.id.button_parentheses).setOnClickListener(this);
        findViewById(R.id.button_percent).setOnClickListener(this);
        findViewById(R.id.button_division).setOnClickListener(this);
        findViewById(R.id.button_multiplication).setOnClickListener(this);
        findViewById(R.id.button_subtraction).setOnClickListener(this);
        findViewById(R.id.button_addition).setOnClickListener(this);
        findViewById(R.id.button_equal).setOnClickListener(this);
        findViewById(R.id.button_dot).setOnClickListener(this);
    }

    private void setOnTouchListener() {
        findViewById(R.id.button_zero).setOnTouchListener(this);
        findViewById(R.id.button_one).setOnTouchListener(this);
        findViewById(R.id.button_two).setOnTouchListener(this);
        findViewById(R.id.button_three).setOnTouchListener(this);
        findViewById(R.id.button_four).setOnTouchListener(this);
        findViewById(R.id.button_five).setOnTouchListener(this);
        findViewById(R.id.button_six).setOnTouchListener(this);
        findViewById(R.id.button_seven).setOnTouchListener(this);
        findViewById(R.id.button_eight).setOnTouchListener(this);
        findViewById(R.id.button_nine).setOnTouchListener(this);

        findViewById(R.id.button_clear).setOnTouchListener(this);
        findViewById(R.id.button_parentheses).setOnTouchListener(this);
        findViewById(R.id.button_percent).setOnTouchListener(this);
        findViewById(R.id.button_division).setOnTouchListener(this);
        findViewById(R.id.button_multiplication).setOnTouchListener(this);
        findViewById(R.id.button_subtraction).setOnTouchListener(this);
        findViewById(R.id.button_addition).setOnTouchListener(this);
        findViewById(R.id.button_dot).setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Button button = (Button) view;

        if (viewId == R.id.button_zero ||
                viewId == R.id.button_one ||
                viewId == R.id.button_two ||
                viewId == R.id.button_three ||
                viewId == R.id.button_four ||
                viewId == R.id.button_five ||
                viewId == R.id.button_six ||
                viewId == R.id.button_seven ||
                viewId == R.id.button_eight ||
                viewId == R.id.button_nine ||
                viewId == R.id.button_addition ||
                viewId == R.id.button_subtraction ||
                viewId == R.id.button_multiplication ||
                viewId == R.id.button_division ||
                viewId == R.id.button_percent ||
                viewId == R.id.button_dot ||
                viewId == R.id.button_parentheses) {

            appendToInputNumbers(button.getText().toString());
        } else if (viewId == R.id.button_clear) {
            clearInputNumbers();
        } else if (viewId == R.id.button_equal) {
            calculateExpression();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                view.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                view.getBackground().clearColorFilter();
                view.invalidate();
                break;
        }
        return false;
    }

    private void appendToInputNumbers(String str) {
        textViewInputNumbers.append(str);
    }

    private void clearInputNumbers() {
        textViewInputNumbers.setText("");
    }

    private void calculateExpression() {
        try {
            String input = textViewInputNumbers.getText().toString();

            // Replace 'x' with '*' for multiplication and '÷' with '/' for division
            input = input.replaceAll("x", "*").replaceAll("÷", "/");

            // Build the expression
            Expression expression = new ExpressionBuilder(input).build();

            // Evaluate the expression
            double result = expression.evaluate();

            // Display the result
            textViewInputNumbers.setText(Double.toString(result));
        } catch (Exception e) {
            // Handle invalid expression
            Toast.makeText(getApplicationContext(), "Invalid Expression", Toast.LENGTH_SHORT).show();
        }
    }
}
