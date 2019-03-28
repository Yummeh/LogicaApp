package com.example.logicaapp;

import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.InputQueue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView Logic_gate_name;
    private TextView Logic_gate;

    private EditText Input1;
    private EditText Input2;
    private EditText Input3;
    private EditText Input4;

    private Button Submit;

    private int questions = 2;
    private int currentQuestion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logic_gate_name = findViewById(R.id.Type);
        Logic_gate = findViewById(R.id.AB);

        Input1 = findViewById(R.id.Input1);
        Input2 = findViewById(R.id.Input2);
        Input3 = findViewById(R.id.Input3);
        Input4 = findViewById(R.id.Input4);

        Submit = findViewById(R.id.button);

        setQuestion(1);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!correctInput(Input1) || !correctInput(Input2) || !correctInput(Input3) || !correctInput(Input4)) {
                    Toast.makeText(MainActivity.this, "Wrong input! use T or F.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (checkAnswer()) {
                    correct();
                } else {
                    wrong();
                }
            }
        });
    }

    public void setQuestion(int i) {
        currentQuestion = i;
        switch (i) {
            case 1:
                setAND();
                break;
            case 2:
                setOR();
                break;
            default:
                Toast.makeText(this, "Unknown logic gate!", Toast.LENGTH_SHORT).show();
        }
    }

    public void nextQuestion() {
        currentQuestion++;
        if (currentQuestion > questions) {
            currentQuestion = 1;
        }
    }

    private boolean checkAnswer() {
        switch (currentQuestion) {
            case 1:
                return checkAND();
            case 2:
                return checkOR();
            default:
                Toast.makeText(this, "Unknown logic gate!", Toast.LENGTH_SHORT).show();
                return false;
        }
    }

    private void setAND() {
        Logic_gate_name.setText(R.string.Conjunction);
        Logic_gate.setText(getString(R.string.AND));
    }

    private boolean checkAND() {
        return checkGate(true, false, false, false);
    }

    private void setOR() {
        Logic_gate_name.setText(R.string.Disjunction);
        Logic_gate.setText(getString(R.string.OR));
    }

    private boolean checkOR() {
        return checkGate(true, true, true, false);
    }

    private boolean checkGate(boolean A, boolean B, boolean C, boolean D) {
        return inputToBool(Input1) == A && inputToBool(Input2) == B && inputToBool(Input3) == C && inputToBool(Input4) == D;
    }

    private boolean correctInput(EditText input) {
        return inputToString(input).equals("T") || inputToString(input).equals("F");
    }

    private boolean inputToBool(EditText input) {
        return inputToString(input).equals("T");
    }

    private String inputToString(EditText input) {
        return input.getEditableText().toString().toUpperCase();
    }

    private void correct() {
        Toast.makeText(this, "Correct! Next question.", Toast.LENGTH_SHORT).show();
        nextQuestion();
    }

    private void wrong() {
        Toast.makeText(this, "Wrong! Try again.", Toast.LENGTH_SHORT).show();
    }

}
