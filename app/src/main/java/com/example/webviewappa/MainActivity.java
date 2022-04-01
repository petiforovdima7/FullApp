package com.example.webviewappa;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent intent;
    Button exitButton, startGameBtn, browserBtn, settingsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("name", "");
        editor = sharedPreferences.edit();

        exitButton = findViewById(R.id.exitButton);
        browserBtn = findViewById(R.id.browserBtn);
        startGameBtn = findViewById(R.id.startGameBtn);

        TextView helloUserText = findViewById(R.id.helloUserText);

        if(!userName.equalsIgnoreCase("")){
            helloUserText.setText("Добро пожаловать : " + userName);
        }
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("id", "");
                editor.commit();
                LoginManager.getInstance().logOut();
                intent = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });


        browserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, BrowserActivity.class);
                startActivity(intent);
            }
        });

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

    }

}