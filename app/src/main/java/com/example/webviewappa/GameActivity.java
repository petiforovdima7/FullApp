package com.example.webviewappa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.webviewappa.Game.FaqActivity;
import com.example.webviewappa.Game.SettingsActivity;
import com.example.webviewappa.Game.UsersActivity;

public class GameActivity extends AppCompatActivity {
    Button backButton, getStartedBtn, btnfaq, gameSettings;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        backButton = findViewById(R.id.backButton);

        getStartedBtn=(Button)findViewById(R.id.GetStartedBtn);
        btnfaq = (Button) findViewById(R.id.btnfaq);
        gameSettings = (Button) findViewById(R.id.gameSettings);

        gameSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        btnfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), FaqActivity.class);
                startActivity(intent);
            }
        });

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), UsersActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(GameActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });
    }
}