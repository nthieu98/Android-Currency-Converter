package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editAmount, editResult;
//    Spinner srcCurr, desCurr;
//
//    double usd2eur = 0.84;
//    double usd2vnd = 23178.0;
//
    List<String> items;
    List<String> currType = Arrays.asList("USD", "EUR", "AUD", "CAD", "JPY", "KRW", "RUB", "CNY", "SGD", "GBP");
    List<Integer> currRatio = Arrays.asList(23060, 26692, 16107, 17125, 213, 17, 303, 3383, 16622, 29424);

    Integer ratio = 0;
    Integer amount = 0;
    Integer result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratio = currRatio.get(0);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.layout_item,
                R.id.text_view,
                currType);

        Spinner spinner = findViewById(R.id.spinner_src_curr);
        spinner.setAdapter(adapter);

        editAmount = findViewById(R.id.edit_amount);
        editResult = findViewById(R.id.edit_result);

//        spinner.setSelection(currType.size());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = currType.get(i);
                ratio = currRatio.get(i);
                Log.v("TAG", "Selected: " + item);
                result = amount * ratio;
                Log.v("TAG", result.toString());
                editResult.setText(String.format("%d", result));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        editAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.v("TAG", "onTextChanged: " + charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.v("TAG", "afterTextChanged: " + editable.toString());
                try {
                    amount = Integer.parseInt(editable.toString());

                } catch (Exception e) {
                    amount = 0;
                }
                result = amount * ratio;
                Log.v("TAG", result.toString());
                editResult.setText(String.format("%d", result));

            }
        });

    }
}