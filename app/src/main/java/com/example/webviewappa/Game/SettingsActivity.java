package com.example.webviewappa.Game;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.webviewappa.GameActivity;
import com.example.webviewappa.R;

public class SettingsActivity extends AppCompatActivity {
    Button backButton;
    EditText playerName;
    Switch darkModeSwitch;
    Intent intent;
    TextView textView3;
    String playerNewName, playerOldName;
    Boolean darkModeSwitchPosition;
   @SuppressLint("ResourceAsColor")
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        backButton = findViewById(R.id.backButton);
        playerName = findViewById(R.id.playerName);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        textView3 = findViewById(R.id.textView3);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);

        SharedPreferences sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();



        playerOldName = sharedPreferences.getString("player_name", null);

        darkModeSwitchPosition = sharedPreferences.getBoolean("b", false);

        if(darkModeSwitchPosition) {
            View view = this.getWindow().getDecorView();
            view.setBackgroundColor(Color.GRAY);
        }

        darkModeSwitch.setChecked(darkModeSwitchPosition);

        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true) {
                    editor.putBoolean("b", true).commit();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    System.out.println("true");
                } else {
                    editor.putBoolean("b", false).commit();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    System.out.println("false");
                }
            }
        });

        if(!playerOldName.equals("")) {
            textView3.setText("Имя игрока: " + playerOldName);
        }

        playerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                playerNewName = editable.toString();

                editor.putString("player_name", playerNewName);
                editor.commit();

                textView3.setText("Имя игрока: " + playerNewName);

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SettingsActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) SettingsActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        }

        return super.dispatchTouchEvent(event);
    }

}