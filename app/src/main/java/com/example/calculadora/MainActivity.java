package com.example.calculadora;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private String currentOperator;
    private double operand1;
    private double operand2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9
        };

        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                editText.append(button.getText().toString());
            }
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(numberButtonListener);
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtons = {
                R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv, R.id.btnEquals, R.id.btnClear
        };

        View.OnClickListener operatorButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String operator = button.getText().toString();

                switch (operator) {
                    case "C":
                        editText.setText("");
                        operand1 = operand2 = 0;
                        currentOperator = "";
                        break;
                    case "=":
                        if (!currentOperator.isEmpty() && !editText.getText().toString().isEmpty()) {
                            operand2 = Double.parseDouble(editText.getText().toString());
                            double result = performOperation(operand1, operand2, currentOperator);
                            editText.setText(String.valueOf(result));
                            currentOperator = "";
                        }
                        break;
                    default:
                        if (!editText.getText().toString().isEmpty()) {
                            operand1 = Double.parseDouble(editText.getText().toString());
                            currentOperator = operator;
                            editText.setText("");
                        }
                        break;
                }
            }
        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(operatorButtonListener);
        }
    }

    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    return Double.NaN; // Return NaN if division by zero
                }
            default:
                return 0;
        }
    }
}